package com.local.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 党
 * @version 1.0
 * 2022/5/22   9:09
 */
@ControllerAdvice
public class ExceptionController {
    //Class<? extends Throwable>[] value() default {};
    @ExceptionHandler(value = {ArithmeticException.class,NullPointerException.class})
    public String testException(Exception e, Model model){
        //e 表示异常信息
        model.addAttribute("ex",e);
        return "error";
    }
}
