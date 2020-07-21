package com.wl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String description;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentable;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    @Transient
    private String tagIds;

    private Type type;
    private List<Tag> tags;
    private User user;
    private List<Comment> comments;

}
