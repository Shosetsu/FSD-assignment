package com.fsd.emart.message.service;

import com.fsd.emart.common.entity.DirectMessageInfo;

public interface DirectMessageService {

	public DirectMessageInfo[] getMessageList(String accountId);

	public void postMessage(DirectMessageInfo message);

}
