package com.dhf.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 党
 * @version 1.0
 * 2022/8/26   10:27
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("route1",r -> r.path("/guonei").
                uri("http://news.baidu.com")).build();
        // http://localhost:9527/guonei => http://news.baidu.com/guonei
        return routes.build();
    }
}
