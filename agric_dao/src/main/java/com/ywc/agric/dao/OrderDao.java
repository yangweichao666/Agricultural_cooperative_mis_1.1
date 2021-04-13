package com.ywc.agric.dao;





import com.github.pagehelper.Page;
import com.ywc.agric.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(Order order);

    /**
     * 根据条件查询订单
     * @param order
     * @return
     */
    public List<Order> findByCondition(Order order);

    /**
     * 根据预约id查询预约信息，包括会员信息、套餐信息
     * @param id
     * @return
     */
    public Map findById4Detail(Integer id);

    /**
     *   根据日期统计预约数
     * @param date
     * @return
     */
    public Integer findOrderCountByDate(String date);

    /**
     * 根据日期统计预约数，统计指定日期之后的预约数
     * @param date
     * @return
     */
    public Integer findOrderCountAfterDate(String date);

    /**
     * 根据日期统计到订单完成数
     * @param date
     * @return
     */
    public Integer findVisitsCountByDate(String date);

    /**
     * 根据日期统计订单完成数，统计指定日期之后的
     * @param date
     * @return
     */
    public Integer findVisitsCountAfterDate(String date);

    /**
     * 热门套餐，查询前5条
     * @return
     */
    public List<Map> findHotSetmeal();

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Order> findPage(String queryString);
}
