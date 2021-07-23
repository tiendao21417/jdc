package com.jdc.onlineshopping.service;

import com.algolia.search.models.indexing.SearchResult;
import com.jdc.onlineshopping.app.api.web.rest.dto.FindProductDTO;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;

public interface AlgoliaService {

    void pushBrand(BrandDTO brandDTO);

    void pushCategory(CategoryDTO brandDTO);

    void pushProduct(ProductDTO brandDTO);

    SearchResult<ProductDTO> find(FindProductDTO findProductDTO, String requestId);
}
