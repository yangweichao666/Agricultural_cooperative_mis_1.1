package com.ywc.agric.service;

import com.ywc.agric.entity.PageResult;
import com.ywc.agric.entity.QueryPageBean;
import com.ywc.agric.exception.HealthException;
import com.ywc.agric.pojo.Member;

import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/7 9:36
 */

public interface MemberService {
    //根据手机号判断是否是会员
    Member findByTelePhone(Map<String, String> loginInfo);

    PageResult<Member> findPage(QueryPageBean queryPageBean);

    /**
     * 添加
     * @param member
     */
    void add(Member member);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById  (int id) throws HealthException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Member findById(int id);

    /**
     * 修改
     * @param member
     */
    void update(Member member);
}
