package com.ptit.edu.store.customer.models.view;

public class HeaderProfile {
    private String customerID;
    private String fullName;
    private String avatarUrl;
    private String email;

    public HeaderProfile(String customerID, String fullName, String avatarUrl,String email) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerID() {

        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public HeaderProfile(String fullName, String avatarUrl) {
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;

    }

    public HeaderProfile() {

    }
}
