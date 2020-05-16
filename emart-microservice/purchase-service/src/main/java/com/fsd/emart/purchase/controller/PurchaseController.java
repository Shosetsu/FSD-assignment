package com.fsd.emart.purchase.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.purchase.bean.PurchaseItemInfo;
import com.fsd.emart.purchase.service.PurchaseService;

@CrossOrigin(methods = RequestMethod.POST, origins = "http://localhost:4200")
@RestController
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;

    @Resource
    private AuthUtil authUtil;

    @PostMapping("/purchase")
    public JsonResponse getCartList(@RequestBody List<PurchaseItemInfo> purchaseList,
        @RequestHeader("accountId") String accountId, @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(accountId, sessionKey);

        purchaseService.purchase(purchaseList, accountId);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}
