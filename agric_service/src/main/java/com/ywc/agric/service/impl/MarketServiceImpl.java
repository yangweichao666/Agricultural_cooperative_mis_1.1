package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.dao.MarketDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Market;
import com.ywc.agric.service.MarketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @Author YWC
 * @Date 2021/4/21 16:42
 */
@Service(interfaceClass = MarketService.class)
public class MarketServiceImpl implements MarketService {
    @Autowired
    MarketDao marketDao;
    @Override
    public PageResult<Market> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");

        }
       Page<Market> page= marketDao.findPage(queryPageBean.getQueryString());
        PageResult<Market> pageResult = new PageResult(page.getTotal(), page.getResult());

        return pageResult;
    }

    @Override
    public Market findById(Integer id) {
        return marketDao.findById(id);
    }

    @Override
    public void add(Market market) throws HealthException{
        if (market.getItemId()!=null){
            marketDao.add(market);
        }else {
            throw new HealthException("所选的项不能为空！");
        }


    }

    @Override
    public void update(Market market) {
        if (market.getItemId()!=null){
            marketDao.update(market);
        }else {
            throw new HealthException("所选的项不能为空！");
        }

    }

    @Override
    public void delete(Integer id) {
        marketDao.delete(id);
    }
}
