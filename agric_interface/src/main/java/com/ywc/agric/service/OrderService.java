package com.ywc.agric.service;

import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Order;

import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/5 20:53
 */
public interface OrderService {
    Order ordersubmit(Map<String, String> orderInfo) throws HealthException;

    /**
     * 根据id查询根据预约id查询预约信息，包括体检人信息、套餐信息
     * @param id
     * @return
     */
    Map<String, Object> findDetailById(Integer id);

    /**
     * 订单信息分页查询
     * @return
     * @param queryPageBean
     */
    PageResult findByPage(QueryPageBean queryPageBean);
}
