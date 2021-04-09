package com.ywc.agric.service;


import com.ywc.agric.pojo.User;

/**
 * @Author YWC
 * @Date 2021/4/8 9:37
 */
public interface UserService {
    /**
     * 根据用户名查询用户权限新系
     * @param username
     * @return
     */
    User findByUsername(String username);
}
