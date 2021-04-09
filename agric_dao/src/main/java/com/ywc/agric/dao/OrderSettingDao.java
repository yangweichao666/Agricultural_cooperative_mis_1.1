package com.ywc.agric.dao;



import com.ywc.agric.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/1 9:33
 */
public interface OrderSettingDao {

    /**
     *根据日期查找
     * @param orderDate
     * @return
     */
    OrderSetting findOrderData(Date orderDate);

    /**x
     * 修改
     * @param orderSetting
     */
    void updateNumber(OrderSetting orderSetting);

    /**
     * 添加
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 根据月份查询预约信息
      * @param dateMoth
     * @return
     */
    List<OrderSetting> getOrderSettingMoth(String dateMoth);

    /**
     * 更新已预约人数
     * @param orderSetting
     */
    void updateReservations(OrderSetting orderSetting);
}
