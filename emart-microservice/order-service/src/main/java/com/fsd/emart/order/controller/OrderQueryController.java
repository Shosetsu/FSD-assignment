package com.fsd.emart.order.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.order.service.OrderQueryService;

@CrossOrigin(methods = RequestMethod.GET, origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderQueryController {
	@Resource
	private OrderQueryService orderService;

	@GetMapping("/all")
	public JsonResponse getOrderList(@RequestHeader("id") String accountId,
			@RequestParam(defaultValue = "sIndex") Integer queryStartIndex) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(orderService.queryOrder(accountId, queryStartIndex));
		return result;
	}

	@GetMapping("/detail/{orderId}")
	public JsonResponse getOrderDetail(@PathVariable("orderId") String orderId) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(orderService.queryOrderDetail(orderId));
		return result;
	}

}
