package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.Result;
import com.ywc.agric.pojo.OrderSetting;
import com.ywc.agric.service.OrderSettingService;
import com.ywc.agric.util.POIUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/1 8:44
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettiongController {
    @Reference
    OrderSettingService orderSettingService;

    @PostMapping("/upload")
    public Result upload(MultipartFile excelFile) {
        try {
            List<String[]> strings = POIUtils.readExcel(excelFile);
            //定义一个预约表类型的容器
            List<OrderSetting> orderSettingList = new ArrayList<OrderSetting>();
            SimpleDateFormat sdf = new SimpleDateFormat(POIUtils.DATE_FORMAT);
            Date parse = null;
            OrderSetting os = null;
            for (String[] dataArr : strings) {
                //代表一行
                parse = sdf.parse(dataArr[0]);
                int number = Integer.valueOf(dataArr[1]);
                //将日期和数量放入实体对象
                os = new OrderSetting(parse, number);
                orderSettingList.add(os);
            }
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException | ParseException e) {
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }

    /**
     * 获取指定月份的预约信息
     *
     * @param month
     * @return
     */
    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month) {
        try {
            List<Map> orderSettingByMonth = orderSettingService.getOrderSettingByMonth(month);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, orderSettingByMonth);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }

    }

    @PostMapping("/updateMaxOrderSetting")
    public  Result updateMaxOrderSetting(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.updateMaxOrderSetting(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);


        }

    }
}
