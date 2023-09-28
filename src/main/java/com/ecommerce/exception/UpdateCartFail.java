package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 19/09/2023
 * @Time: 15:48
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Update cart fail!!!")
public class UpdateCartFail extends RuntimeException {
    public UpdateCartFail(String message) {
        super(message);
    }

    public UpdateCartFail() {
        this("Update cart fail!!!");
    }
}
