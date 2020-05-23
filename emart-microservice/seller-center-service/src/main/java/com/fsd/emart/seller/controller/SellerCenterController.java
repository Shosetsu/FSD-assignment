package com.fsd.emart.seller.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.seller.service.SellerManagementService;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, origins = "http://localhost:4200")
@RestController
@RequestMapping("/seller")
public class SellerCenterController {
    @Resource
    private SellerManagementService sellerService;

    @Resource
    private AuthUtil authUtil;

    @GetMapping("/checkStatus")
    public JsonResponse isSeller(@RequestParam("sid") String targetId, @RequestHeader("id") String accountId,
        @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.authCheck(accountId, sessionKey, targetId);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(sellerService.isSeller(targetId));
        return result;
    }

    @GetMapping("/salesList")
    public JsonResponse getSalesList(@RequestParam("sid") String targetId, @RequestHeader("id") String accountId,
        @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.authCheck(accountId, sessionKey, targetId);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(sellerService.getSalesList(targetId));
        return result;
    }

    @GetMapping("/overview")
    public JsonResponse getSalesOverviewByMonth(@RequestParam("sid") String targetId,
        @RequestParam("date") String dateYm, @RequestHeader("id") String accountId,
        @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.authCheck(accountId, sessionKey, targetId);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(sellerService.getSalesOverviewByMonth(targetId, dateYm));
        return result;
    }

    @PostMapping("/item")
    public JsonResponse updateItem(@RequestBody GoodInfo info, @RequestHeader("id") String accountId,
        @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(accountId, sessionKey);

        sellerService.saveSalesItem(info, accountId);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        return result;
    }

    @PutMapping("/item/{itemId}/status")
    public JsonResponse updateItemStatus(@PathVariable("itemId") String itemId, @RequestParam("status") Integer status,
        @RequestHeader("id") String accountId, @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(accountId, sessionKey);

        sellerService.changeSalesItemStatus(itemId, status, accountId);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        return result;
    }
}
