package com.dhf.feignClient;

import com.dhf.pojo.Payment;
import com.dhf.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 党
 * @version 1.0
 * 2022/8/25   11:54
 */
@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {
    @GetMapping("/payment/payment/create")
    Result create(@RequestBody Payment payment);
    @GetMapping("/payment/payment/getPaymentById/{id}")
    Result getPaymentById(@PathVariable(value = "id") String id);
}
