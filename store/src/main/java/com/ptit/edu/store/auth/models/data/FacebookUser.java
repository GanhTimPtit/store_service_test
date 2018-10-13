package com.ptit.edu.store.auth.models.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FacebookUser {
    public static final String ID = "id";
    public static final String FULL_NAME = "name";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String BIRTHDAY = "birthday";
    public static final String COVER = "cover";

    private String id;
    private String name;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private Date birthday;
    private FacebookCover cover;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.birthday = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            this.birthday = null;
        }
    }

    public FacebookCover getCover() {
        return cover;
    }

    public void setCover(FacebookCover cover) {
        this.cover = cover;
    }
}
