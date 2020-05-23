package com.fsd.emart.mart.query.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.mart.query.bean.FilterConditions;
import com.fsd.emart.mart.query.service.MartQueryService;

@RestController
public class MartQueryController {
    @Resource
    private MartQueryService martService;

    @GetMapping("/category")
    public JsonResponse getCategoryList() {
        JsonResponse result = new JsonResponse();
        result.setData(martService.getCategoryList());
        return result;
    }

    @GetMapping("/manufacturer")
    public JsonResponse getManufacturerList() {
        JsonResponse result = new JsonResponse();
        result.setData(martService.getManufacturerList());
        return result;
    }

    @GetMapping("/list")
    public JsonResponse getItemList(@ModelAttribute FilterConditions filter,
        @RequestParam(required = false, name = "sr", defaultValue = "0") int startRow) {
        JsonResponse result = new JsonResponse();
        result.setData(martService.queryItems(filter, startRow));
        return result;
    }

    @GetMapping("/detail")
    public JsonResponse getItemDetail(@RequestParam("gid") String itemId) {
        JsonResponse result = new JsonResponse();
        result.setData(martService.queryItemDetail(itemId));
        return result;
    }

}
