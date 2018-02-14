package com.example.jb963962.computepricev2;

/**
 * Created by jecsan on 2/13/18.
 */

public class Item {
    private String name;
    private Double price;
    private Integer qty;

    Item(String name, Double price, Integer qty){
        this.name  = name;
        this.price = price;
        this.qty = qty;
    }
    public Double getPrice(){
       return this.price;
    }
    public String getIName(){
        return this.name;
    }
    public Integer getQty(){
        return this.qty;
    }
}
