package com.local.boot.config;

import com.local.boot.messageConverters.DhfMessageConverter;
import com.local.boot.pojo.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 党
 * @version 1.0
 * 2022/5/31   18:33
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_method");
        return hiddenHttpMethodFilter;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        // 设置为不移除，矩阵变量才能生效
        urlPathHelper.setRemoveSemicolonContent(false);
        pathMatchConfigurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // WebMvcConfigurer添加一个自定义的转换器
        // str -> Pet
        registry.addConverter(new Converter<String, Pet>() {
            @Override
            /*
            * Pet (STring name,Integer age)
            * 提交过来的是 name,age  */
            public Pet convert(String s) {
                if (!StringUtils.isEmpty(s)){
                    // 使用 ，分割
                    String[] split = s.split(",");
                    Pet pet = new Pet();
                    pet.setName(split[0]);
                    pet.setAge(Integer.parseInt(split[1]));
                    return pet;
                }
                return null;
            }
        });
    }

    @Override
    //扩展MessageConverters
    // 添加自定义的Converter
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new DhfMessageConverter());
    }

    @Override
    // 自定义内容协商策略
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //public ParameterContentNegotiationStrategy(Map<String, MediaType> mediaTypes)
        HashMap<String, MediaType> hashMap = new HashMap<>();
        hashMap.put("json", MediaType.APPLICATION_JSON);
        hashMap.put("xml", MediaType.APPLICATION_XML);
        hashMap.put("dhf", MediaType.parseMediaType("application/x-dhf"));
        // 基于参数的内容协商策略
        ParameterContentNegotiationStrategy strategy =
                new ParameterContentNegotiationStrategy(hashMap);
        strategy.setParameterName("format");//参数名称
        //基于请求头的内容协商策略
        HeaderContentNegotiationStrategy headerContentNegotiationStrategy =
                new HeaderContentNegotiationStrategy();
        configurer.strategies(Arrays.asList(strategy,headerContentNegotiationStrategy));

    }
}
