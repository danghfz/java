package com.local.util;


import javax.servlet.http.Cookie;

/**
 * @author ��
 * @version 1.0
 * 2022/4/25   21:52
 */
public class CookieUtils {
    /**
     * @param name ���ҵ�Cookie name
     * @param cookies ���ҵ���Դ
     * @return �ҵ�����Cookie,���򷵻�null*/
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
