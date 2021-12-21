package com.xuzimian.globaldemo.common.xml.digester;

/**
 * @program: global-demo
 * @description: 商品类
 * @author: xzm
 * @create: 2019-05-24 13:42
 **/
public class Goods {
    private String id;
    private String name;
    private String price;
    private String count;
    private String total_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    //省略getter和setter...

    @Override
    public String toString() {
        return "Goods [id=" + id + ", name=" + name + ", price=" + price
                + ", count=" + count + ", total_price=" + total_price +"]";
    }
}
