package com.fsd.emart.common.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.exception.BizException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = BizException.class)
    public JsonResponse handleException(BizException ex) {

        JsonResponse response = new JsonResponse();
        response.setStatus(Constants.RES_ERROR);
        String[] messageList = new String[] {ex.getMessage()};

        response.setMessageList(messageList);

        return response;
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public JsonResponse handleNotFound(EntityNotFoundException ex) {

        JsonResponse response = new JsonResponse();
        response.setStatus(Constants.RES_TURNBACK);
        String[] messageList = new String[] {"Not found detail"};

        response.setMessageList(messageList);

        return response;
    }

}
