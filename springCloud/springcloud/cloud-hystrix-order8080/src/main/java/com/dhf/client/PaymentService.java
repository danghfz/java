package com.dhf.client;

import com.dhf.client.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   17:17
 */
@Component
@FeignClient(name = "CLOUD-HYSTRIX-PAYMENT8001",fallback = PaymentServiceImpl.class)
public interface PaymentService {
    @GetMapping("/paymentInfo")
    public String paymentInfo(@RequestParam("id")Integer id);
    @GetMapping("/err")
    public String error();
}
