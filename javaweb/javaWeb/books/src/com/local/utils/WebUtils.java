package com.local.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   13:27
 */
public class WebUtils {
    public static void copyParamBean(HttpServletRequest request, Object bean) {
        try {
            /**BeanUtils.populate(bean, request.getParameterMap());
             * 参数1：目标对象
             * 参数2：从request中获取的参数
             * 使用反射,调用set方法赋值
             */
            BeanUtils.populate(bean, request.getParameterMap());

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //把map中的值注入bean对象中,比request更通用,,耦合性低
//    public static void copyParamBean(Map map, Object bean) {
//        try {
//            /**BeanUtils.populate(bean, request.getParameterMap());
//             * 参数1：目标对象
//             * 参数2：从request中获取的参数
//             * 使用反射,调用set方法赋值
//             */
//            BeanUtils.populate(bean, map);
//
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
    public static <T> T copyParamBean(Map map, T bean) {
        try {
            /**BeanUtils.populate(bean, request.getParameterMap());
             * 参数1：目标对象
             * 参数2：从request中获取的参数
             * 使用反射,调用set方法赋值
             */
            BeanUtils.populate(bean, map);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
