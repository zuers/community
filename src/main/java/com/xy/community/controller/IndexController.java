package com.xy.community.controller;

import com.xy.community.dto.PaginationDTO;
import com.xy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;

@Controller
public class IndexController {

    @Autowired
    QuestionService questionService;



    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size)
    {
        PaginationDTO pagination = questionService.list(page,size);
        pagination.getPages().sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                int i = o1 > o2 ? 1 : -1;
                return i;
            }
        });
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
