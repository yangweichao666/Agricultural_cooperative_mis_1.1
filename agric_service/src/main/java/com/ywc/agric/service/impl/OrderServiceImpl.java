package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.dao.MemberDao;
import com.ywc.agric.dao.OrderDao;
import com.ywc.agric.dao.OrderSettingDao;
import com.ywc.agric.dao.SetmealDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Member;
import com.ywc.agric.pojo.Order;
import com.ywc.agric.pojo.OrderSetting;
import com.ywc.agric.pojo.Setmeal;
import com.ywc.agric.service.OrderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author YWC
 * @Date 2021/4/6 14:56
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SetmealDao setmealDao;

    @Override
    public Order ordersubmit(Map<String, String> orderInfo) throws HealthException {
        //获取日期
        String orderDateStr = orderInfo.get("orderDate");
        //设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = null;
        try {
            orderDate = simpleDateFormat.parse(orderDateStr);
        } catch (ParseException e) {
            throw new HealthException("日期格式错误");
        }
        if (orderDate == null) {
            throw new HealthException("所选日期,不能预约");
        }
        //查询当前状态
        OrderSetting orderSetting = orderSettingDao.findOrderData(orderDate);
        if(orderSetting==null){
            throw new HealthException("当前日期未安排预约容量");
        }
        if (orderSetting.getReservations() >= orderSetting.getNumber()) {
            throw new HealthException("当前日期预约数量已满不能预约");
        }
        //通过手机号查询是否是会员如果不是则注册
        Member member = memberDao.findByTelephone(orderInfo.get("telephone"));
        if (member == null) {
            member=new Member();
            member.setName(orderInfo.get("name"));
            member.setIdCard(orderInfo.get("idCard"));
            member.setPhoneNumber(orderInfo.get("telephone"));
            member.setRegTime(new Date());
            //添加会员 mybatis要返回id
            memberDao.add(member);
        }
        Order order = new Order();
        //设置 订单
        order.setMemberId(member.getId());
        order.setOrderDate(orderDate);
        order.setSetmealId(Integer.valueOf(orderInfo.get("setmealId")));
        //查询订单是否已经存在
        List<Order> orders = orderDao.findByCondition(order);
        if (member != null && orders.size() > 0) {//订单重复报错
            throw new HealthException("提交的订单重复！不能重复预约");
        }
        //没有重复可以进行预约
        //订单类型
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setOrderType(orderInfo.get("orderType"));
        //添加预约订单  //并返回id
        orderDao.add(order);
        //更新预约数量表
        orderSettingDao.updateReservations(orderSetting);

        return order;
    }

    @Override
    public Map<String, Object> findDetailById(Integer id) {

        return orderDao.findById4Detail(id);
    }

    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //判断条件是否存在
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<Order> page=orderDao.findPage(queryPageBean.getQueryString());
        List<Order> orderList = page.getResult();
        //将套餐名称
        //会员名称拼接起来
        ArrayList<Map> orderNewList = new ArrayList<>();
        for (Order order : orderList) {
            Member member = memberDao.findById(order.getMemberId());
            Setmeal setmeal = setmealDao.findById(order.getSetmealId());
            HashMap<String, Object> orderMap = new HashMap<>();
            orderMap.put("id",order.getId());
            orderMap.put("memberId",order.getMemberId());
            if (member!=null)
            orderMap.put("memberName",member.getName());
            orderMap.put("orderStatus",order.getOrderStatus());
            orderMap.put("orderType",order.getOrderType());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date orderDate = order.getOrderDate();
            orderMap.put("orderDate",simpleDateFormat.format(orderDate));
            orderMap.put("setmealId",order.getSetmealId());
            if (setmeal!=null)
            orderMap.put("setmealName",setmeal.getName());
            orderNewList.add(orderMap);
        }
        return new PageResult(page.getTotal(),orderNewList);
    }
}
