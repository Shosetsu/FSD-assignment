package com.fsd.emart.message.service;

import java.util.List;

import com.fsd.emart.common.entity.DirectMessageInfo;

public interface DirectMessageService {

	public List<DirectMessageInfo> getMessageList(String accountId);

	public void postMessage(DirectMessageInfo message);

}
