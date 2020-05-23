package com.fsd.emart.common.bean;

import com.fsd.emart.common.constants.Constants;

import lombok.Data;

@Data
public class JsonResponse {

    public JsonResponse() {
        this.status = Constants.RES_NOTHING;
    }

    private Integer status;

    private String[] messageList;

    private Object data;

}