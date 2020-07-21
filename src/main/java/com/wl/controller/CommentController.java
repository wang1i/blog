package com.wl.controller;

import com.wl.pojo.Comment;
import com.wl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments/{blogId}")
    public String comment(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.getCommentListAndSet(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlogId();
    }

}
