package com.xuzimian.globaldemo.common.xml.digester;

import java.util.ArrayList;

/**
 * @program: global-demo
 * @description:订单类
 * @author: xzm
 * @create: 2019-05-24 13:41
 **/
public class Order {
    private String user;    //对应<Order>标签中的user属性
    private String date;    //对应<Order>标签中的date属性
    private String price;   //对应<Order>标签中的price属性
    private ArrayList<Goods> goodsList = new ArrayList<Goods>();//对应<Order>标签下的所有<good>标签

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    // 添加商品到订单
    public void add(Goods goods){
        this.getGoodsList().add(goods);
    }
    // 重写toString()方法，方便于观察结果
    @Override
    public String toString() {
        return "Order [user=" + user + ", date=" + date + ", price=" + price
                + ", goodsList=" + goodsList.toString() + "]";
    }
}
