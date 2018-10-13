package com.ptit.edu.store.customer.models.body;

import com.ptit.edu.store.validation.GreaterOrEqual;

public class OrderBody {
    private String color;
    private String size;
    @GreaterOrEqual(value = 0,message = "Tong so luong phai > 0")
    private int amount;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
