package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 19/09/2023
 * @Time: 15:48
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Found!!!")
public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }

    public NotFound() {
        this("Not Found!!!");
    }
}
