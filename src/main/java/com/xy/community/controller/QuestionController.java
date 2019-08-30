package com.xy.community.controller;

import com.xy.community.dto.QuestionDTO;
import com.xy.community.mapper.QuestionMapper;
import com.xy.community.mapper.UserMapper;
import com.xy.community.model.User;
import com.xy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getById(id);
        //累加阅读数
        questionService.incViewCount(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
