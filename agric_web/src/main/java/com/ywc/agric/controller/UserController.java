package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.Result;

import com.ywc.agric.service.UserService;
import okhttp3.Request;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;

/**
 * @Author YWC
 * @Date 2021/4/9 18:45
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    UserService userService;
    @PostMapping("/findUser")
    public Result findUser(){

        User loginUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //返回用户名
        String username = loginUser.getUsername();
        com.ywc.agric.pojo.User user = userService.findByUsername(username);
        String userImg=user.getImg();
        HashMap<String,String> userMap=new HashMap<>();
        userMap.put("username", username);
        userMap.put("userimg", userImg);

        // 返回给前端
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,userMap);


    }
}
