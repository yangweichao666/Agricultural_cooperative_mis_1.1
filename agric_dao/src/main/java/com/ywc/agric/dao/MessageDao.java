package com.ywc.agric.dao;

import com.ywc.agric.pojo.Message;

/**
 * @Author YWC
 * @Date 2021/4/23 8:42
 */
public interface MessageDao {

    /**
     * 添加消息内容并返归id
     * @param message
     * @return
     */
     void addMe(Message message);

    /**
     * 添加市场与消息关联表内容
     * @param market_id
     * @param message_id
     */
    void addMM(Integer market_id, Integer message_id);
}
