package com.local.spring5.ioc.xml.bean;

import java.util.*;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/8   17:35
 */
public class Student {
    private String[] books;
    private List<String> score;
    private Map<String, String> map;
    private Set<String> set;
    private List<Date> dateList;

    public List<Date> getDateList() {
        return dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }

    public Student(){}

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<String> getScore() {
        return score;
    }

    public void setScore(List<String> score) {
        this.score = score;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getBooks() {
        return books;
    }
    public void setBooks(String[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Student{" +
                "\nbooks=" + Arrays.toString(books) +
                ", \nscore=" + score +
                ", \nmap=" + map +
                ", \nset=" + set +
                ", \ndateList=" + dateList +
                '}';
    }
}
