package com.dhf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhf.pojo.SecurityUser;
import com.dhf.entity.User;
import com.dhf.service.PermissionService;
import com.dhf.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   18:42
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("username",username));
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }else {
            com.dhf.pojo.User curUser = new com.dhf.pojo.User();
            BeanUtils.copyProperties(user,curUser);
            SecurityUser securityUser = new SecurityUser();
            securityUser.setCurrentUserInfo(curUser);
            List<String> strings = permissionService.selectPermissionValueByUserId(user.getId());
            securityUser.setPermissionValueList(strings);
            return securityUser;
        }
    }
}
