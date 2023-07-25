package com.local.bean;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 党
 * @version 1.0
 * 2022/4/28   20:23
 * 购物车
 */
public class Cart {
//    private Integer totalCount;//总数
//    private BigDecimal totalPrice;//总价
    private Map<Integer,CartItem> items = new HashMap<>();//购物项



    //增加商品项
    public void addItem(CartItem cartItem){
        //先检查有没有相同的商品
        //有的话，数量相加
        CartItem item = items.get(cartItem.getId());
        if (item != null){
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }else {
            //没有的话，添加到购物车
            items.put(cartItem.getId(),cartItem);
        }

    }
    //删除商品
    public void deleteItem(Integer id){
        items.remove(id);
    }
    //清空
    public void clear(){
        items.clear();
    }
    //修改商品数量
    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if (item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        AtomicReference<Integer> totalCount = new AtomicReference<>(0);
        items.forEach((k,v)->{
            totalCount.updateAndGet(v1 -> v1 + v.getCount());
        });
        return totalCount.get();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem item : items.values()) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }


    public Cart() {
    }



}
