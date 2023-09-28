package com.ecommerce.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ecommerce.exception.NotFound;
import com.ecommerce.exception.UpdateCartFail;

import lombok.NonNull;

/**
 * @Project: hn-naitei19-02-ecommerce
 * @Author: sonle
 * @Date: 15/09/2023
 * @Time: 09:17
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Nếu validate fail thì trả về 400
    @ResponseBody
    public Error handleBindException(BindException e) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Request không hợp lệ";
        if (e.getBindingResult().hasErrors())
            errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new Error(errorMessage, e.getFieldError().getField());
    }

    @ExceptionHandler({ NoHandlerFoundException.class, NotFound.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Model model) {
        model.addAttribute("title", "404 Not Found!!!");
        model.addAttribute("header", "Phát hiện lỗi 404");
        model.addAttribute("message", "Không tìm thấy trang bạn yêu cầu");
        return "error/index";
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Bắt hết những lỗi còn lại chưa handle đc
    public String exception(
            @NonNull final Throwable throwable,
            @NonNull final Model model) {
        throwable.printStackTrace();
        model.addAttribute("title", "500 Internal server error!!!");
        model.addAttribute("header", "Phát hiện lỗi server 500");
        model.addAttribute("message", "Do coder của chúng tôi quá phèn, chưa bắt được hết lỗi, mong bạn thông cảm");
        return "error/index";
    }

    @ExceptionHandler(UpdateCartFail.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUpdateCartFailException(Exception e) {
        return ResponseEntity.status(400).body("Update cart fail!!!");
    }

    public record Error(String message, String field) {
    }
}
