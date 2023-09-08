package com.dhf.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/09/04 17:22
 * evection_global 中的流程变量
 */
public class Evection implements Serializable {
    private final Long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 出差单名字
     */
    private String evectionName;
    /**
     * 出差天数
     */
    private int days;
    private Date startDate;
    private Date endDate;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出差理由
     */
    private String reson;

    public Evection() {
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvectionName() {
        return evectionName;
    }

    public void setEvectionName(String evectionName) {
        this.evectionName = evectionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }
}
