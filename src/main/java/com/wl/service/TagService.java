package com.wl.service;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Tag;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    List<Tag> getTagsByBlogId(Long blogId);

    List<Tag> getTagList();

    List<Tag> getTagList(String ids);

    PageInfo<Tag> TagList(Integer pageNum);

    int updateTag(Tag tag);

    int deleteTag(Long id);


}
