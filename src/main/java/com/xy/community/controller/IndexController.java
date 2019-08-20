package com.xy.community.controller;

import com.xy.community.dto.QuestionDTO;
import com.xy.community.mapper.QuestionMapper;
import com.xy.community.mapper.UserMapper;
import com.xy.community.model.Question;
import com.xy.community.model.User;
import com.xy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userMapper.findByToken(token);
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                        }
                        break;
                    }
                }
            }
        }
        List<QuestionDTO> questionsList = questionService.list();
        model.addAttribute("questions",questionsList);
        return "index";
    }
}
