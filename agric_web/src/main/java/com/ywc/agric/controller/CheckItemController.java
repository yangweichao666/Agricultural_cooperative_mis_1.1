package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.CheckItem;
import com.ywc.agric.service.CheckItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/18 11:12
 */
@Component
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    @GetMapping("/findAll")
    public Result findAll(){
        //调用服务
        System.out.println("查询全部被调用");
        List<CheckItem> list =checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,new PageResult<CheckItem>(null,list));
    }

    /**
     * 新增添加项
     * @param checkItem
     * @return
     */
    //添加检查项添加的权限控制
//    @PreAuthorize("ITEM_ADD")
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem ){
        try {
            checkItemService.add(checkItem);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult=checkItemService.findPage(queryPageBean);
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(true, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }

    /**
     * 进行删除项
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result deleteById(int id){
        //已经定义了全局异常处理
//        try {
            checkItemService.deleteById(id);
            return  new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);

//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            return  new Result(false,e.getMessage());
//        }
    }

    /**
     * 根据id查询项
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public  Result findById(int id){
      CheckItem checkItem =checkItemService.findById(id);
      return  new Result(true, "查询检查成功",checkItem);
    }

    /**
     * 编辑
     * @param checkItem
     * @return
     */
    @PostMapping("/update")
    public  Result update(@RequestBody CheckItem checkItem){
        checkItemService.update(checkItem);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
}
