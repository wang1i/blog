package com.wl.mapper;

import com.wl.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    void saveComment(Comment comment);

    Comment getCommentById(Long id);

    List<Comment> getTopCommentsByBlogId(Long blogId);

    List<Comment> getReplyCommentsByCid(Long cid);

}
