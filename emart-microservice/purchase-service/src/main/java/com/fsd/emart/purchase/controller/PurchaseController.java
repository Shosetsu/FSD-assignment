package com.fsd.emart.purchase.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.purchase.bean.PurchaseItemInfo;
import com.fsd.emart.purchase.service.PurchaseService;

@RestController
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    @PostMapping
    public JsonResponse getCartList(@RequestBody List<PurchaseItemInfo> purchaseList,
        @RequestHeader("hid") String accountId) {

        purchaseService.purchase(purchaseList, accountId);

        return new JsonResponse();
    }

}
