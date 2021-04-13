package com.ywc.agric.controller;

import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.service.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/12 17:21
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    OrderService orderService;
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult=orderService.findByPage(queryPageBean);
            return new Result(true, "查询订单信息成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询订单信息失败");


        }

    }
}
