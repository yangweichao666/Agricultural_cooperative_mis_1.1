package com.ywc.agric.service;

import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.News;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/18 11:25
 */
public interface NewsService {
    PageResult<News> findPage(QueryPageBean queryPageBean);

    void add(News news);

    void update(News news);

    News findById(Integer id);

    /**
     * 查询前五条数据
     * @return
     */
    List<News> findNewFive();
}
