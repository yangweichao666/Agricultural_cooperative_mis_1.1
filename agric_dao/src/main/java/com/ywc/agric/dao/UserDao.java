package com.ywc.agric.dao;


import com.ywc.agric.pojo.User;

/**
 * @Author YWC
 * @Date 2021/4/8 9:43
 */
public interface UserDao {
    /**
     * 根据用户名查询用户权限信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
