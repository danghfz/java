package com.dhf.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import jdk.nashorn.internal.ir.Block;

/**
 * @author 党
 * @version 1.0
 * 2022/9/2   16:36
 * 定义限流处理类
 */
public class CustomerBlockHandler {
    public static String handlerException(BlockException e){
        return "全局异常处理 ---- 1";
    }
    public static String handlerException2(BlockException e){
        return "全局异常处理 ---- 2";
    }
}
