package com.ywc.agric.controller;

import com.github.pagehelper.StringUtil;
import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.constant.RedisMessageConstant;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Member;
import com.ywc.agric.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/7 9:06
 */
@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    JedisPool jedisPool;
    @Reference
    MemberService memberService;

    @PostMapping("/check")
    public Result login(@RequestBody Map<String,String> loginInfo, HttpServletResponse res){
        Jedis jedisPoolResource = jedisPool.getResource();
        String telePhone = loginInfo.get("telephone");
        String validateCode=loginInfo.get("validateCode");
        String telephoneKey= RedisMessageConstant.SENDTYPE_LOGIN+":"+telePhone;

        if (!StringUtil.isEmpty(validateCode)){

            String code = jedisPoolResource.get(telephoneKey);
            if (code==null){
                return  new Result(false, "验证码失效请重新获取验证码！");
            }


            if (code.equals(validateCode)){
                    //验证成功
                    //判断是否是会员，不是则注册，并返回
                    Member member=memberService.findByTelePhone(loginInfo);
                    //发送后删除redis中的，防止重复提交 测试时建议删除
//                    jedisPoolResource.del(key);
                    jedisPoolResource.close();

                    //设置Cookie 跟踪记录手机号
      
                // 跟踪记录的手机号码，代表着会员
                Cookie cookie = new Cookie("login_member_telephone",telePhone);
                cookie.setMaxAge(30*24*60*60); // 存1个月
                cookie.setPath("/"); // 访问的路径 根路径下时 网站的所有路径 都会发送这个cookie
                res.addCookie(cookie);
                return   new Result(true, MessageConstant.LOGIN_SUCCESS);
            }else {

                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }

        }
        return new Result(false, MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);


    }
}
