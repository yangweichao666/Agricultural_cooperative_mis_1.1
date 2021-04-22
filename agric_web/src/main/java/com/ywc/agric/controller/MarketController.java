package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Market;
import com.ywc.agric.service.MarketService;
import com.ywc.agric.util.QiNiuUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.HashMap;

/**
 * @Author YWC
 * @Date 2021/4/21 16:34
 */
@RestController
@RequestMapping("/market")
public class MarketController {
    @Reference
    MarketService marketService;
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Market> pageResult = marketService.findPage(queryPageBean);


        //返回分页数据和图片域名链接
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("pageResult", pageResult);
        stringObjectHashMap.put("domain", QiNiuUtils.DOMAIN);
        return new Result(true, "查询商品成功", stringObjectHashMap);
        
    }
    @PostMapping("/findById")
    public  Result findById(Integer id){
        Market market=marketService.findById(id);
        return new Result(true, "根据id查询上成功",market);
    }
    @PostMapping("/add")
    public Result add(@RequestBody Market market){
        try {
            marketService.add(market);
            return new Result(true, "添加产品发布！");
        } catch (HealthException e) {
            e.printStackTrace();
            return new Result(true, e.getMessage());
        }
    }
    @PostMapping("/update")
    public Result update(@RequestBody Market market ){
        marketService.update(market);
        return  new Result(true, "修改产品新系成功");

    }
    @PostMapping("/deleteById")
    public Result delete(Integer id){
        marketService.delete(id);
        return new Result(true, "删除单条产品成功!");
    }
}
