package com.atguigu.pojo;

/**
 * @author µ³
 * @version 1.0
 * 2022/4/17   17:22
 */
public class Book {
    public String getSn() {
        return sn;
    }

    public Book(String sn, String name, String author, String prices) {
        this.sn = sn;
        this.name = name;
        this.author = author;
        this.prices = prices;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public Book() {

    }

    private String sn;//³ö°æÐòÁÐºÅ
    private String name;
    private String author;
    private String prices;
}
