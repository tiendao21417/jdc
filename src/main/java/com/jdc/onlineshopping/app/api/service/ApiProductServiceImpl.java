package com.jdc.onlineshopping.app.api.service;

import com.algolia.search.models.indexing.SearchResult;
import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.app.api.web.rest.dto.FindProductDTO;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.service.AlgoliaService;
import com.jdc.onlineshopping.utils.JsonSupport;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.web.rest.dto.ProductAlgoliaDTO;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class ApiProductServiceImpl implements ApiProductService {


    @Autowired
    private AlgoliaService algoliaService;

    @Override
    public ResponseDTO find(FindProductDTO findProductDTO, User user, String requestId) {

        LoggerProvider.APP.info("search info: " + JsonSupport.toJson(findProductDTO));
        SearchResult<ProductAlgoliaDTO> searchResult = algoliaService.find(findProductDTO, requestId);

        List<ProductDTO> products = searchResult.getHits().stream().map(ProductDTO::valueOf).collect(Collectors.toList());

        return ResponseUtils.buildResponse(ResponseUtils.CODE_OK,
                ResponseUtils.MESSAGE_OK, searchResult.getPage().intValue(),
                searchResult.getHitsPerPage().intValue(), (searchResult.getNbPages() * searchResult.getHitsPerPage()), products);
    }
}
