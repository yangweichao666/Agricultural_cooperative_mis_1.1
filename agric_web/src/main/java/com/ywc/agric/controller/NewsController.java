package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Contract;
import com.ywc.agric.pojo.News;
import com.ywc.agric.service.NewsService;
import com.ywc.agric.util.QiNiuUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/18 11:23
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Reference
    NewsService newsService;

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<News> pageResult = newsService.findPage(queryPageBean);


        //返回分页数据和图片域名链接
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("pageResult", pageResult);
        stringObjectHashMap.put("domain", QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_CONTRACT_SUCCESS, stringObjectHashMap);
    }

    @PostMapping("/add")
    public Result add(@RequestBody News news){
        newsService.add(news);
        return new Result(true, "添加新闻成功");

    }
    @PostMapping("/update")
    public Result update(@RequestBody News news){
        newsService.update(news);
        return new Result(true, "修改新闻成功");

    }
    @PostMapping("/findById")
    public Result findById(Integer id){
        News news=  newsService.findById(id);
        return new Result(true, "查询新闻成功",news);

    }

    @PostMapping("/findNewFive")
    public Result  findNewFive() {
        List<News> formData = newsService.findNewFive();


        //返回分页数据和图片域名链接
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("rows", formData);
        stringObjectHashMap.put("domain", QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.QUERY_CONTRACT_SUCCESS, stringObjectHashMap);
    }

}
