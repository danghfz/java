package com.dhf.bootadmin.config;

import com.dhf.bootadmin.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author 党
 * @version 1.0
 * 2022/6/17   23:31
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig  implements WebMvcConfigurer {
    //Restful风格的url
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_method");
        return hiddenHttpMethodFilter;
    }

    //矩阵变量
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }


    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /** 拦截所有,静态资源也会被拦截,css样式等，都会拦截
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/**").//拦截路径
                excludePathPatterns("/","/login","/css/**","/favicon.ico");//排除路径
    }
}
