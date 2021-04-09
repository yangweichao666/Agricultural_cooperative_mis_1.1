package com.ywc.agric.service.impl;


import com.ywc.agric.dao.OrderSettingDao;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.OrderSetting;
import com.ywc.agric.service.OrderSettingService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @Author YWC
 * @Date 2021/4/1 9:12
 */

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;
    @Override
    public void add(List<OrderSetting> orderSettingList) {
        //遍历
        for (OrderSetting orderSetting : orderSettingList) {
            OrderSetting orderSettingInDB=orderSettingDao.findOrderData(orderSetting.getOrderDate());
            System.out.println(orderSetting.getOrderDate());
            //判断是否存在日期重合
            if (orderSettingInDB !=null){
                // 存在则判断已预约数是否超过要修改的
                if (orderSettingInDB.getReservations()>orderSetting.getNumber()){
                    // 如果超过包自定义错误
                    throw new HealthException("导入到表每日容量以低于预约数");
                }else { //没超过则今行更新
                   orderSettingDao.updateNumber(orderSetting);
                }

            }else{
                //不存在则添加一行
                orderSettingDao.add(orderSetting);
            }


        }

    }

    @Override
    public List<Map> getOrderSettingByMonth(String month) {
        //设置查询map dateBegin 月份开始 date月份结束
//        String dateBegin=month+"-01";
//        String dateEnd=month+"-30";
//        Map<String,String> dateMap=new HashMap<>();
//        dateMap.put("dateBegin", dateBegin);
//        dateMap.put("dateEnd", dateEnd);
     //放置有闰月
        String dataMonth=month+"-%";
        //查询当前月份
        List<OrderSetting> list = orderSettingDao.getOrderSettingMoth(dataMonth);
        List<Map> dataList=new ArrayList<Map>();
        //将list<OrderSting> 转换成List<Map>
        for (OrderSetting itme : list) {
            Map orderSettingMap=new HashMap();
            Date orderDate = itme.getOrderDate();
            int number = itme.getNumber();
            int reservations = itme.getReservations();
            orderSettingMap.put("date", orderDate.getDate());
            orderSettingMap.put("number", number);
            orderSettingMap.put("reservations", reservations);
            dataList.add(orderSettingMap);
        }
        return dataList;
    }

    /**
     * 日历上直接修改
     * @param orderSetting
     */
    @Override
    public void updateMaxOrderSetting(OrderSetting orderSetting) {
//        SimpleDateFormat formatter = new SimpleDateFormat(POIUtils.DATE_FORMAT);
//        String dateString = formatter.format(orderSetting);
            OrderSetting orderSettingInDB=orderSettingDao.findOrderData(orderSetting.getOrderDate());
            //判断是否存在日期重合
            if (orderSettingInDB !=null){
                // 存在则判断已预约数是否超过要修改的
                if (orderSettingInDB.getReservations()>orderSetting.getNumber()){
                    // 如果超过包自定义错误
                    throw new HealthException("导入到表每日容量以低于预约数");
                }else { //没超过则今行更新
                    orderSettingDao.updateNumber(orderSetting);
                }

            }else{
                //不存在则添加一行
                orderSettingDao.add(orderSetting);
            }


        }


}
