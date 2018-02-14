package com.example.jb963962.computepricev2;

/**
 * Created by jecsan on 2/13/18.
 */

public class Item {
    private String name,price,qty;

    Item(){
        name = price = qty  = "";
    }
    Item(String name, String price, String qty){
        this.setName(name);
        this.setPrice(price);
        this.setQty(qty);
    }
    public void setName(String name){
       this.name = name;
    }
    public void setPrice(String price){
       this.price = price;
    }
    public void setQty(String qty){
      this.qty = qty;
    }
    public String getPrice(){
       return this.price;
    }
    public String getIName(){
        return this.name;
    }
    public String getQty(){
        return this.qty;
    }
}
