package com.fsd.emart.auth.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.auth.bean.LoginInfo;
import com.fsd.emart.auth.service.AuthService;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.BizException;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Resource
	private AuthService authService;

	@PostMapping("/login")
	public JsonResponse login(@RequestBody AuthInfo info) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(authService.login(info));
		return result;
	}

	@PostMapping("/logout")
	public JsonResponse logout(@RequestBody SessionInfo info) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		authService.logout(info);
		return result;
	}

	@GetMapping("/login")
	public JsonResponse getSessionType(@RequestParam("ssId") String ssId) {
		JsonResponse result = new JsonResponse();

		// Is valid Session Param?
		if (ssId.indexOf("|") == -1) {
			throw new BizException("Invalid ssid.");
		}

		String[] tempInfo = ssId.split("\\|");
		if (!authService.checkSession(tempInfo[1], tempInfo[0])) {
			throw new BizException("Invalid ssid.");
		}

		CustomerInfo cusInfo = authService.getCustomerInfo(tempInfo[1]);

		result.setStatus(Constants.SUCCESS);

		LoginInfo data = new LoginInfo();
		data.setAccountId(cusInfo.getId());
		data.setAccountType(cusInfo.getType());
		data.setSessionKey(tempInfo[0]);
		result.setData(data);
		return result;
	}

}
