package com.lgs.springboot.demo.controller;

import com.lgs.springboot.demo.dto.CommentDTO;
import com.lgs.springboot.demo.dto.ResultDTO;
import com.lgs.springboot.demo.mapper.CommentMapper;
import com.lgs.springboot.demo.model.Comment;
import com.lgs.springboot.demo.model.User;
import com.lgs.springboot.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author GS
 * @date 2020/4/22
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user =(User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(2002,"未登录无法进行评论，请先去登录~");
        }
        Comment comment = new Comment();
        comment.setParintId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        commentService.insert(comment);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message","成功");
        return objectObjectHashMap;
    }
}
