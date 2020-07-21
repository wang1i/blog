package com.wl.mapper;

import com.wl.pojo.Blog;
import com.wl.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BlogTagMapper {

    int saveBlogTag(@Param("bid") Long blogId, @Param("tds") List<Long> tagIds);

    List<Long> getBlogsByTagId(Long tid);

    List<Long> getTagsByBlogId(Long bid);

    int deleteBlogTagByBid(Long blogId);

}
