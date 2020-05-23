package com.fsd.emart.seller.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.seller.service.SellerManagementService;

@RestController
public class SellerCenterController {
    @Resource
    private SellerManagementService sellerService;

    @Resource
    private AuthUtil authUtil;

    @GetMapping("/checkStatus")
    public JsonResponse isSeller(@RequestParam("sid") String targetId, @RequestHeader("hid") String accountId,
        @RequestHeader("rt") String accountType) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, targetId);

        JsonResponse result = new JsonResponse();
        result.setData(sellerService.isSeller(targetId));
        return result;
    }

    @GetMapping("/salesList")
    public JsonResponse getSalesList(@RequestParam("sid") String targetId, @RequestHeader("hid") String accountId,
        @RequestHeader("rt") String accountType) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, targetId);

        JsonResponse result = new JsonResponse();
        result.setData(sellerService.getSalesList(targetId));
        return result;
    }

    @GetMapping("/overview")
    public JsonResponse getSalesOverviewByMonth(@RequestParam("sid") String targetId,
        @RequestParam("date") String dateYm, @RequestHeader("hid") String accountId,
        @RequestHeader("rt") String accountType) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, targetId);

        JsonResponse result = new JsonResponse();
        result.setData(sellerService.getSalesOverviewByMonth(targetId, dateYm));
        return result;
    }

    @PostMapping("/item")
    public JsonResponse updateItem(@RequestBody GoodInfo info, @RequestHeader("hid") String accountId) {

        sellerService.saveSalesItem(info, accountId);

        return new JsonResponse();
    }

    @PutMapping("/item/status")
    public JsonResponse updateItemStatus(@RequestParam("iid") String itemId, @RequestParam("status") Integer status,
        @RequestHeader("hid") String accountId, @RequestHeader("rt") String accountType) {

        sellerService.changeSalesItemStatus(itemId, status, accountId, accountType);

        return new JsonResponse();
    }
}
