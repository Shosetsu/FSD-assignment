package com.fsd.emart.mart.query.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.mart.query.bean.FilterConditions;
import com.fsd.emart.mart.query.service.MartQueryService;

@CrossOrigin(methods = RequestMethod.GET, origins = "http://localhost:4200")
@RestController
public class MartQueryController {
    @Resource
    private MartQueryService martService;

    @GetMapping("/category")
    public JsonResponse getCategoryList() {
        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(martService.getCategoryList());
        return result;
    }

    @GetMapping("/manufacturer")
    public JsonResponse getManufacturerList() {
        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(martService.getManufacturerList());
        return result;
    }

    @GetMapping("/list")
    public JsonResponse getItemList(@ModelAttribute FilterConditions filter,
        @RequestParam(required = false, name = "sr", defaultValue = "0") int startRow) {
        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(martService.queryItems(filter, startRow));
        return result;
    }

    @GetMapping("/list/{itemId}")
    public JsonResponse getItemDetail(@PathVariable String itemId) {
        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(martService.queryItemDetail(itemId));
        return result;
    }

}
