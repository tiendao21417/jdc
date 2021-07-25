package com.jdc.onlineshopping.app.worker.service;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.kafka.HeaderData;
import com.jdc.onlineshopping.kafka.KafkaTransferData;
import com.jdc.onlineshopping.kafka.KafkaTransferKeys;
import com.jdc.onlineshopping.service.AlgoliaService;
import com.jdc.onlineshopping.utils.JsonSupport;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Autowired
    private AlgoliaService algoliaService;

    @KafkaListener(topics = "${kafka.topics.transfer}", groupId = "${kafka.group.transfer}")
    public void consumeData(String data) {

        LoggerProvider.APP.info(String.format("Consumed data [%s]", data));
        KafkaTransferData transferData = JsonSupport.toObject(data, KafkaTransferData.class);
        HeaderData header = transferData.getHeader();
        if (transferData.getData() == null || transferData.getData().isEmpty()) {
            return;
        }
        if (KafkaTransferKeys.UPDATE_BRAND.equals(header.getKey())) {
            handleUpdateBrand(JsonSupport.toObject(transferData.getData(), BrandDTO.class), header.getRequestId());
            return;
        }

        if (KafkaTransferKeys.UPDATE_CATEGORY.equals(header.getKey())) {
            handleUpdateCategory(JsonSupport.toObject(transferData.getData(), CategoryDTO.class), header.getRequestId());
            return;
        }

        if (KafkaTransferKeys.UPDATE_PRODUCT.equals(header.getKey())) {
            handleUpdateProduct(JsonSupport.toObject(transferData.getData(), ProductDTO.class), header.getRequestId());
        }
    }

    private void handleUpdateProduct(ProductDTO productDTO, String requestId) {

        LoggerProvider.APP.info("Start send product to algolia: " + JsonSupport.toJson(productDTO));
        algoliaService.pushProduct(productDTO);
    }

    private void handleUpdateCategory(CategoryDTO categoryDTO, String requestId) {

        LoggerProvider.APP.info("Start send category to algolia: " + JsonSupport.toJson(categoryDTO));
        algoliaService.pushCategory(categoryDTO);
    }

    private void handleUpdateBrand(BrandDTO brandDTO, String requestId) {
        LoggerProvider.APP.info("Start send brand to algolia: " + JsonSupport.toJson(brandDTO));

        algoliaService.pushBrand(brandDTO);
        LoggerProvider.APP.info("Done push brand to Algolia");
    }
}
