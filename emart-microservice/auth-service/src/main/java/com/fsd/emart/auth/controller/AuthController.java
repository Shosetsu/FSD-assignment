package com.fsd.emart.auth.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.auth.service.AuthService;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.entity.SessionInfo;

@RestController
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("/login")
    public JsonResponse login(@RequestBody AuthInfo info) {
        JsonResponse result = new JsonResponse();
        result.setData(authService.login(info));
        return result;
    }

    @PostMapping("/logout")
    public JsonResponse logout(@RequestBody SessionInfo info) {

        authService.logout(info);

        return new JsonResponse();
    }

    @GetMapping("/login")
    public JsonResponse getSessionType(@RequestParam("hid") String id, @RequestParam("sss") String sessionKey) {
        String accountType = Constants.ROLE_ANY;

        if (authService.checkSession(id, sessionKey)) {
            CustomerInfo cusInfo = authService.getCustomerInfo(id);
            accountType = cusInfo.getType();
        }

        JsonResponse result = new JsonResponse();
        result.setData(accountType);
        return result;
    }

}
