package com.local.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:38
 */
public class Order {
    /**
     * `order_id` VARCHAR(50) PRIMARY KEY,  	##订单号
     * 	`create_time` DATETIME NOT NULL,	##订单时间
     * 	`total_money` DECIMAL(11,2) NOT NULL,	##总金额
     * 	`status` INT NOT NULL DEFAULT 0, 	##物流状态：0-未发货、1-等待用户签收、2-用户已签收
     * 	`user_id` INT NOT NULL,			##用户编号*/
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    // 0 表未发货, 1 已发货 2 已签收
    private Integer status = 0;
    private Integer userId;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", creatTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreatTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }
}
