package com.ryan.hello.advice;

import com.ryan.hello.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeException) {
               model.addAttribute("message",e.getMessage());
        } else {
           model.addAttribute("message","服务器冒烟了，要不然你稍后试试~~~");
        }
        model.addAttribute("message", "");
        return new ModelAndView("error");
    }
}


