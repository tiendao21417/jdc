package com.jdc.onlineshopping.app.api.service;

import com.jdc.onlineshopping.app.api.web.rest.dto.*;
import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.constant.RedisKeys;
import com.jdc.onlineshopping.domain.Product;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.repository.ProductRepository;
import com.jdc.onlineshopping.service.RedisService;
import com.jdc.onlineshopping.utils.JsonSupport;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tiendao on 23/07/2021
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseDTO addRemoveToCart(User user, AddRemoveProductToCartDTO dto, String requestId) {

        Optional<Product> productOptional = productRepository.findById(dto.getProductId());
        if (productOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        CartStoreDTO cartStoreDTO = getCart(user);

        // update cart
        addRemoveProduct(cartStoreDTO, dto);

        // store update
        redisService.set(RedisKeys.KEY_CART, JsonSupport.toJson(cartStoreDTO), String.valueOf(user.getId()));

        CartDTO cartDTO = new CartDTO();
        for (CartStoreItem cartStoreItem : cartStoreDTO.getItems()) {

            Product product;
            if (cartStoreItem.getProductId() == productOptional.get().getId()) {
                product = productOptional.get();
            } else {
                Optional<Product> productStoreOptional = productRepository.findById(cartStoreItem.getProductId());
                if (productStoreOptional.isEmpty()) {
                    Throws.of(CErrors.CREATE_ORDER_FAILED_BY_PRODUCT_NOT_FOUND,
                            messageSource.getMessage(CErrors.CREATE_ORDER_FAILED_BY_PRODUCT_NOT_FOUND,
                                    new Object[]{"body"}, null));
                }
                product = productStoreOptional.get();
            }
            CartItem cartItem = new CartItem();
            cartItem.setBrandCode(product.getBrand().getCode());
            cartItem.setCategoryCode(product.getCategory().getCode());
            cartItem.setBrandName(product.getBrand().getName());
            cartItem.setCategoryName(product.getCategory().getName());
            cartItem.setColour(product.getColour());
            cartItem.setAmount(cartStoreItem.getAmount());
            cartItem.setUrl(product.getUrl());
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartDTO.getItems().add(cartItem);
        }
        return ResponseUtils.responseOK(cartDTO);
    }

    @Override
    public ResponseDTO detail(User user, String requestId) {

        CartStoreDTO cartDTO = getCart(user);
        return ResponseUtils.responseOK(cartDTO);
    }

    private void addRemoveProduct(CartStoreDTO cartDTO, AddRemoveProductToCartDTO dto) {

        for (CartStoreItem item : cartDTO.getItems()) {

            if (item.getProductId() == dto.getProductId()) {
                int currentAmount = item.getAmount() + dto.getAmount();
                if (currentAmount < 0) {
                    currentAmount = 0;
                }
                item.setAmount(currentAmount);
                return;
            }
        }
        if (dto.getAmount() < 0) {
            return;
        }
        CartStoreItem item = new CartStoreItem();
        item.setProductId(dto.getProductId());
        item.setAmount(dto.getAmount());
        cartDTO.getItems().add(item);
    }

    @Override
    public CartStoreDTO getCart(User user) {

        String cartJson = redisService.get(RedisKeys.KEY_CART, String.valueOf(user.getId()));

        CartStoreDTO cartDTO;
        if (cartJson == null || "".equals(cartJson)) {
            cartDTO = new CartStoreDTO();
        } else {
            cartDTO = JsonSupport.toObject(cartJson, CartStoreDTO.class);
        }
        if (cartDTO == null) {
            cartDTO = new CartStoreDTO();
        }
        return cartDTO;
    }

    @Override
    public void clear(User user) {
        redisService.set(RedisKeys.KEY_CART, JsonSupport.toJson(new CartStoreDTO()), String.valueOf(user.getId()));
    }
}
