package com.ptit.edu.store.product.models.view;

import com.ptit.edu.store.product.models.data.RateClothes;

public class RateClothesViewModel {
    private String customerName;
    private String logoUrl;
    private long rateDate;
    private String message;
    private int rating;

    public RateClothesViewModel(RateClothes rateClothes) {
        this.customerName = rateClothes.getCustomer().getFirstName()+" "+rateClothes.getCustomer().getLastName();
        this.logoUrl = rateClothes.getCustomer().getAvatarUrl();
        this.rateDate = rateClothes.getRateDate().getTime();
        this.message = rateClothes.getMessage();
        this.rating = rateClothes.getRating();
    }

    public RateClothesViewModel() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public long getRateDate() {
        return rateDate;
    }

    public void setRateDate(long rateDate) {
        this.rateDate = rateDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
