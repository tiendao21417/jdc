package com.jdc.onlineshopping.service;

import com.algolia.search.JavaNetHttpRequester;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchConfig;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.algolia.search.models.settings.IndexSettings;
import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import com.jdc.onlineshopping.app.api.web.rest.dto.FindProductDTO;
import com.jdc.onlineshopping.utils.JsonSupport;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
import com.jdc.onlineshopping.web.rest.dto.ProductAlgoliaDTO;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author tiendao on 22/07/2021
 */
@Service
public class AlgoliaServiceImpl implements AlgoliaService {


    private SearchIndex<BrandDTO> brandDTOSearchIndex;
    private SearchIndex<CategoryDTO> categoryDTOSearchIndex;
    private SearchIndex<ProductAlgoliaDTO> productDTOSearchIndex;

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

        categoryDTOSearchIndex = client.initIndex("categoryIndex", CategoryDTO.class);

        productDTOSearchIndex = client.initIndex("productIndex", ProductAlgoliaDTO.class);
        // Asynchronous
        productDTOSearchIndex.setSettingsAsync(new IndexSettings().setSearchableAttributes(
                Collections.singletonList("name")
        ).setAttributesForFaceting(
                Collections.singletonList("filterOnly(colour)")
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
        ProductAlgoliaDTO productAlgoliaDTO = ProductAlgoliaDTO.valueOf(productDTO);
        this.productDTOSearchIndex.saveObjectAsync(productAlgoliaDTO);
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
        List<ProductAlgoliaDTO> result = new ArrayList<>();
        for (ProductDTO productDTO : brandDTOs) {

            ProductAlgoliaDTO productAlgoliaDTO = ProductAlgoliaDTO.valueOf(productDTO);
            result.add(productAlgoliaDTO);
        }
        this.productDTOSearchIndex.saveObjectsAsync(result);
    }

    @Override
    public SearchResult<ProductAlgoliaDTO> find(FindProductDTO findProductDTO, String requestId) {
        initializeIfNeed();
        boolean isNotFirst = false;
        String queryString = "";
        if (findProductDTO.getName() != null && !findProductDTO.getName().isBlank()) {
            queryString = findProductDTO.getName();
        }
        StringBuilder searchBuilder = new StringBuilder("");
        isNotFirst = addColourInCondition(searchBuilder, findProductDTO.getColours(), false);
        isNotFirst = addInConditionByIds(searchBuilder, "category_id", findProductDTO.getCategoryIds(), isNotFirst);
        isNotFirst = addInConditionByIds(searchBuilder, "brand_id", findProductDTO.getBrandIds(), isNotFirst);
        addPriceRange(searchBuilder, findProductDTO.getPriceMin(), findProductDTO.getPriceMax(), isNotFirst);

        LoggerProvider.APP.info(String.format("Search info [%s]", searchBuilder.toString()));
        Query query = new Query(queryString).setPage((int) findProductDTO.getPage())
                .setHitsPerPage((int) findProductDTO.getLimit())
                .setFilters(searchBuilder.toString());
        return this.productDTOSearchIndex.search(query);
    }

    private void addPriceRange(StringBuilder searchBuilder, Double priceMin, Double priceMax, boolean isNotFirst) {

        if (priceMin == null && priceMax == null) {
            return;
        }
        if (isNotFirst) {
            searchBuilder.append(" AND ");
        }
        searchBuilder.append(" (");
        boolean hasMin = false;
        if (priceMin != null) {
            searchBuilder.append("price>").append(priceMin);
            hasMin = true;
        }
        if (priceMax != null) {
            if (hasMin) {
                searchBuilder.append(" AND ");
            } else {
                searchBuilder.append(" ");
            }
            searchBuilder.append("price<").append(priceMax);
        }
        searchBuilder.append(")");
    }

//    public static void main(String[] args) {
//        AlgoliaServiceImpl sv = new AlgoliaServiceImpl();
//        FindProductDTO findProductDTO = new FindProductDTO();
//        findProductDTO.setPage(0);
//        findProductDTO.setLimit(20);
//        findProductDTO.setCategoryIds(new ArrayList<>());
//        findProductDTO.getCategoryIds().add(1L);
//        findProductDTO.getCategoryIds().add(2L);
//        findProductDTO.setBrandIds(new ArrayList<>());
//        findProductDTO.getBrandIds().add(1L);
//        findProductDTO.getBrandIds().add(2L);
//        findProductDTO.setColours(new ArrayList<>());
//        //findProductDTO.getColours().add("Gold");
//        //findProductDTO.setName("iPhone");
//        SearchResult<ProductAlgoliaDTO> result = sv.find(findProductDTO, "1234");
//        LoggerProvider.APP.info(JsonSupport.toJson(result.getHits()));
//
//        result = sv.productDTOSearchIndex.search(new Query("Green").setFilters("brand_id=9 AND category_id=3 AND price>11.0 AND price<25.0 AND colour:Red"));
//        LoggerProvider.APP.info("result: " +  JsonSupport.toJson(result.getHits()));
//
//        // SearchResult<BrandDTO> result2 = sv.brandDTOSearchIndex.search(new Query(""));
//        // LoggerProvider.APP.info("result2: " +  JsonSupport.toJson(result2.getHits()));
//
//        // SearchResult<BrandDTO> result3 = sv.brandDTOSearchIndex.search(new Query("* APPLE"));
//        // LoggerProvider.APP.info("result3: " +  JsonSupport.toJson(result3.getHits()));
//    }

    private boolean addInConditionByIds(StringBuilder searchBuilder, String key, List<Long> values, boolean isNotFirst) {

        if (values == null || values.size() == 0) {
            return isNotFirst;
        }
        if (isNotFirst) {
            searchBuilder.append(" AND ");
        }
        searchBuilder.append(" (");
        boolean isFirst = true;
        for (Long value : values) {

            if (isFirst) {
                isFirst = false;
            } else {
                searchBuilder.append(" OR ");
            }
            searchBuilder.append(key).append("=").append(value);
        }
        searchBuilder.append(")");
        return true;
    }

    private boolean addColourInCondition(StringBuilder searchBuilder, List<String> values, boolean isNotFirst) {

        if (values == null || values.size() == 0) {
            return isNotFirst;
        }
        if (isNotFirst) {
            searchBuilder.append(" AND ");
        }
        searchBuilder.append(" (");

        boolean isFirst = true;
        for (String value : values) {

            if (isFirst) {
                isFirst = false;
            } else {
                searchBuilder.append(" OR ");
            }
            searchBuilder.append("colour").append(":").append(value);
        }
        searchBuilder.append(")");
        return true;
    }

    private boolean addEqualCondition(StringBuilder searchBuilder, String key, String value, boolean isNotFirst) {

        if (value == null || value.isBlank()) {
            return isNotFirst;
        }
        if (isNotFirst) {
            searchBuilder.append(" AND ").append(key).append(":").append(value);
            return true;
        }
        searchBuilder.append(" ").append(key).append(":").append(value);
        return true;
    }
}
