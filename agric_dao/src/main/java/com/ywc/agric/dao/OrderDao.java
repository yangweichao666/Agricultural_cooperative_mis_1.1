package com.ywc.agric.dao;





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
     * 根据预约id查询预约信息，包括体检人信息、套餐信息
     * @param id
     * @return
     */
    public Map findById4Detail(Integer id);
    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();
}
