package com.local.spring5.JdbcTemplate.pojo;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/12   12:30
 */
public class Dept {
    private Integer depton;
    private String dname;
    private String loc;

    @Override
    public String toString() {
        return "Dept{" +
                "depton=" + depton +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    public Dept(Integer depton, String dname, String loc) {
        this.depton = depton;
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {
    }

    public Integer getDepton() {
        return depton;
    }

    public void setDepton(Integer depton) {
        this.depton = depton;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
