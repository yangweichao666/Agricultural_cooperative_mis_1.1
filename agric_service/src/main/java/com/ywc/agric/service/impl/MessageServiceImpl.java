package com.ywc.agric.service.impl;

import com.ywc.agric.dao.MessageDao;
import com.ywc.agric.pojo.Message;
import com.ywc.agric.service.MessageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author YWC
 * @Date 2021/4/23 8:37
 */
@Service(interfaceClass = MessageService.class)
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;
    @Override
    public void add(Integer market_id, Message message) {
       messageDao.addMe(message);
        Integer message_id = message.getId();
        messageDao.addMM(market_id,message_id);
    }
}
