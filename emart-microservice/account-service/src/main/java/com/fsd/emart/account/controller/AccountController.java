package com.fsd.emart.account.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.account.bean.AccountDetailUpdateForm;
import com.fsd.emart.account.bean.CustomerDetail;
import com.fsd.emart.account.bean.SignupForm;
import com.fsd.emart.account.bean.UnregisterForm;
import com.fsd.emart.account.service.AccountService;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.common.util.StringUtil;

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @Resource
    private AuthUtil authUtil;

    @PostMapping("/register")
    public JsonResponse register(@RequestBody SignupForm info) {
        CustomerInfo processedInfo = new CustomerInfo();
        processedInfo.setId(StringUtil.getNonblankString(info.getAccountId()));
        processedInfo.setEmail(StringUtil.getNonblankString(info.getEmail()));
        processedInfo.setType(Constants.ROLE_BUYER);
        if (info.isAsSeller()) {
            processedInfo.setType(Constants.ROLE_SELLER);
            processedInfo.setCompany(StringUtil.getNonblankString(info.getCoName()));
            processedInfo.setAddress(StringUtil.getNonblankString(info.getPostalAddr()));
            processedInfo.setGstin(StringUtil.getNonblankString(info.getGstin()));
            processedInfo.setBankDetail(StringUtil.getNonblankString(info.getBankDetail()));
            processedInfo.setSellerDate(new Timestamp(System.currentTimeMillis()));
            processedInfo.setTel(info.getTelNumber());
        } else {
            processedInfo.setTel(StringUtil.getNonblankString(info.getTelNumber()));
        }
        processedInfo.setCreateTime(null);

        accountService.register(processedInfo, info.getPassword());

        return new JsonResponse();
    }

    @PostMapping("/unregister")
    public JsonResponse unregister(@RequestHeader("hid") String accountId, @RequestBody UnregisterForm password) {

        accountService.unregist(accountId, password.getPassword());

        return new JsonResponse();
    }

    @PostMapping("/findaccount")
    public JsonResponse getSessionType(@RequestParam("email") String email) {

        accountService.findAccount(email);

        return new JsonResponse();
    }

    @GetMapping("/query/sellerdate")
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
        result.setData(CustomerDetail.getInfoFromEntity(accountService.getAccountDetail(accountId)));
        return result;
    }

    @PutMapping("/update")
    public JsonResponse updateAccountDetail(@RequestHeader("hid") String accountId,
        @RequestHeader("rt") String accountType, @RequestBody AccountDetailUpdateForm form) {
        // Authorization Check
        authUtil.authCheck(accountId, accountType, form.getTargetId());

        accountService.updateAccountDetail(form, form.getTargetId());

        return new JsonResponse();
    }

}
