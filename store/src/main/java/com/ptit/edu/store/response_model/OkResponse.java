package com.ptit.edu.store.response_model;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

public class OkResponse extends Response {
    public <T> OkResponse(T data) {
        super(HttpStatus.OK, ResponseConstant.MSG_OK, data);
    }

    public OkResponse(Page<?> page) {
        super(HttpStatus.OK, ResponseConstant.MSG_OK, new PageResult(page));
    }

    public OkResponse() {
        super(HttpStatus.OK, ResponseConstant.MSG_OK);
    }
}
