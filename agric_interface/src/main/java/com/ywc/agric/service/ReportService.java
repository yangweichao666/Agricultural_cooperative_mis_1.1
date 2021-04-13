package com.ywc.agric.service;

import java.util.ArrayList;

/**
 * @Author YWC
 * @Date 2021/4/10 14:58
 */
public interface ReportService {
    /**
     * 根据月份查询会员新增数据
     * @param months
     * @return
     */
    ArrayList<Integer> finCountMemberByMonth(ArrayList<String> months);
}
