package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.News;

/**
 * @Author YWC
 * @Date 2021/4/18 11:31
 */
public interface NewsDao {
    Page<News> findPage(String queryString);

    void add(News news);

    void update(News news);

    News findById(Integer id);
}
