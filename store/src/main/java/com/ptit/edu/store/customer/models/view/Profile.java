package com.ptit.edu.store.customer.models.view;

import com.ptit.edu.store.customer.models.body.ProfileBody;
import com.ptit.edu.store.customer.models.data.Customer;

import java.util.Date;

public class Profile {
    private String id;
    private String fullName;
    private String phone;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private int gender;
    private long birthday;
    private String email;

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profile(Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getFirstName()+" "+customer.getLastName();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
        this.identityCard = customer.getIdentityCard();
        this.description = customer.getDescription();
        this.avatarUrl = customer.getAvatarUrl();
        this.gender = customer.getGender();
        if (customer.getBirthday() == null) {
            this.birthday = -1;
        } else {
            this.birthday = customer.getBirthday().getTime();

        }
        this.email = customer.getEmail();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
