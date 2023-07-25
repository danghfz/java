package com.dhf.controller;

import com.dhf.service.IMessageService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 党
 * @version 1.0
 * 2022/8/27   17:14
 */
@RestController
public class ProdController {
    @Autowired
    private IMessageService messageService;
    @GetMapping("/send")
    public String sendMessage(String message){
        return messageService.send(message);
    }
}
