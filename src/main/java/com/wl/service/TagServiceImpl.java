package com.wl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.mapper.BlogMapper;
import com.wl.mapper.BlogTagMapper;
import com.wl.mapper.TagMapper;
import com.wl.pojo.Blog;
import com.wl.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getTagList() {
        List<Tag> tagList= tagMapper.getTagList();
        for (Tag t : tagList) {
            List<Long> blogIds = blogTagMapper.getBlogsByTagId(t.getId());
            List<Blog> blogs = blogMapper.getBlogListByBids(blogIds);
            t.setBlogs(blogs);
        }
        return tagList;
    }

    @Override
    public List<Tag> getTagsByBlogId(Long blogId) {
        List<Long> tagIds =  blogTagMapper.getTagsByBlogId(blogId);
        return tagMapper.getTagListByIds(tagIds);
    }

    @Override
    public List<Tag> getTagList(String ids) {
        String[] idSplit= ids.split(",");
        List<Tag> tags = new ArrayList<>();
        for (String id : idSplit) {
            Long newId = Long.valueOf(id);
            Tag t = tagMapper.getTagById(newId);
            tags.add(t);
        }
        return tags;
    }

    @Override
    public PageInfo<Tag> TagList(Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Tag> tagList = tagMapper.getTagList();
        return new PageInfo<>(tagList, 5);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }
}
