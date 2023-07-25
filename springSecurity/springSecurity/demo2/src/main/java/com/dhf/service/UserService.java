package com.dhf.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * 2022/7/12   15:31
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.getUserByUsername(username);
//        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username",username);
//        User user1 = mapper.selectOne(wrapper);
        if (user!=null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(user.getPassword());
            List<GrantedAuthority> authorities;
            if (user.getUsername().equals("admin")){
                // 赋予 admin 权限和  admin角色 ，角色需要 ROLE_前缀
                 authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_admin");
            }else {
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
            }

            return new org.springframework.security.core.userdetails.User(
                    username,encode,authorities
            );
        }else {
            throw new UsernameNotFoundException("用户未找到");
        }
    }
}
