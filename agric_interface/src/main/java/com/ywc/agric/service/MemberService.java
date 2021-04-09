package com.ywc.agric.service;

import com.ywc.agric.pojo.Member;

import java.util.Map;

/**
 * @Author YWC
 * @Date 2021/4/7 9:36
 */

public interface MemberService {
    //根据手机号判断是否是会员
    Member findByTelePhone(Map<String, String> loginInfo);
}
