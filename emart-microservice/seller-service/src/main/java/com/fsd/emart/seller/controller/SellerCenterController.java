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
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.seller.service.SellerManagementService;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT }, origins = "http://localhost:4200")
@RestController
@RequestMapping("/seller")
public class SellerCenterController {
	@Resource
	private SellerManagementService sellerService;

	@GetMapping("/checkStatus")
	public JsonResponse isSeller(@RequestHeader("id") String accountId,
			@RequestHeader("sessionKey") String sessionKey) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(sellerService.isSeller(accountId));
		return result;
	}

	@GetMapping("/salesList")
	public JsonResponse getSalesList(@RequestHeader("id") String accountId,
			@RequestHeader("sessionKey") String sessionKey) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(sellerService.getSalesList(accountId));
		return result;
	}

	@GetMapping("/overview")
	public JsonResponse getSalesOverviewByMonth(@RequestParam("date") String dateYm,
			@RequestHeader("id") String accountId, @RequestHeader("sessionKey") String sessionKey) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(sellerService.getSalesOverviewByMonth(dateYm));
		return result;
	}

	@PostMapping("/item")
	public JsonResponse updateItem(@RequestBody GoodInfo info, @RequestHeader("id") String accountId,
			@RequestHeader("sessionKey") String sessionKey) {
		sellerService.saveSalesItem(info);

		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		return result;
	}

	@PutMapping("/item/{itemId}")
	public JsonResponse updateItem(@PathVariable("itemId") String itemId, @RequestParam("status") String status,
			@RequestHeader("id") String accountId, @RequestHeader("sessionKey") String sessionKey) {
		sellerService.changeSalesItemStatus(itemId, status);

		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		return result;
	}
}
