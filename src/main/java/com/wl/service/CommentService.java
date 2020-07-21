package com.wl.service;

import com.wl.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentListAndSet(Long blogId);

    void saveComment(Comment comment);

}
