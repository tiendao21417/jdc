package com.jdc.onlineshopping.app.api.web.rest;

import com.jdc.onlineshopping.app.api.service.OrderService;
import com.jdc.onlineshopping.app.api.web.rest.dto.AddRemoveProductToCartDTO;
import com.jdc.onlineshopping.app.api.web.rest.dto.CreateOrderDTO;
import com.jdc.onlineshopping.constant.CRequestAttribute;
import com.jdc.onlineshopping.domain.User;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiendao on 24/07/2021
 */
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id,
                                           @RequestAttribute(name = CRequestAttribute.USER) User user,
                                           @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO responseDTO = orderService.getOrder(id, user, requestId);
        return ResponseEntity.ok().body(ResponseUtils.responseOK(responseDTO));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(@RequestAttribute(name = CRequestAttribute.USER) User user,
                                              @RequestBody CreateOrderDTO dto,
                                              @RequestAttribute(name = CRequestAttribute.REQUEST_ID) String requestId) {

        ResponseDTO responseDTO = orderService.create(user, dto, requestId);
        return ResponseEntity.ok().body(ResponseUtils.responseOK(responseDTO));
    }
}
