package com.jdc.onlineshopping.app.api.service;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.app.api.mapper.OrderMapper;
import com.jdc.onlineshopping.app.api.web.rest.dto.CartStoreDTO;
import com.jdc.onlineshopping.app.api.web.rest.dto.CartStoreItem;
import com.jdc.onlineshopping.app.api.web.rest.dto.CreateOrderDTO;
import com.jdc.onlineshopping.app.api.web.rest.dto.OrderDTO;
import com.jdc.onlineshopping.service.KafkaService;
import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.constant.EPaymentType;
import com.jdc.onlineshopping.constant.EStatus;
import com.jdc.onlineshopping.domain.Order;
import com.jdc.onlineshopping.domain.OrderSnapshot;
import com.jdc.onlineshopping.domain.Product;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.kafka.KafkaTransferKeys;
import com.jdc.onlineshopping.mapper.ProductMapper;
import com.jdc.onlineshopping.repository.OrderRepository;
import com.jdc.onlineshopping.repository.OrderSnapshotRepository;
import com.jdc.onlineshopping.repository.ProductRepository;
import com.jdc.onlineshopping.utils.JsonSupport;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author tiendao on 24/07/2021
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSnapshotRepository orderSnapshotRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseDTO getOrder(Long id, User user, String requestId) {

        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            Throws.of(CErrors.ORDER_IS_NOT_FOUND, messageSource.getMessage(CErrors.ORDER_IS_NOT_FOUND,
                    new Object[]{"body"}, null));
        }
        OrderDTO orderDTO = orderMapper.toDto(orderOptional.get());
        return ResponseUtils.responseOK(orderDTO);
    }

    @Override
    public ResponseDTO create(User user, CreateOrderDTO dto, String requestId) {

        CartStoreDTO cartDTO = cartService.getCart(user);
        if (cartDTO.getItems() == null || cartDTO.getItems().isEmpty()) {

            Throws.of(CErrors.CREATE_ORDER_FAILED_BY_EMPTY_CART, messageSource.getMessage(CErrors.CREATE_ORDER_FAILED_BY_EMPTY_CART,
                    new Object[]{"body"}, null));

        }

        Order order = new Order();
        order.setOrderSnapshots(new HashSet<>());
        order.setCustomerAddress(dto.getCustomerAddress());
        order.setCustomerName(dto.getCustomerName());
        order.setCustomerPhone(dto.getCustomerPhone());
        order.setStatus(EStatus.NOT_YET_PAYMENT);
        order.setPaymentMethod(EPaymentType.COD);
        order.setTotalPrice(0);

        long totalPrice = 0;

        List<Product> products = new ArrayList<>();
        for (CartStoreItem item : cartDTO.getItems()) {

            Optional<Product> productOptional = productRepository.findById(item.getProductId());
            if (productOptional.isEmpty()) {
                Throws.of(CErrors.CREATE_ORDER_FAILED_BY_PRODUCT_NOT_FOUND,
                        messageSource.getMessage(CErrors.CREATE_ORDER_FAILED_BY_PRODUCT_NOT_FOUND,
                        new Object[]{"body"}, null));
            }
            Product product = productOptional.get();
            LoggerProvider.APP.info(String.format("product info [%s]", JsonSupport.toJson(product)));
            if (product.getRemainAmount() < item.getAmount()) {
                Throws.of(CErrors.CREATE_ORDER_FAILED_BY_OUT_STOCK,
                        messageSource.getMessage(CErrors.CREATE_ORDER_FAILED_BY_OUT_STOCK,
                                new Object[]{"body"}, null));
            }

            // update remain amount
            product.setRemainAmount(product.getRemainAmount() - item.getAmount());
            products.add(product);

            // update total price
            totalPrice += product.getPrice() * item.getAmount();

            OrderSnapshot orderSnapshot = new OrderSnapshot();
            orderSnapshot.setAmount(item.getAmount());
            orderSnapshot.setBrandCode(product.getBrand().getCode());
            orderSnapshot.setBrandName(product.getBrand().getName());
            orderSnapshot.setCategoryCode(product.getCategory().getCode());
            orderSnapshot.setCategoryName(product.getCategory().getName());
            orderSnapshot.setColour(product.getColour());
            orderSnapshot.setUnitPrice(product.getPrice());
            orderSnapshot.setUrl(product.getUrl());
            orderSnapshot.setProductName(product.getName());
            orderSnapshot.setOrder(order);
            order.getOrderSnapshots().add(orderSnapshot);
        }

        // sum total price
        order.setTotalPrice(totalPrice);
        // store order
        order = orderRepository.save(order);

        // update amount
        productRepository.saveAll(products);

        // now clear cart
        cartService.clear(user);

        // update to store
        for (Product product : products) {
            ProductDTO productDTO = productMapper.toDto(product);
            kafkaService.send(productDTO, requestId, KafkaTransferKeys.UPDATE_PRODUCT);
        }

        OrderDTO orderDTO = orderMapper.toDto(order);
        return ResponseUtils.responseOK(orderDTO);
    }
}
