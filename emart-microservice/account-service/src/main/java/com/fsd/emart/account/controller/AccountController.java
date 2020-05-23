package com.fsd.emart.account.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.account.bean.AccountDetailUpdateForm;
import com.fsd.emart.account.bean.SignupForm;
import com.fsd.emart.account.service.AccountService;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.util.AuthUtil;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @Resource
    private AuthUtil authUtil;

    @PostMapping("/register")
    public JsonResponse register(@RequestBody SignupForm info) {
        CustomerInfo processedInfo = new CustomerInfo();
        processedInfo.setId(info.getAccountId());
        processedInfo.setEmail(info.getEmail());
        processedInfo.setTel(info.getTelNumber());
        processedInfo.setType(Constants.ROLE_BUYER);
        if (info.isAsSeller()) {
            processedInfo.setType(Constants.ROLE_SELLER);
            processedInfo.setCompany(info.getCoName());
            processedInfo.setAddress(info.getPostalAddr());
            processedInfo.setGstin(info.getGSTIN());
            processedInfo.setBankDetail(info.getBankDetail());
            processedInfo.setSellerDate(null);
        }
        processedInfo.setCreateTime(null);

        accountService.register(processedInfo, info.getPassword());

        return new JsonResponse();
    }

    @PostMapping("/unregister")
    public JsonResponse unregister(@RequestParam("accountId") String accountId,
        @RequestParam("password") String password) {

        accountService.unregist(accountId, password);

        return new JsonResponse();
    }

    @PostMapping("/findAccount")
    public JsonResponse getSessionType(@RequestParam("email") String email) {

        accountService.findAccount(email);

        return new JsonResponse();
    }

    @GetMapping("/query/sellerDate")
    public JsonResponse getSellerCreateTime(@RequestParam("tid") String targetId,
        @RequestHeader("hid") String accountId, @RequestHeader("rt") String accountType) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, targetId);

        JsonResponse result = new JsonResponse();
        result.setData(accountService.getSellerCreateTime(targetId));
        return result;
    }

    @GetMapping("/query")
    public JsonResponse getAccountDetail(@RequestParam("tid") String targetId, @RequestHeader("hid") String accountId,
        @RequestHeader("rt") String accountType) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, targetId);

        JsonResponse result = new JsonResponse();
        result.setData(accountService.getAccountDetail(accountId));
        return result;
    }

    @PutMapping("/update")
    public JsonResponse updateAccountDetail(@RequestParam("tid") String targetId,
        @RequestHeader("hid") String accountId, @RequestHeader("rt") String accountType,
        @RequestBody AccountDetailUpdateForm form) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, targetId);

        accountService.updateAccountDetail(form, targetId);

        return new JsonResponse();
    }

}
