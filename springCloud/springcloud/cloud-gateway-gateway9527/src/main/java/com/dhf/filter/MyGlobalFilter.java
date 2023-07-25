package com.dhf.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 党
 * @version 1.0
 * 2022/8/26   11:15
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String username = request.getQueryParams().getFirst("username");
        if (StringUtils.isEmpty(username)){
            System.out.println("用户名为空,非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST); // 设置响应头
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() { // 优先级，数值越小，优先级越高
        // int HIGHEST_PRECEDENCE = -2147483648;
        // int LOWEST_PRECEDENCE = 2147483647;
        return 0;
    }
}
