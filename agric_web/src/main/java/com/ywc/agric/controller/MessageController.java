package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Message;
import com.ywc.agric.service.MessageService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result add(Integer id, @RequestBody Message message) {
         messageService.add(id, message);

        return new Result(true, "发送消息成功");

    }
    @PostMapping("/add2")
    public Result add(@RequestBody Message message) {

            messageService.add(message);

        return new Result(true, "发送消息成功");

    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Message> pageResult=messageService.findPage(queryPageBean);
        return new Result(true, "查询消息成功",pageResult);
    }
}
