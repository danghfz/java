package com.local.i18n;

import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   21:04
 */
public class I18nTest {
    @Test
    public void testLocale(){
        Locale locale = Locale.getDefault();//默认的语言
        System.out.println(locale);//zh_CN
//        Locale[] availableLocales = Locale.getAvailableLocales();//获取可用的语言
//        for (Locale l:availableLocales){
//            System.out.println(l);
//        }
        System.out.println(Locale.CHINA);//获取中文中国的locale对象 zh_CN
        System.out.println(Locale.US);//获取英文美国的locale对象 en_US

    }
    @Test
    public void testI18n(){
        //得到locale对象
        Locale locale = Locale.US;
        //i18n_zh_CN.properties,i18n_en_US.properties选前面的前缀
        //通过知道的basename和locale读取相应的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);
        String username = bundle.getString("username");
        System.out.println(username);

    }
}
