package com.ptit.edu.store.auth.models.models_view;


import com.ptit.edu.store.customer.models.data.Customer;

public class FacebookUserInfo {
    private String firstName;
    private String lastName;
    private String email;
    private int gender;

    public FacebookUserInfo(Customer customer) {
        setFirstName(customer.getFirstName());
        setLastName(customer.getLastName());
        setGender(customer.getGender());
        setEmail(customer.getEmail());
    }

    public FacebookUserInfo() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
