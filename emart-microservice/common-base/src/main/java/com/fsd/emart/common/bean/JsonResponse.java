package com.fsd.emart.common.bean;

import lombok.Data;

@Data
public class JsonResponse {

    private String status;

    private String[] messageList;

    private Object data;

}