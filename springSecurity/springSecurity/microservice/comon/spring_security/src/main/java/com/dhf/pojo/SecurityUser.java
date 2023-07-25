package com.dhf.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   16:54
 */
public class SecurityUser implements UserDetails {
    public User getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void setCurrentUserInfo(User currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    public List<String> getPermissionValueList() {
        return permissionValueList;
    }

    public void setPermissionValueList(List<String> permissionValueList) {
        this.permissionValueList = permissionValueList;
    }

    // 当前登录用户
    private transient User currentUserInfo;
    // 当前权限
    private List<String> permissionValueList;
    public SecurityUser() {
    }

    public SecurityUser(User currentUserInfo) {
        if (currentUserInfo != null){
            this.currentUserInfo = currentUserInfo;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        for (String permission : permissionValueList){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            collection.add(authority);
        }
        return collection;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    // 是否未过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // 是否未锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
