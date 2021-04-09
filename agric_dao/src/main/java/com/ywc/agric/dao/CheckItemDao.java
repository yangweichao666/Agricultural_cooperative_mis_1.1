package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.pojo.CheckItem;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/18 11:34
 */

public interface CheckItemDao {
    /**
     * 查询所有
     * @return
     */
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    Page<CheckItem> findByCondition(String queryString);

    void deleteById(int id);

    int findCountByCheckItem(int id);

    CheckItem findById(int id);

    void update(CheckItem checkItem);
}
