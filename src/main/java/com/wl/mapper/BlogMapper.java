package com.wl.mapper;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface BlogMapper {

    int countBlogs();

    Blog getBlogById(Long id);

    List<Blog> getNewBlogList(Long number);

    Long getBlogIdByCreateTime(Date createTime);

    List<String> getUpdateYears();

    List<Blog> getBlogListByUpdateYear(String year);

    List<Blog> getAllBlogList();

    List<Blog> getFilterBlogList(String title, Long typeId, Long recommend);

    List<Blog> getRecommendBlogList();

    List<Blog> getSearchBlogList(String query);

    List<Blog> getBlogListByBids(List<Long> bids);

    List<Blog> getBlogListByTypeId(Long typeId);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlogById(Long id);

}
