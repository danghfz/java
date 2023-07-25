package com.dhf.pojo;

import java.io.Serializable;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   16:54
 */
public class User implements Serializable {
    private String username;
    private String password;
    // 昵称
    private String nickname;
    // 用户头像
    private String salt;
    // 用户签名
    private String token;

    public User() {
    }

    public User(String username, String password, String nickname, String salt, String token) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.salt = salt;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
