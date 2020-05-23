package com.fsd.emart.common.bean;

import lombok.Data;

@Data
public class JsonResponse {

    private Integer status;

    private String[] messageList;

    private Object data;

}