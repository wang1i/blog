package com.wl.service;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Blog;

import java.util.List;
import java.util.Map;


public interface BlogService {

    int countBlogs();

    Blog getBlogById(Long id);

    List<Blog> getNewBlogList(Long number);

    Blog getBlogAndConvertById(Long id);

    Map<String, List<Blog>> getBlogArchivesWithYear();

    PageInfo<Blog> allBlogList(Integer pageNum);

    PageInfo<Blog> filterBlogList(Integer pageNum, String title, Long typeId, Long recommend);

    List<Blog> recommendBlogList();

    PageInfo<Blog> searchBlogList(Integer pn, String query);

    PageInfo<Blog> getBlogListByTypeId(Long typeId, Integer pageNum);

    PageInfo<Blog> getBlogListByTagId(Long typeId, Integer pageNum);

    void setTagIdsInBlog(Blog blog);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

}
