package com.ywc.agric.service;

import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.CheckItem;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/18 11:19
 */
@Service
public interface CheckItemService {
    /**
     * 查询所有
     * @returnth
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(int id) throws HealthException;

    /**
     * 根据查询检查项
     * @param id
     * @return
     */
    CheckItem findById(int id) throws HealthException;

    void update(CheckItem checkItem);
}
