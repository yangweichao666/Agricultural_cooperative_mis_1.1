package com.ywc.agric.controller;

import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.entity.Result;
import com.ywc.agric.service.ReportService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author YWC
 * @Date 2021/4/10 14:34
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Reference
    ReportService reportService;
    @GetMapping("/getMemberReport")
    public Result getMemberReport(){
        //定义月份数组
        ArrayList<String> queryMonths = new ArrayList<>();
        ArrayList<String> months = new ArrayList<>();
        //创建日历对象
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        //因为有的月没有31号
        calendar.add(Calendar.MONTH,+1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i=0;i<12;i++){
            //返回月份数据时使用
            Date time = calendar.getTime();
            String month = sdf.format(time);
            months.add(month);
            calendar.add(Calendar.MONTH,1);
            //查询时月份会员数使用会多一个月
            Date querytime = calendar.getTime();
            String querymonth = sdf.format(querytime);
            queryMonths.add(querymonth);

        }
        //查询当前上个月的会员总数量
        ArrayList<Integer> memberCount= reportService.finCountMemberByMonth(queryMonths);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("months", months);
        hashMap.put("memberCount", memberCount);
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,hashMap);

    }
}
