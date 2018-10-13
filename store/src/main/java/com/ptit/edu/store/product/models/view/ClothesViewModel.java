package com.ptit.edu.store.product.models.view;

import com.ptit.edu.store.product.models.data.Category;
import com.ptit.edu.store.product.models.data.Clothes;
import com.ptit.edu.store.product.models.data.RateClothes;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClothesViewModel {
    private String id;
    private String name;
    private int price;
    private String description;
    private Date createdDate;
    private String logoUrl;
    private Category category;
    private Set<RateClothesViewModel> rateClothesViewModels;
    private int numberSave;
    private boolean isSaved;
    private float avarageOfRate = 0;

    public ClothesViewModel() {
    }

    public ClothesViewModel(Clothes clothes) {
        this.id = clothes.getId();
        this.name = clothes.getName();
        this.price = clothes.getPrice();
        this.description = clothes.getDescription();
        this.createdDate = clothes.getCreatedDate();
        this.logoUrl = clothes.getLogoUrl();
        this.category = clothes.getCategory();
        this.rateClothesViewModels = new HashSet<>();
        for (RateClothes rateClothes : clothes.getRateClothes()) {
            this.rateClothesViewModels.add(new RateClothesViewModel(rateClothes));
        }
        this.isSaved = false;
        this.numberSave = clothes.getTotalSave();
        setAvarageOfRate(getAvarageOfRate(clothes));
    }

    public float getAvarageOfRate() {
        return avarageOfRate;
    }

    public float getAvarageOfRate(Clothes clothes) {
        if(clothes.getRateClothes().size()==0){
            return 0;
        }
        int sum = 0;
        for (RateClothes rateClothes : clothes.getRateClothes()) {
            sum += rateClothes.getRating();
        }

        return (float) sum / clothes.getRateClothes().size();
    }

    public void setAvarageOfRate(float avarageOfRate) {
        this.avarageOfRate = avarageOfRate;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberSave() {
        return numberSave;
    }

    public void setNumberSave(int numberSave) {
        this.numberSave = numberSave;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean saved) {
        isSaved = saved;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<RateClothesViewModel> getRateClothesViewModels() {
        return rateClothesViewModels;
    }

    public void setRateClothesViewModels(Set<RateClothesViewModel> rateClothesViewModels) {
        this.rateClothesViewModels = rateClothesViewModels;
    }
}
