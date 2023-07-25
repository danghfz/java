package com.dhf.controller;

import com.dhf.Order2001;
import com.dhf.entity.Account;
import com.dhf.entity.Order;
import com.dhf.entity.Storage;
import com.dhf.service.AccountService;
import com.dhf.service.OrderService;
import com.dhf.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.RuntimeErrorException;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/9/3   13:04
 */
@RestController

public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;
    //新建订单
    @GetMapping("/test/{count}")
    @GlobalTransactional(name = "seata-order-service",rollbackFor = Exception.class)
    public String create(@PathVariable Integer count) {
        try {
            // *********************** 创建订单
            Order order = new Order();
            order.setStatus(0);
            order.setCount(count);
            order.setMoney((long) (count * 10));
            order.setProductId(1L);
            order.setUserId(1L);
            orderService.updateById(order);
            // *********************** 减少库存
            Storage storage = storageService.getById(1L);
            if (storage.getResidue() >= count){ // 库存够
                storage.setResidue(storage.getResidue() - count);
                storage.setUsed(storage.getUsed() + count);
                storageService.updateById(storage);
            }else  { // 库存不够
                throw new RuntimeException("库存不够了");
            }
            // ********************** 减少用户余额
            Account account = accountService.getById(1L);
            if (account.getResidue() >= count * 10){ // 余额充足
                account.setResidue(account.getResidue() - count * 10);
                account.setUsed(account.getUsed() + count * 10);
                accountService.updateById(account);
            }else {
                throw new RuntimeException("余额不够了");
            }
            // **********************************  修改订单状态
            order.setStatus(1);
            orderService.updateById(order);
        }catch (Exception e){ // 将所以异常变成 RuntimeException，确保seata回滚
            throw new RuntimeException("出现异常");
        }
        return "成功";
    }

}
