package com.fsd.emart.account.controller;

import java.sql.Timestamp;
import java.util.Date;

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

import com.fsd.emart.account.bean.SignupForm;
import com.fsd.emart.account.service.AccountService;
import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.entity.CustomerInfo;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT }, origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class AccountController {
	@Resource
	private AccountService accountService;

	@PostMapping("/register")
	public JsonResponse register(@RequestBody SignupForm info) {
		JsonResponse result = new JsonResponse();

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
		}
		processedInfo.setCreateTime(new Timestamp(new Date().getTime()));

		accountService.register(processedInfo, info.getPassword());

		result.setStatus(Constants.SUCCESS);
		return result;
	}

	@PostMapping("/unregister")
	public JsonResponse unregister(@RequestParam("accountId") String accountId,
			@RequestParam("password") String password) {
		JsonResponse result = new JsonResponse();

		accountService.unregist(accountId, password);

		result.setStatus(Constants.SUCCESS);
		return result;
	}

	@PostMapping("/findAccount")
	public JsonResponse getSessionType(@RequestParam("email") String email) {
		JsonResponse result = new JsonResponse();

		accountService.findAccount(email);

		result.setStatus(Constants.SUCCESS);
		return result;
	}

	@GetMapping("/query/{accountId}/sellerDate")
	public JsonResponse getSellerCreateTime(@PathVariable("accountId") String accountId,
			@RequestHeader("sessionKey") String sessionKey) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(accountService.getSellerCreateTime(accountId));
		return result;
	}

	@GetMapping("/query/{accountId}")
	public JsonResponse getAccountDetail(@PathVariable("accountId") String accountId,
			@RequestHeader("sessionKey") String sessionKey) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(accountService.getAccountDetail(accountId));
		return result;
	}

	@PutMapping("/update/{accountId}")
	public JsonResponse updateAccountDetail(@PathVariable("accountId") String accountId,
			@RequestHeader("sessionKey") String sessionKey) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(accountService.getAccountDetail(accountId));
		return result;
	}

}
