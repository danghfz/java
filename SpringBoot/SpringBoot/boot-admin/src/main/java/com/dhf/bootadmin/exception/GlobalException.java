package com.dhf.bootadmin.exception;

/**
 * @author 党
 * @version 1.0
 * 2022/6/22   21:23
 */

//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理整个web异常
 */
@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String handlerArithmeticException(Exception e){//处理异常
        log.error("异常是{}",e);
        return "login";//视图地址
    }

}
