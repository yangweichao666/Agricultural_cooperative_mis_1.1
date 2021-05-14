package com.ywc.agric.security;

import com.ywc.agric.pojo.Permission;
import com.ywc.agric.pojo.Role;
import com.ywc.agric.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author YWC
 * @Date 2021/4/8 9:25
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    UserService userService;
    //重写此方法

    /**
     * 根据登录用户信息 获取用户信息，用户角色，用户权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据登陆用户名称查询用户权限信息
        //t_user -> t_user_role -> t_role -> t_role_permission -> t_permission
        //找出用户所拥有的角色，及角色下所拥有的权限集合
        //User.roles(角色集合).permissions(权限集合)
        com.ywc.agric.pojo.User user = userService.findByUsername(username);
        //查询是否存在此用户，存在则进行 查找权限
        if (null != user) {

            // 用户名
            // 密码
            String password = user.getPassword();
            // 用户权限集合
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            // 授权

            // 用户所拥有的角色
            SimpleGrantedAuthority sai = null;
            Set<Role> roles = user.getRoles();
            if (null != roles) {
                for (Role role : roles) {
                    //keyword ROLE_ADMIN
                    //将来支持角色访问控制 hasRole   hasAnyRole
                    // 角色用关键字, 授予角色     会自动被加上 ROEL_角色名
                    sai = new SimpleGrantedAuthority(role.getKeyword());

                    authorities.add(sai);
                    // 权限, 角色下的所有权限
                    Set<Permission> permissions = role.getPermissions();
                    //角色的权限不为空后授予
                    if (null != permissions) {
                        for (Permission permission : permissions) {
                            // 授予权限
                            sai = new SimpleGrantedAuthority(permission.getKeyword());
                            authorities.add(sai);
                        }
                    }
                }
            }
            //返回登录用户信息给security，会保存到session中（只需查询数据库中用户的名称，密码，权限，返给security框架即可 ）
            return new org.springframework.security.core.userdetails.User(username, password, authorities);

        }
        return null;
    }
}
