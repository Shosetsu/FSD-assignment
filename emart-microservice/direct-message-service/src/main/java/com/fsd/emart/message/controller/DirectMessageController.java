package com.fsd.emart.message.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.emart.common.bean.JsonResponse;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.entity.DirectMessageInfo;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.common.util.StringUtil;
import com.fsd.emart.message.service.DirectMessageService;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "http://localhost:4200")
@RestController
@RequestMapping("/message")
public class DirectMessageController {
    @Resource
    private DirectMessageService dmService;

    @Resource
    private AuthUtil authUtil;

    @GetMapping("/{id}")
    public JsonResponse getMessageList(@PathVariable("id") String accountId,
        @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(accountId, sessionKey);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        result.setData(dmService.getMessageList(accountId));
        return result;
    }

    @PostMapping
    public JsonResponse postMessage(@RequestBody DirectMessageInfo newMessage,
        @RequestHeader("accountId") String accountId, @RequestHeader("sessionKey") String sessionKey) {
        // Authorization Check
        authUtil.froceCheck(accountId, sessionKey);

        // Check Send to id
        if (StringUtil.isEmpty(newMessage.getSendto()) || !authUtil.isExistAccountId(newMessage.getSendto())) {
            throw new ApplicationException("Invalid User Name.");
        }

        // fix the message info
        newMessage.setMsgId("");
        newMessage.setSendby(accountId);
        newMessage.setCreateTime(null);

        dmService.postMessage(newMessage);

        JsonResponse result = new JsonResponse();
        result.setStatus(Constants.RES_NOTHING);
        return result;
    }

}
