package com.dhf.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 党
 * @version 1.0
 * 2022/8/31   18:30
 */
@RestController
public class FlowController {
    @GetMapping("/testA")
    public String testA(){
        return "hello  testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "hello  testB";
    }
    @GetMapping("/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "deal_hotKey") // 自定义一个处理异常方法
    // sentinel 资源名 为  /hotkey( mapping )  或者  hotkey( SentinelResource )
    public String hotKey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false)String p2){
        return "#********************  hotKey  *********************#";
    }
    public String deal_hotKey(@RequestParam(value = "p1",required = false) String p1,
                              @RequestParam(value = "p2",required = false)String p2,
                              BlockException blockException){
        return "请稍后再试，hotKeyException";
    }
}
