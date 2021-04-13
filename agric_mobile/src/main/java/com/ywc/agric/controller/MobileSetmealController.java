package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.Setmeal;
import com.ywc.agric.service.SetmealService;
import com.ywc.agric.util.QiNiuUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author YWC
 * @Date 2021/4/5 10:27
 */
@RestController
@RequestMapping("/setmeal")
public class MobileSetmealController {
    @Reference
    SetmealService setmealService;

    /**
     * 查询所有套餐信息
     * @return
     */
    @PostMapping("/getSetmeal")
    public Result getSetmeal(){
        try {
            List<Setmeal> setmealList= setmealService.findAll();
            HashMap data=new ManagedMap();
            data.put("URL", QiNiuUtils.DOMAIN);
            data.put("setmealList", setmealList);
            return  new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false, MessageConstant.QUERY_SETMEALLIST_FAIL);

        }
    }
    /**
     * 根据id查询详情 三表查询
     */
    @PostMapping("/findById")
    public Result findById(Integer id){
        try {
            Setmeal setmeal= setmealService.findById(id);
            HashMap data=new ManagedMap();
            data.put("URL", QiNiuUtils.DOMAIN);
            data.put("setmeal", setmeal);
            return  new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);

        }


    }

}
