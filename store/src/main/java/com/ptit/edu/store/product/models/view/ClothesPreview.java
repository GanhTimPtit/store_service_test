package com.ptit.edu.store.product.models.view;

import com.ptit.edu.store.product.models.data.Clothes;

import java.util.Date;

public class ClothesPreview {
    private String id;
    private String name;
    private int price;
    private String category;
    private String logoUrl;
    private int numberSave;

    public ClothesPreview() {
    }

    public ClothesPreview(Clothes clothes) {
        this.id = clothes.getId();
        this.name = clothes.getName();
        this.price = clothes.getPrice();
        this.logoUrl = clothes.getLogoUrl();
        this.numberSave = clothes.getTotalSave();
        this.category= clothes.getCategory().getTitle();
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getNumberSave() {
        return numberSave;
    }

    public void setNumberSave(int numberSave) {
        this.numberSave = numberSave;
    }
}
