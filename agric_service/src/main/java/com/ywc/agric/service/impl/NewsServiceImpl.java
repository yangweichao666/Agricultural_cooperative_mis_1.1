package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.dao.NewsDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.News;
import com.ywc.agric.service.NewsService;
import com.ywc.agric.util.QiNiuUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @Author YWC
 * @Date 2021/4/18 11:27
 */
@Service(interfaceClass = NewsService.class)
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsDao newsDao;
    @Override
    public PageResult<News> findPage(QueryPageBean queryPageBean) {
        //拦截器使用静态方法
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<News> page =newsDao.findPage(queryPageBean.getQueryString());

        PageResult<News> memberPageResult = new PageResult<News>(page.getTotal(),page.getResult());
        return memberPageResult;
    }

    @Override
    public void add(News news) {
        newsDao.add(news);
    }

    @Override
    public void update(News news) {
        newsDao.update(news);
    }

    @Override
    public News findById(Integer id) {
        return newsDao.findById(id);
    }
}
