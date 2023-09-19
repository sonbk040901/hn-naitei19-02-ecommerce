package com.ecommerce.handler;

import org.springframework.http.HttpStatus;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:17
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
    @ResponseBody
    public Error handleBindException(BindException e) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Request không hợp lệ";
        if (e.getBindingResult().hasErrors())
            errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new Error(errorMessage, e.getFieldError().getField());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // Bắt hết những lỗi còn lại chưa handle đc
    public String exception(
            @NonNull final Throwable throwable,
            @NonNull final Model model) {
        var errorMessage = throwable.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        System.out.println("Error: " + errorMessage);
        return "error/index";
    }

    public record Error(String message, String field) {
    }
}
