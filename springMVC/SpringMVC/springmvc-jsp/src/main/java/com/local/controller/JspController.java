package com.local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/19   14:02
 */
@Controller
public class JspController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}
