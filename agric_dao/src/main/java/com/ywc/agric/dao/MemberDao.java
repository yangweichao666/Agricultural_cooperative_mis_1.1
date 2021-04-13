package com.ywc.agric.dao;


import com.github.pagehelper.Page;
import com.ywc.agric.pojo.Member;

import java.util.List;

public interface MemberDao {
    /**
     * 查询所有
     * @return
     */
     List<Member> findAll();

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Member> selectByCondition(String queryString);

    /**
     * 添加
     *
     * @param member
     */
    void add(Member member);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    Member findById(Integer id);

    /**
     * 根据手机号查询会员
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 编辑会员
     * @param member
     */
    void edit(Member member);

    /**
     * 根据日期统计会员数，统计指定日期之前的会员数
     *
     * @param date
     * @return
     */
    Integer findMemberCountBeforeDate(String date);

    /**
     * 根据日期统计会员数
     * @param date
     * @return
     */
    Integer findMemberCountByDate(String date);

    /**
     * 指定日期之后的会员数
     *
     * @param date
     * @return
     */
    Integer findMemberCountAfterDate(String date);

    /**
     * 总会员数
     * @return
     */
    Integer findMemberTotalCount();

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Member> findPage(String queryString);

    /**
     * 根据id查询是否存在
     * @param id
     * @return
     */
    int findCountByMemberId(int id);
}
