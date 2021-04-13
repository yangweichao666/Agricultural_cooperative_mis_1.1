package com.ywc.agric.controller;

import com.github.pagehelper.StringUtil;
import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.constant.RedisMessageConstant;
import com.ywc.agric.entity.Result;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Order;
import com.ywc.agric.service.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/5 20:34
 */
@RestController
@RequestMapping("/order")
public class OrderMobileController {
    @Autowired
    JedisPool jedisPool;
    @Reference

    OrderService orderService;
    @PostMapping("/submit")
    public Result submit(@RequestBody Map<String,String> orderInfo){
        Jedis jedisPoolResource = jedisPool.getResource();

        String telephone = orderInfo.get("telephone");
        String key= RedisMessageConstant.SENDTYPE_ORDER+":"+telephone;
        String validateCode = orderInfo.get("validateCode");

        if (!StringUtil.isEmpty(validateCode)){

            String code = jedisPoolResource.get(key);
            if (code==null){
                return  new Result(false, "验证码失效请重新获取验证码！");
            }

            if (code.equals(validateCode)){
                //验证成功
                //设置预约类型
                orderInfo.put("orderType",Order.ORDERTYPE_WEIXIN );
                // 预约成功页面展示时需要用到id
                Order order = null;
                try {
                    order = orderService.ordersubmit(orderInfo);
                    //发送后删除redis中的，防止重复提交 测试时建议删除
//                    jedisPoolResource.del(key);
                    jedisPoolResource.close();
                } catch (HealthException e) {
                    //处理自定义异常信息
                    return  new Result(false, e.getMessage());

                }

              return   new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS,order);
            }else {
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }

        }
        return new Result(false, MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);


    }
    @PostMapping("/findById")
    public Result findDetailById(Integer id){
        try {
            Map<String,Object> data=orderService.findDetailById(id);
            return new Result(true, MessageConstant.ORDER_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "数据错误");


        }
    }
}
