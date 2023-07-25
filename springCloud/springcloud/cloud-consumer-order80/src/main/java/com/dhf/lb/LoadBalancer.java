package com.dhf.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   11:12
 * 自定义负载均衡
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
