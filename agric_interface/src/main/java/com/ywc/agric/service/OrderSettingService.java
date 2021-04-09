package com.ywc.agric.service;

import com.ywc.agric.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/1 9:11
 */
public interface OrderSettingService {


    /**
     * 导入
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList);

    /**
     * 根据月份获取预约信息
     *
     * @param month
     * @return
     */

    List<Map> getOrderSettingByMonth(String month);

    /**
     * 修改预约数据
     * @param orderSetting
     */
    void updateMaxOrderSetting(OrderSetting orderSetting) ;
}
