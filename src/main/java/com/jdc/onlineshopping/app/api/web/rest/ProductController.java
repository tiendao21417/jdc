package com.jdc.onlineshopping.app.api.web.rest;

import com.jdc.onlineshopping.app.api.service.ApiProductService;
import com.jdc.onlineshopping.app.api.web.rest.dto.FindProductDTO;
import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.constant.CPagging;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tiendao on 17/07/2021
 */
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ApiProductService productService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> find(
            @RequestAttribute(name = CRequestAttribute.USER) User user,
            @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "categories", required = false) String categories,
            @RequestParam(name = "brands", required = false) String brands,
            @RequestParam(name = "colours", required = false) String colours,
            @RequestParam(name = "price_min", required = false) Double priceMin,
            @RequestParam(name = "price_max", required = false) Double priceMax,
            @RequestParam(name = CPagging.PAGE_NAME, defaultValue = CPagging.STR_DEFAULT_PAGE) int page,
            @RequestParam(name = CPagging.LIMIT_NAME, defaultValue = CPagging.STR_DEFAULT_LIMIT) int limit) {

        FindProductDTO findProductDTO = new FindProductDTO();
        findProductDTO.setName(name);
        findProductDTO.setCategoryIds(getAsLongList(categories));
        findProductDTO.setBrandIds(getAsLongList(brands));
        findProductDTO.setColours(getAsStringList(colours));
        findProductDTO.setPriceMin(priceMin);
        findProductDTO.setPriceMax(priceMax);
        findProductDTO.setLimit(limit);
        findProductDTO.setPage(page);
        ResponseDTO responseDTO = productService.find(findProductDTO, user, requestId);
        return ResponseEntity.ok().body(responseDTO);
    }

    private List<Long> getAsLongList(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        String[] idTexts = text.split(",");
        if (idTexts.length == 0) {
            return null;
        }
        List<Long> result = new ArrayList<>();
        for (String idText : idTexts) {
            try {
                Long id = Long.parseLong(idText);
                result.add(id);
            } catch (NumberFormatException ne) {
                Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                        new Object[]{"body"}, null));
            }
        }
        return result;
    }

    private List<String> getAsStringList(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        String[] values = text.split(",");
        if (values.length == 0) {
            return null;
        }
        return Arrays.asList(values);
    }

}
