package com.fsd.emart.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.exception.BizException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value = BizException.class)
	public JsonResponse handleException(BizException ex) {

		JsonResponse response = new JsonResponse();
		response.setStatus(Constants.FAILURE);
		String[] messageList = new String[] { ex.getMessage() };
		
		response.setMessageList(messageList);

		return response;
	}
}
