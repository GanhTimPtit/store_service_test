package com.ptit.edu.store.auth.models.body;


import com.ptit.edu.store.constants.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import validation.phone.Phone;
import validation.value_in.IntegerIn;

import javax.validation.constraints.NotNull;

@ApiModel
public class FacebookUserRegisterBody {
    @ApiModelProperty(notes = "Tên UV, NOT NULL, NOT EMPTY", required = true, position = 1)
    @NotEmpty
    private String firstName;
    @ApiModelProperty(notes = "Họ UV, NOT NULL, NOT EMPTY", required = true, position = 2)
    @NotEmpty
    private String lastName;
    @ApiModelProperty(notes = "Email UV, NOT NULL, NOT EMPTY", required = true, position = 3)
    @Email
    private String email;
    @ApiModelProperty(notes = "SDT UV, NOT NULL, NOT EMPTY", required = true, position = 4)
    @Phone(nullable = false)
    private String phone;
    @ApiModelProperty(notes = "Giới tính, nhận giá trị (" + Constant.MALE + "(Nam), "
            + Constant.FEMALE + "(Nữ)), NOIT NULL", position = 5)
    @IntegerIn(value = {Constant.FEMALE, Constant.MALE}, message = "Value must be in {" + Constant.FEMALE + ", " + Constant.MALE + "}")
    private int gender;
    @ApiModelProperty(notes = "Kinh độ của UV, NOT NULL", position = 6)
    @NotNull
    private Double lat;
    @ApiModelProperty(notes = "Vĩ độ của UV, NOT NULL", position = 7)
    @NotNull
    private Double lon;
    @ApiModelProperty(notes = "Facebook AccessToken của UV, NOT NULL, NOT EMPTY", position = 8)
    @NotEmpty
    private String accessToken;
    @ApiModelProperty(notes = "Facebook gcmToken của UV", position = 9)
    private String gcmToken;

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
