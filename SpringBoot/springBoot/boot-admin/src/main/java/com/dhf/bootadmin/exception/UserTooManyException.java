package com.dhf.bootadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 党
 * @version 1.0
 * 2022/6/25   16:49
 */
//自定义异常
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多")//403
public class UserTooManyException extends RuntimeException{

    public UserTooManyException(String message){
        super(message);
    }
    public UserTooManyException(){}

}
