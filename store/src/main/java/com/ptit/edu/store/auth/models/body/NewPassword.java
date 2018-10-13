package com.ptit.edu.store.auth.models.body;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class NewPassword {
    @NotEmpty
    @NotNull
    @ApiModelProperty(notes = "Mật khẩu cũ",position = 1)
    private String oldPassword;
    @NotEmpty
    @NotNull
    @ApiModelProperty(notes = "Mật khẩu mới",position = 2)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
