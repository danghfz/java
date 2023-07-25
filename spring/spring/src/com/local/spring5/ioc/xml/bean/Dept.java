package com.local.spring5.ioc.xml.bean;

/**
 * @author µ≥
 * @version 1.0
 * 2022/5/8   16:51
 */
//≤ø√≈
public class Dept {
    private String depton;
    private String dname;
    Dept(){
    }
    public Dept(String depton,String dname){
        this.depton=depton;
        this.dname=dname;
    }

    public String getDepton() {
        return depton;
    }

    public void setDepton(String depton) {
        this.depton = depton;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "depton='" + depton + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }
}
