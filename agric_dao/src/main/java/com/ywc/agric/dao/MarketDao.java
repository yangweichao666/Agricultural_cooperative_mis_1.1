package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Market;

/**
 * @Author YWC
 * @Date 2021/4/21 16:49
 */
public interface MarketDao {
    Page<Market> findPage(String value);

    Market findById(Integer id);

    void add(Market market);

    void update(Market market);

    void delete(Integer id);
}
