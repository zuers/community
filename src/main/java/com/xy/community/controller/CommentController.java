package com.xy.community.controller;

import com.xy.community.dto.CommentDTO;
import com.xy.community.dto.ResultDTO;
import com.xy.community.mapper.CommentMapper;
import com.xy.community.model.Comment;
import com.xy.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Kori
 * @Description:
 * @Date: Create in 11:11 2019/8/30
 * @Modified by:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public  Object post(@RequestBody CommentDTO commentDTO,
                        HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(2002,"请先登录");
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0l);
        commentMapper.insert(comment);
        Map<Object,Object> map = new HashMap<>();
        map.put("message","成功");
        return map;
    }
}
