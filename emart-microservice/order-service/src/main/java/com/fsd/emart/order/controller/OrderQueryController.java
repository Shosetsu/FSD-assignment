package com.fsd.emart.order.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.order.service.OrderQueryService;

@CrossOrigin(methods = RequestMethod.GET, origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderQueryController {
    @Resource
    private OrderQueryService orderService;

    @Resource
    private AuthUtil authUtil;

    @GetMapping("/all")
    public JsonResponse getOrderList(@RequestHeader("accountId") String accountId,
        @RequestHeader("sessionKey") String sessionKey,
        @RequestParam(defaultValue = "sr", required = false) Integer startRow) {

        // Authorization Check
        authUtil.froceCheck(accountId, sessionKey);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(orderService.queryOrder(accountId, startRow));
        return result;
    }

    @GetMapping("/detail/{orderId}")
    public JsonResponse getOrderDetail(@PathVariable("orderId") String orderId,
        @RequestHeader("accountId") String accountId, @RequestHeader("sessionKey") String sessionKey) {

        // Authorization Check - part.1
        authUtil.froceCheck(accountId, sessionKey);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(orderService.queryOrderDetail(orderId, accountId));
        return result;
    }

}
