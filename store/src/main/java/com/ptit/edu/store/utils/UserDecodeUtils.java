package com.ptit.edu.store.utils;

import com.ptit.edu.store.auth.models.data.User;
import com.ptit.edu.store.response_model.HeaderConstant;

public class UserDecodeUtils {
    public static User decodeFromAuthorizationHeader(String headerValue) {
        headerValue = headerValue.replace(HeaderConstant.AUTHORIZATION_VALUE_PREFIX, "");
        String decodedValue = Base64Utils.decode(headerValue);
        String values[] = decodedValue.split(":");
        return new User(values[0], values[1]);
    }
}
