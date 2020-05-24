package com.fsd.emart.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.emart.common.dao.DirectMessageDao;
import com.fsd.emart.common.entity.DirectMessageInfo;
import com.fsd.emart.message.service.DirectMessageService;

@Transactional
@Service
public class DirectMessageServiceImpl implements DirectMessageService {

    @Resource
    private DirectMessageDao directMessageDao;

    @Override
    public List<DirectMessageInfo> getMessageList(String accountId) {
        return directMessageDao.findBySendbyOrSendtoOrderByCreateTimeDesc(accountId, accountId);
    }

    @Override
    public void postMessage(DirectMessageInfo message) {
        directMessageDao.saveAndFlush(message);
    }

}
