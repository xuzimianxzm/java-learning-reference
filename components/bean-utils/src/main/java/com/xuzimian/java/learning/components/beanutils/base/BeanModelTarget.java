package com.xuzimian.java.learning.components.beanutils.base;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class BeanModelTarget {
    private String name;
    private Character character;
    private Integer num;
    private Long bigNum;
    private Float floatNum;
    private Double doubleNum;
    private BigDecimal bigDecimal;
    private Date date;
    private LocalDateTime localDateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getBigNum() {
        return bigNum;
    }

    public void setBigNum(Long bigNum) {
        this.bigNum = bigNum;
    }

    public Float getFloatNum() {
        return floatNum;
    }

    public void setFloatNum(Float floatNum) {
        this.floatNum = floatNum;
    }

    public Double getDoubleNum() {
        return doubleNum;
    }

    public void setDoubleNum(Double doubleNum) {
        this.doubleNum = doubleNum;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "BeanModelTarget{" +
                "name='" + name + '\'' +
                ", character=" + character +
                ", num=" + num +
                ", bigNum=" + bigNum +
                ", floatNum=" + floatNum +
                ", doubleNum=" + doubleNum +
                ", bigDecimal=" + bigDecimal +
                ", date=" + date +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
