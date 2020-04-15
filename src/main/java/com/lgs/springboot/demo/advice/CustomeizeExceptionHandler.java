package com.lgs.springboot.demo.advice;

import com.lgs.springboot.demo.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 空白页面跳转到error.html 页面
 */
@ControllerAdvice
public class CustomeizeExceptionHandler {
@ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
    if (e instanceof CustomizeException) {
        model.addAttribute("message",e.getMessage());
    }else {
        model.addAttribute("message","服务器开小差，稍后试试吧 ");
    }
    return  new ModelAndView("error");
}
/*
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (int)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  HttpStatus.valueOf(statusCode);
    }*/
}
