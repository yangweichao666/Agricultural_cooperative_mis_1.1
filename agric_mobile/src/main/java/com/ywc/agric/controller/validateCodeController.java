package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.constant.RedisMessageConstant;
import com.ywc.agric.entity.Result;
import com.ywc.agric.util.ValidateCodeUtils;
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
 * @Date 2021/4/5 19:30
 * 验证码业务
 */
@RestController
@RequestMapping("/validateCode")
public class validateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @PostMapping("/send4Order")
    public Result send4Order(@RequestBody Map orderInfo){
        //获取连接
        Jedis jedisPoolResource = jedisPool.getResource();
        String telephone = (String) orderInfo.get("telephone");
        String key= RedisMessageConstant.SENDTYPE_ORDER+":"+telephone;

//        Object validateCode = orderInfo.get("validateCode");
        String JTelephoneCode = jedisPoolResource.get(key);
        if (JTelephoneCode==null){
            //redis内没有验证码 需要获取
            Integer code = ValidateCodeUtils.generateValidateCode(6);
            //调用阿里短信的方法
            try {
                //因为还没有注册所以先注释
               // SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code+"");
                //设置验证码过期时间
                jedisPoolResource.setex(key, 10*600, code+"");
                return  new Result(false, MessageConstant.SEND_VALIDATECODE_SUCCESS);

            } catch (Exception e) {

                e.printStackTrace();
                return  new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
            }

        }
//        if ()
        return new Result(false, "验证码已发送,请不要重新发送");


    }
    @PostMapping("/send4Login")
    public Result send40login(String telephone){
        //获取连接
        Jedis jedisPoolResource = jedisPool.getResource();;
        String key= RedisMessageConstant.SENDTYPE_ORDER+":"+telephone;


        String JTelephoneCode = jedisPoolResource.get(key);
        if (JTelephoneCode==null){
            //redis内没有验证码 需要获取
            Integer code = ValidateCodeUtils.generateValidateCode(6);
            //调用阿里短信的方法
            try {
                //因为还没有注册所以先注释
            //    SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code+"");
                //设置验证码过期时间
                jedisPoolResource.setex(key, 10*600, code+"");
                return  new Result(false, MessageConstant.SEND_VALIDATECODE_SUCCESS);

            } catch (Exception e) {

                e.printStackTrace();
                return  new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
            }

        }
//        if ()
        return new Result(false, "验证码已发送,请不要重新发送");


    }

}
