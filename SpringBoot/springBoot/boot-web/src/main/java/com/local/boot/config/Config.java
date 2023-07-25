package com.local.boot.config;

import com.local.boot.messageConverters.DhfMessageConverter;
import com.local.boot.pojo.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/6/12   16:13
 */
//@Configuration(proxyBeanMethods = false)
public class Config {
    // HiddenHttpMethodFilter
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        // 设置方法参数名称
        hiddenHttpMethodFilter.setMethodParam("_method");
        return hiddenHttpMethodFilter;
    }

    // WebMvcConfiguration
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            // urlPathHelper
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }


            // 格式转换器
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {

                    @Override
                    public Pet convert(String source) {
                        if (!StringUtils.isEmpty(source)) {
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }

            @Override
            // 扩展MessageConverters自定义消息转换器
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new DhfMessageConverter());
            }

            @Override
            // 自定义内容协商策略
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                //public ParameterContentNegotiationStrategy(Map<String, MediaType> mediaTypes)
                HashMap<String, MediaType> map = new HashMap<>();
                map.put("json", MediaType.APPLICATION_JSON);
                map.put("xml", MediaType.APPLICATION_XML);
                map.put("dhf", MediaType.parseMediaType("application/x-dhf"));
                ParameterContentNegotiationStrategy strategy =
                        new ParameterContentNegotiationStrategy(map);
                configurer.strategies(Arrays.asList(strategy));

            }
        };
    }
}
