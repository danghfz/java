package com.local.conreoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 党
 * @version 1.0
 * 2022/5/19   10:04
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/test_restful")
    public String test_restful(){
        return "test_restful";
    }

}
