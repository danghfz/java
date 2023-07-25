package com.local.controller;

import com.local.pojo.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 党
 * @version 1.0
 * 2022/5/20   19:03
 */
@Controller
public class HttpController {
    //请求体
    @RequestMapping("/testRequestBody")
    public ModelAndView testRequestBody(@RequestBody String requestBody) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("requestBody", requestBody);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testRequestEntity")
    public ModelAndView testRequestEntity(RequestEntity<String> requestEntity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("requestEntity", requestEntity);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping("/testResponse")
    @ResponseBody//直接将返回值作为响应体返回
    public String testResponseBody() {
        return "success";
    }

    @RequestMapping("/testUser")
    @ResponseBody//直接将返回值作为响应体返回
    public User testUser() {//User要有get方法
        return new User("赵迪", 18);
    }

    @RequestMapping("/testAjax")
    @ResponseBody
    public Ajax testAjax(String username, String password) {
        return new Ajax(username, password);
    }

    static class Ajax {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public Ajax() {
        }

        public Ajax(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


}
