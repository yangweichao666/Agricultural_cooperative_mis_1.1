package com.ywc.agric.dao;

import com.github.pagehelper.Page;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.News;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/18 11:31
 */
public interface NewsDao {
    Page<News> findPage(String queryString);

    void add(News news);

    void update(News news);

    News findById(Integer id);

    /**
     * 查询前五条新闻
     * @return
     */
    List<News> findNewFive();
}
