package com.dhf.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   14:52
 */
@Service("userDetailsService")
public class UserService implements UserDetailsService {
    private final String USERNAME = "danghf";
    private final String PASSWORD = "dhf200827";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过username获取一个UserDetails
        //...查数据库，获取账号密码
        //User实现了userDetails接口
        // select password form user where username = username
        //
        String password = new BCryptPasswordEncoder().encode(PASSWORD);
        //权限集合
        List<GrantedAuthority> authorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(USERNAME, password, authorities);
    }
}
