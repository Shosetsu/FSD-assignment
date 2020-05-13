package com.fsd.emart.message.service.impl;

import javax.annotation.Resource;

import com.fsd.emart.common.dao.DirectMessageDao;
import com.fsd.emart.common.entity.DirectMessageInfo;
import com.fsd.emart.message.service.DirectMessageService;

public class DirectMessageServiceImpl implements DirectMessageService {

	@Resource
	private DirectMessageDao directMessageDao;

	@Override
	public DirectMessageInfo[] getMessageList(String accountId) {
		// TODO
		return null;
	}

	@Override
	public void postMessage(DirectMessageInfo message) {
		// TODO

	}

}
