package com.local.util;


import javax.servlet.http.Cookie;

/**
 * @author 党
 * @version 1.0
 * 2022/4/25   21:52
 */
public class CookieUtils {
    /**
     * @param name 查找的Cookie name
     * @param cookies 查找的资源
     * @return 找到返回Cookie,否则返回null*/
    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (cookies == null || name == null || cookies.length == 0) {
            return null;
        }else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
