package com.ywc.agric.service;

import com.ywc.agric.pojo.Message;

/**
 * @Author YWC
 * @Date 2021/4/23 8:37
 */
public interface MessageService {
    /**
     * 发送消息
     * @param id 商品信息id
     * @param message 消息
     */
    void add(Integer id, Message message);
}
