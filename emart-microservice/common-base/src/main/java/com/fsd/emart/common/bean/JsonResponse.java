package com.fsd.emart.common.bean;

import com.fsd.emart.common.constants.Constants;

import lombok.Data;

@Data
public class JsonResponse {

    private Integer status = Constants.RES_NOTHING;

    private String[] messageList;

    private Object data;

}