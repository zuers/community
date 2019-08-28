package com.xy.community.controller;

import com.xy.community.dto.PaginationDTO;
import com.xy.community.mapper.UserMapper;
import com.xy.community.model.User;
import com.xy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;

@Controller
public class ProfileController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "5") Integer size
    ) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }
        if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }

        PaginationDTO pagination =  questionService.profileList(user.getId(),page,size);
        pagination.getPages().sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                int i = o1 > o2 ? 1 : -1;
                return i;
            }
        });
        model.addAttribute("pagination",pagination);
        return "profile";

    }
}
