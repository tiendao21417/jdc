package com.jdc.onlineshopping.app.api.web.rest;

import com.jdc.onlineshopping.app.api.service.CartService;
import com.jdc.onlineshopping.app.api.web.rest.dto.AddRemoveProductToCartDTO;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiendao on 23/07/2021
 */
@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(@RequestAttribute(name = CRequestAttribute.USER) User user,
                                           @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId
                                           ) {

        ResponseDTO responseDTO = cartService.detail(user, requestId);
        return ResponseEntity.ok().body(ResponseUtils.responseOK(responseDTO));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> addProductToCart(@RequestAttribute(name = CRequestAttribute.USER) User user,
                                                        @RequestBody AddRemoveProductToCartDTO dto,
                                                        @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO responseDTO = cartService.addRemoveToCart(user, dto, requestId);
        return ResponseEntity.ok().body(ResponseUtils.responseOK(responseDTO));
    }
}
