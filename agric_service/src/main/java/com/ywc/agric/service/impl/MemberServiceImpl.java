package com.ywc.agric.service.impl;

import com.ywc.agric.dao.MemberDao;
import com.ywc.agric.pojo.Member;
import com.ywc.agric.service.MemberService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/7 9:38
 */
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;


    @Override
    public Member findByTelePhone(Map<String, String> loginInfo) {
        Member member = memberDao.findByTelephone(loginInfo.get("telePhone"));
        if (member==null){
            member=new Member();
            member.setName(loginInfo.get("name"));
            member.setIdCard(loginInfo.get("idCard"));
            member.setPhoneNumber(loginInfo.get("telephone"));
            member.setRegTime(new Date());
            //添加会员 mybatis要返回id
            memberDao.add(member);
        }
        return member;

    }
}
