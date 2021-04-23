package com.ywc.agric.controller;

import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Message;
import com.ywc.agric.service.MessageService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YWC
 * @Date 2021/4/23 8:30
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Reference
    MessageService messageService;
    @PostMapping("/add")
    public Result add(Integer id, @RequestBody Message message){
        if (id!=null)
        messageService.add(id,message);
        return new Result(true, "发送消息成功");


    }
}
