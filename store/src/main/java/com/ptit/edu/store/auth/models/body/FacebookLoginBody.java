package com.ptit.edu.store.auth.models.body;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class FacebookLoginBody {
    @ApiModelProperty(notes = "facebook AccessToken ,NOT NULL,NOT EMPTY",position = 0)
    @NotEmpty
    private String accessToken;
    @ApiModelProperty(notes = "facebook fcmToken ",position = 1)
    private String fcmToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
