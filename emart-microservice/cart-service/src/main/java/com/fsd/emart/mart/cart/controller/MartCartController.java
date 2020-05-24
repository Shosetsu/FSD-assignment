package com.fsd.emart.mart.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.mart.cart.bean.CartData;
import com.fsd.emart.mart.cart.service.MartCartService;

@RestController
public class MartCartController {
    @Resource
    private MartCartService martService;

    @GetMapping
    public JsonResponse getCartList(@RequestHeader("hid") String id) {
        JsonResponse result = new JsonResponse();
        result.setData(martService.getCartList(id));
        return result;
    }

    @PutMapping
    public JsonResponse putCartList(@RequestHeader("hid") String id,
        @RequestBody(required = false) List<CartData> list) {

        if (list == null) {
            list = new ArrayList<>();
        }

        martService.updateCartList(id, list);

        return new JsonResponse();
    }

}
