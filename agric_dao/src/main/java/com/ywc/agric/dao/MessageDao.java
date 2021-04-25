package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Message;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 分页查询
     * @param
     * @return
     */
    Page<Message> findPage(String id);

    Page<Message> findPage(@Param("id") Integer id);
}
