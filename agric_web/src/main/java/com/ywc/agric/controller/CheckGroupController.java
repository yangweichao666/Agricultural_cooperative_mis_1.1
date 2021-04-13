package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.CheckGroup;
import com.ywc.agric.service.CheckGroupService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author YWC
 * @Date 2021/3/28 15:53
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    CheckGroupService checkGroupService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup ,Integer[] checkItemIds){
        checkGroupService.add(checkGroup,checkItemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    };
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup> pageResult=checkGroupService.findPage(queryPageBean);
        return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 编辑中根据id查询品类组
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public Result findById(Integer id){
        CheckGroup checkGroup=checkGroupService.findByid(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    /**
     * 根据品类组id查询品类组关系
     * @param id
     * @return
     */
    @PostMapping("findCheckGroupAndCheckItemIds")
    public  Result findCheckGroupAndCheckItemIds(Integer id){
        Integer[] checkItemIds=checkGroupService.findCheckGroupAndCheckItemIds(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
    }

    /**
     * 修改品类组
     * @param checkGroup
     * @param checkItemIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup ,Integer[] checkItemIds){
        checkGroupService.update(checkGroup,checkItemIds);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    };

    @PostMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroups=checkGroupService.findAll();
        return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroups);
    }
}
