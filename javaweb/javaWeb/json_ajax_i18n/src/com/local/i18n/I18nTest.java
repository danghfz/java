package com.local.i18n;

import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author ��
 * @version 1.0
 * 2022/5/5   21:04
 */
public class I18nTest {
    @Test
    public void testLocale(){
        Locale locale = Locale.getDefault();//Ĭ�ϵ�����
        System.out.println(locale);//zh_CN
//        Locale[] availableLocales = Locale.getAvailableLocales();//��ȡ���õ�����
//        for (Locale l:availableLocales){
//            System.out.println(l);
//        }
        System.out.println(Locale.CHINA);//��ȡ�����й���locale���� zh_CN
        System.out.println(Locale.US);//��ȡӢ��������locale���� en_US

    }
    @Test
    public void testI18n(){
        //�õ�locale����
        Locale locale = Locale.US;
        //i18n_zh_CN.properties,i18n_en_US.propertiesѡǰ���ǰ׺
        //ͨ��֪����basename��locale��ȡ��Ӧ�������ļ�
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);
        String username = bundle.getString("username");
        System.out.println(username);

    }
}
