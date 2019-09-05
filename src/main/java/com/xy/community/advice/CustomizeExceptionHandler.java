package com.xy.community.advice;

import com.alibaba.fastjson.JSON;
import com.xy.community.dto.ResultDTO;
import com.xy.community.exception.CustomizeErrorCode;
import com.xy.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();

        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO = null;
            //返回JSON
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException)ex);
            }else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }else {
            //跳转到错误页面
            if (ex instanceof CustomizeException) {
                model.addAttribute("error", ex.getMessage());
            }else {
                model.addAttribute("error",CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
