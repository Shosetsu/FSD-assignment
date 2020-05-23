package com.fsd.emart.auth.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.auth.service.AuthService;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.util.StringUtil;

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
    public JsonResponse logout(@RequestHeader("hid") String id) {

        authService.logout(id);

        return new JsonResponse();
    }

    @GetMapping("/login")
    public JsonResponse getSessionType(@RequestHeader("hid") String id, @RequestHeader("sss") String sessionKey,
        @RequestHeader(name = "rt", required = false) String role) {

        JsonResponse result = new JsonResponse();
        result.setData(Constants.ROLE_ANY);

        if (!StringUtil.isEmpty(role)) {
            result.setData(role);
            return result;
        }

        if (authService.checkSession(id, sessionKey)) {
            CustomerInfo cusInfo = authService.getCustomerInfo(id);
            result.setData(cusInfo.getType());
        }

        return result;
    }

}
