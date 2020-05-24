package com.fsd.emart.order.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.order.service.OrderQueryService;

@RestController
public class OrderQueryController {
    @Resource
    private OrderQueryService orderService;

    @GetMapping("/all")
    public JsonResponse getOrderList(@RequestHeader("hid") String accountId,
        @RequestParam(name = "sr", required = false, defaultValue = "0") Integer startRow) {

        JsonResponse result = new JsonResponse();
        result.setData(orderService.queryOrder(accountId, startRow));
        return result;
    }

    @GetMapping("/detail")
    public JsonResponse getOrderDetail(@RequestParam("oid") String orderId, @RequestHeader("hid") String accountId) {

        JsonResponse result = new JsonResponse();
        result.setData(orderService.queryOrderDetail(orderId, accountId));
        return result;
    }

}
