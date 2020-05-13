package com.fsd.emart.purchase.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.purchase.bean.PurchaseItemInfo;
import com.fsd.emart.purchase.service.PurchaseService;

@CrossOrigin(methods = RequestMethod.POST, origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class PurchaseController {
	@Resource
	private PurchaseService purchaseService;

	@PostMapping("/purchase")
	public JsonResponse getCartList(@RequestBody PurchaseItemInfo[] purchaseList,
			@RequestHeader("sessionKey") String sessionKey) {
		purchaseService.purchase(purchaseList);

		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		return result;
	}

}
