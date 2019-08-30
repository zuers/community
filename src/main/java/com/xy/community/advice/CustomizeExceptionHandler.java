package com.xy.community.advice;

import com.xy.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {

        if (ex instanceof CustomizeException) {
            model.addAttribute("error", ex.getMessage());
        }else {
            model.addAttribute("error","服务器冒烟了");
        }
        return new ModelAndView("error");

    }
}
