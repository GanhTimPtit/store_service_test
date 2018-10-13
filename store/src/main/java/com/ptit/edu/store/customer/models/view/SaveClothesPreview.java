package com.ptit.edu.store.customer.models.view;

import com.ptit.edu.store.product.models.data.SaveClothes;

public class SaveClothesPreview {
    private String id;
    private String name;
    private String logoUrl;
    private int price;
    private long saveDate;

    public SaveClothesPreview() {
    }

    public SaveClothesPreview(SaveClothes saveClothes) {
        setId(saveClothes.getClothes().getId());
        setName(saveClothes.getClothes().getName());
        setLogoUrl(saveClothes.getClothes().getLogoUrl());
        setPrice(saveClothes.getClothes().getPrice());
        setSaveDate(saveClothes.getSaveDate().getTime());
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(long saveDate) {
        this.saveDate = saveDate;
    }
}
