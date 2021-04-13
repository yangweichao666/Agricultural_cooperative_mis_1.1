package com.ywc.agric.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.ywc.agric.constant.MessageConstant;
import com.ywc.agric.dao.MemberDao;
import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
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

    @Override
    public PageResult<Member> findPage(QueryPageBean queryPageBean) {
       //拦截器使用静态方法
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
       //
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"");
        }
        Page<Member> page =memberDao.findPage(queryPageBean.getQueryString());
        PageResult<Member> memberPageResult = new PageResult<Member>(page.getTotal(),page.getResult());
        return memberPageResult;
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);

    }

    @Override
    public void deleteById(int id) throws HealthException {
        //检查次检查项是否被检查组使用
        //检查id是否在t_checkgroup_checkitem表中
        int cout=memberDao.findCountByMemberId(id);
        if (cout<=0){
            //上抛异常
            throw new HealthException(MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        memberDao.deleteById(id);

    }

    @Override
    public Member findById(int id) {
        return memberDao.findById(id);
    }

    @Override
    public void update(Member member) {
        memberDao.edit(member);

    }
}
