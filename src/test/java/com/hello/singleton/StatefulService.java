package com.hello.singleton;

public class StatefulService {

    private int price;

    public void order(String name, int price){
        System.out.println(price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
