package com.jdc.onlineshopping.service;

import com.algolia.search.JavaNetHttpRequester;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchConfig;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.algolia.search.models.settings.IndexSettings;
import com.jdc.onlineshopping.app.api.web.rest.dto.FindProductDTO;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class AlgoliaServiceImpl implements AlgoliaService {


    private SearchIndex<BrandDTO> brandDTOSearchIndex;
    private SearchIndex<CategoryDTO> categoryDTOSearchIndex;
    private SearchIndex<ProductDTO> productDTOSearchIndex;

    private boolean isInit = false;

    public synchronized void initializeIfNeed() {
        if (isInit) {
            return;
        }
        initialize();
    }


    private void initialize() {

        SearchConfig config = new SearchConfig.Builder("DTO5PQL9QV", "62c39f7183eee87f822e8804694cb03c").build();
        SearchClient client = new SearchClient(config, new JavaNetHttpRequester(config));

        brandDTOSearchIndex = client.initIndex("brandIndex", BrandDTO.class);
        // Asynchronous
        brandDTOSearchIndex.setSettingsAsync(new IndexSettings().setSearchableAttributes(
                Arrays.asList("code", "name")
        ));

        categoryDTOSearchIndex = client.initIndex("categoryIndex", CategoryDTO.class);
        // Asynchronous
        brandDTOSearchIndex.setSettingsAsync(new IndexSettings().setSearchableAttributes(
                Arrays.asList("code", "name")
        ));

        productDTOSearchIndex = client.initIndex("productIndex", ProductDTO.class);
        // Asynchronous
        brandDTOSearchIndex.setSettingsAsync(new IndexSettings().setSearchableAttributes(
                Arrays.asList("category.", "name")
        ));

        clearAllDataOnAlgolia();
        isInit = true;
    }

    /** for development
     *
     */
    private void clearAllDataOnAlgolia() {

        brandDTOSearchIndex.clearObjects();
        categoryDTOSearchIndex.clearObjects();
        productDTOSearchIndex.clearObjects();
    }

    @Override
    public void pushBrand(BrandDTO brandDTO) {
        initializeIfNeed();
        brandDTO.setObjectID(String.valueOf(brandDTO.getId()));
        this.brandDTOSearchIndex.saveObjectAsync(brandDTO);
    }

    @Override
    public void pushCategory(CategoryDTO categoryDTO) {
        initializeIfNeed();
        categoryDTO.setObjectID(String.valueOf(categoryDTO.getId()));
        this.categoryDTOSearchIndex.saveObjectAsync(categoryDTO);
    }

    @Override
    public void pushProduct(ProductDTO productDTO) {
        initializeIfNeed();
        productDTO.setObjectID(String.valueOf(productDTO.getId()));
        this.productDTOSearchIndex.saveObjectAsync(productDTO);
    }

    @Override
    public void pushBrand(BrandDTO[] dtos) {
        initializeIfNeed();
        List<BrandDTO> result = new ArrayList<>();
        for (BrandDTO dto : dtos) {
            dto.setObjectID(String.valueOf(dto.getId()));
            result.add(dto);
        }
        this.brandDTOSearchIndex.saveObjectsAsync(result);
    }

    @Override
    public void pushCategory(CategoryDTO[] dtos) {
        initializeIfNeed();
        List<CategoryDTO> result = new ArrayList<>();
        for (CategoryDTO dto : dtos) {
            dto.setObjectID(String.valueOf(dto.getId()));
            result.add(dto);
        }
        this.categoryDTOSearchIndex.saveObjectsAsync(result);
    }

    @Override
    public void pushProduct(ProductDTO[] brandDTOs) {
        initializeIfNeed();
        List<ProductDTO> result = new ArrayList<>();
        for (ProductDTO productDTO : brandDTOs) {
            productDTO.setObjectID(String.valueOf(productDTO.getId()));
            result.add(productDTO);
        }
        this.productDTOSearchIndex.saveObjectsAsync(result);
    }

    @Override
    public SearchResult<ProductDTO> find(FindProductDTO findProductDTO, String requestId) {
        return this.productDTOSearchIndex.search(new Query(""));
    }
}
