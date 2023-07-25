package com.local.bean;

import java.math.BigDecimal;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   15:27
 */
public class Book {
    /**
     * id int PRIMARY KEY AUTO_INCREMENT,
     *     name VARCHAR(100),
     *     price DECIMAL(11,2),-- 单价
     *     author VARCHAR(100),
     *     sales int, -- 出售
     *     stock INT, -- 库存
     *     img_path VARCHAR(200)*/
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;
    private Integer sales;
    private Integer stock;
    private String img_path = "static/img/default.jpg";

    public Book() {
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public Book(Integer id, String name, BigDecimal price, String author, Integer sales, Integer stock, String img_path) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        if (img_path == null || "".equals(img_path)){
            this.img_path = img_path;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                '}';
    }
}
