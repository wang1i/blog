package com.wl.service;

import com.wl.mapper.CommentMapper;
import com.wl.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Value("${comment.avatar}")
    private String avatar;

    private List<Comment> tempReplyList = new ArrayList<>();
    private List<Comment> setReplyComments(List<Comment> commentList) {
        for (Comment comment : commentList){
            List<Comment> replies = commentMapper.getReplyCommentsByCid(comment.getId());

            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            recursiveReply(replies, c);

            comment.setReplyComments(tempReplyList);

            tempReplyList = new ArrayList<Comment>();
        }
        return commentList;
    }


    private void recursiveReply(List<Comment> replies, Comment parent) {
        if (replies == null)
            return;
        for (Comment reply : replies) {

            reply.setParentComment(parent);
            tempReplyList.add(reply);

            List<Comment> nextReplies = commentMapper.getReplyCommentsByCid(reply.getId());
            recursiveReply(nextReplies, reply);
        }
    }

    @Override
    public List<Comment> getCommentListAndSet(Long blogId) {
        List<Comment> topComments = commentMapper.getTopCommentsByBlogId(blogId);
        return setReplyComments(topComments);

    }

    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setAvatar(avatar);
        commentMapper.saveComment(comment);
    }
}
