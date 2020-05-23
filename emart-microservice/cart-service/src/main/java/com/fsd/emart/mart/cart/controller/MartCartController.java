package com.fsd.emart.mart.cart.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.mart.cart.service.MartCartService;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.PUT}, origins = "http://localhost:4200")
@RestController
@RequestMapping("/mart")
public class MartCartController {
    @Resource
    private MartCartService martService;

    @Resource
    private AuthUtil authUtil;

    @GetMapping("/cart/{id}")
    public JsonResponse getCartList(@PathVariable String id, @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(id, sessionKey);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(martService.getCartList(id));
        return result;
    }

    @PutMapping("/cart/{id}")
    public JsonResponse putCartList(@PathVariable String id, @RequestParam("list") String[] list,
        @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(id, sessionKey);

        martService.updateCartList(id, list);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        return result;
    }

}
