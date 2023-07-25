package com.dhf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhf.mapper.UserMapper;
import com.dhf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   15:05
 */
@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if (user!=null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(user.getPassword());
            //权限
            List<GrantedAuthority> authorities;
            if (username.equals("admin")){
                // 角色
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
            }else {
                // 权限
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("LUCY");
            }
            return new org.springframework.security.core.userdetails.User(
                    username,
                    encode,
                    authorities
            );
        }
        return null;
    }
}
