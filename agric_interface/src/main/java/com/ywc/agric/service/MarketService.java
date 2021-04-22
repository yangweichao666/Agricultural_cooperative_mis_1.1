package com.ywc.agric.service;

import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Market;

/**
 * @Author YWC
 * @Date 2021/4/21 16:41
 */
public interface MarketService {
    PageResult<Market> findPage(QueryPageBean queryPageBean);

    Market findById(Integer id);

    /**
     *
     * 发布产品信息
     * @param market
     */
    void add(Market market) throws HealthException;

    void update(Market market);

    void delete(Integer id);
}
