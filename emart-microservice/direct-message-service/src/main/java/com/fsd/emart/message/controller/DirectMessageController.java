package com.fsd.emart.message.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.entity.DirectMessageInfo;
import com.fsd.emart.message.service.DirectMessageService;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "http://localhost:4200")
@RestController
@RequestMapping("/message")
public class DirectMessageController {
	@Resource
	private DirectMessageService directMessageService;

	@GetMapping("/{id}")
	public JsonResponse getMessageList(@PathVariable("id") String accountId) {
		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		result.setData(directMessageService.getMessageList(accountId));
		return result;
	}

	@PostMapping
	public JsonResponse getManufacturerList(@RequestBody DirectMessageInfo newMessage) {
		directMessageService.postMessage(newMessage);

		JsonResponse result = new JsonResponse();
		result.setStatus(Constants.SUCCESS);
		return result;
	}

}
