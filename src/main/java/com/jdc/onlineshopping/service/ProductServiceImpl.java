package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.domain.Category;
import com.jdc.onlineshopping.domain.Product;
import com.jdc.onlineshopping.kafka.KafkaTransferKeys;
import com.jdc.onlineshopping.mapper.ProductMapper;
import com.jdc.onlineshopping.app.ops.service.OpsKafkaService;
import com.jdc.onlineshopping.app.ops.web.rest.dto.CreateProductDTO;
import com.jdc.onlineshopping.repository.BrandRepository;
import com.jdc.onlineshopping.repository.CategoryRepository;
import com.jdc.onlineshopping.repository.ProductRepository;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private OpsKafkaService kafkaService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseDTO create(CreateProductDTO dto, String requestId) {

        Product product = new Product();
        ProductDTO productDTO = updateProductInternal(product, dto, requestId);
        return ResponseUtils.responseOK(productDTO);
    }

    @Override
    public ResponseDTO update(Long id, CreateProductDTO dto, String requestId) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }

        Product product = productOptional.get();
        ProductDTO productDTO = updateProductInternal(product, dto, requestId);
        return ResponseUtils.responseOK(productDTO);
    }

    private ProductDTO updateProductInternal(Product product, CreateProductDTO dto, String requestId) {

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setColour(dto.getColour());
        product.setUrl(dto.getUrl());
        Optional<Category> categoryOptional = categoryRepository.findById(dto.getCategoryId());
        if (categoryOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        product.setCategory(categoryOptional.get());
        Optional<Brand> brandOptional = brandRepository.findById(dto.getBrandId());
        if (brandOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        product.setBrand(brandOptional.get());

        product = productRepository.save(product);
        ProductDTO productDTO = productMapper.toDto(product);
        kafkaService.send(productDTO, requestId, KafkaTransferKeys.UPDATE_PRODUCT);
        return productDTO;
    }

    @Override
    public ResponseDTO getList(String requestId, int page, int limit) {

        Pageable paging = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<Product> result = productRepository.findAll(paging);
        List<Product> categories = new ArrayList<>(result.getContent());
        return ResponseUtils.buildResponse(ResponseUtils.CODE_OK,
                ResponseUtils.MESSAGE_OK, page, limit, result.getTotalElements(), categories);
    }

    @Override
    public ResponseDTO detail(Long id, String requestId) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        Product product = productOptional.get();
        ProductDTO productDTO = productMapper.toDto(product);
        return ResponseUtils.responseOK(productDTO);
    }
}