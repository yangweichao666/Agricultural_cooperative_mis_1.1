package com.ywc.agric.service.impl;

import com.ywc.agric.dao.MemberDao;
import com.ywc.agric.service.ReportService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @Author YWC
 * @Date 2021/4/10 14:59
 */
@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {
    @Autowired
    MemberDao memberDao;
    @Override
    public ArrayList<Integer> finCountMemberByMonth(ArrayList<String> months) {
        ArrayList<Integer> countMember = new ArrayList<>();
        Integer memberCountAfterDate=0;
        for (String month : months) {
            month=month+"-01";

             memberCountAfterDate = memberDao.findMemberCountBeforeDate(month);
            countMember.add(memberCountAfterDate);

        }

        return countMember;
    }
}
