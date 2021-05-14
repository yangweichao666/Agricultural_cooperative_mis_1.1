package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.dao.MessageDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Message;
import com.ywc.agric.service.MessageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

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
        //添加消息
        messageDao.addMe(message);

        Integer message_id = message.getId();
        //添加消息与市场关联表内容
        messageDao.addMM(market_id,message_id);
    }

    @Override
    public PageResult<Message> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //因为要跟据id查所以不需要
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
//            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");

        }
        Integer id = Integer.valueOf(queryPageBean.getQueryString());

        Page<Message> page= messageDao.findPage(id);

        PageResult<Message> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    @Override
    public void add(Message message) {
        //添加消息
        messageDao.addMe(message);

    }
}
