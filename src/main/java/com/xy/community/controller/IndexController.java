package com.xy.community.controller;

import com.xy.community.dto.PaginationDTO;
import com.xy.community.dto.QuestionDTO;
import com.xy.community.mapper.QuestionMapper;
import com.xy.community.mapper.UserMapper;
import com.xy.community.model.Question;
import com.xy.community.model.User;
import com.xy.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    QuestionService questionService;



    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size)
    {
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
        PaginationDTO pagination = questionService.list(page,size);
        pagination.getPages().sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int i = o1 > o2 ? 1 : -1;
                return i;
            }
        });
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
