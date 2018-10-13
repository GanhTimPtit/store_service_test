package com.ptit.edu.store.customer.models.data;

import com.ptit.edu.store.auth.models.body.CustomerRegisterBody;
import com.ptit.edu.store.auth.models.data.User;
import com.ptit.edu.store.customer.models.body.ProfileBody;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private int gender;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private Date birthday;
    private String coverUrl;
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;

    public String getEmail() {
        return email;
    }

    public void update(ProfileBody body) {
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.phone = body.getPhone();
        this.address = body.getAddress();
        this.identityCard = body.getIdentityCard();
        this.avatarUrl = body.getAvatarUrl();
        this.gender = body.getGender();
        if (body.getBirthday() == -1) {
            birthday = new Date();
        } else {
            birthday = new Date(body.getBirthday());
        }
        this.email = body.getEmail();
    }
    public void updatecontruct(User user, CustomerRegisterBody body) {
        this.user= user;
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.phone = body.getPhone();
        this.email = user.getUsername();
        this.address = body.getAddress();
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
