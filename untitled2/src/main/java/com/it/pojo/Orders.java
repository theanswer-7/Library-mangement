package com.it.pojo;

import java.util.List;

// 根据订单id查询订单信息以及该订单买了哪几种商品信息
public class Orders {
    private int id;                  // 订单id
    private String number;           // 订单编号
    private List<Product> productList;  // 该订单包含的商品列表（一对多）

    // 无参构造
    public Orders() {
    }

    // 全参构造（可选）
    public Orders(int id, String number, List<Product> productList) {
        this.id = id;
        this.number = number;
        this.productList = productList;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    // toString（可选，方便打印）
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", productList=" + productList +
                '}';
    }
}