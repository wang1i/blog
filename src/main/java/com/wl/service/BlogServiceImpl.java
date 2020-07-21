package com.wl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.mapper.BlogMapper;
import com.wl.mapper.BlogTagMapper;
import com.wl.mapper.TagMapper;
import com.wl.pojo.Blog;
import com.wl.pojo.Tag;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    BlogTagMapper blogTagMapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public int countBlogs() {
        return blogMapper.countBlogs();
    }

    @Override
    public Map<String, List<Blog>> getBlogArchivesWithYear() {
        Map<String, List<Blog>> blogArchives = new TreeMap<>();
        List<String> years = blogMapper.getUpdateYears();
        for (String year: years) {
            blogArchives.put(year, blogMapper.getBlogListByUpdateYear(year));
        }
        return blogArchives;
    }

    public void setTags(Blog blog) {
        List<Long> tagIds =  blogTagMapper.getTagsByBlogId(blog.getId());
        List<Tag> tags = tagMapper.getTagListByIds(tagIds);
        blog.setTags(tags);
    }

    @Override
    public Blog getBlogAndConvertById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        setTags(blog);
        Parser parser = Parser.builder().build();
        Node document = parser.parse(blog.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        blog.setContent(renderer.render(document));
        return blog;
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        setTags(blog);
        return blog;
    }


    @Override
    public PageInfo<Blog> allBlogList(Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList = blogMapper.getAllBlogList();
        for(Blog blog : blogList) {
            setTags(blog);
        }
        return new PageInfo<Blog>(blogList, 5);
    }

    @Override
    public PageInfo<Blog> getBlogListByTypeId(Long typeId, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList = blogMapper.getBlogListByTypeId(typeId);
        return new PageInfo<Blog>(blogList, 5);
    }

    @Override
    public PageInfo<Blog> getBlogListByTagId(Long tagId, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Long> bids = blogTagMapper.getBlogsByTagId(tagId);
        List<Blog> blogList = blogMapper.getBlogListByBids(bids);
        return new PageInfo<Blog>(blogList, 5);
    }

    @Override
    public PageInfo<Blog> filterBlogList(Integer pageNum, String title, Long typeId, Long recommend) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogList = blogMapper.getFilterBlogList(title, typeId, recommend);
        for(Blog blog : blogList) {
            setTags(blog);
        }
        return new PageInfo<Blog>(blogList, 5);
    }


    @Override
    public void setTagIdsInBlog(Blog blog) {
        List<Long> tagIds =  blogTagMapper.getTagsByBlogId(blog.getId());
        StringBuilder tidSb = new StringBuilder();
        for (int i = 0; i < tagIds.size(); i++) {
            tidSb.append(tagIds.get(i).toString());
            if (i != tagIds.size() - 1)
                tidSb.append(",");
        }
        blog.setTagIds(tidSb.toString());
    }

    @Override
    public int saveBlog(Blog blog) {
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = df.format(dt);
        Timestamp ctime = Timestamp.valueOf(nowTime);

        blog.setCreateTime(ctime);
        blog.setUpdateTime(ctime);
        blog.setViews(0);

        String[] tagIds = blog.getTagIds().split(",");
        List<Long> tds = new ArrayList<>();
        for (String id : tagIds) {
            tds.add(Long.valueOf(id));
        }

        int s1 = blogMapper.saveBlog(blog);
        Long blogId = blogMapper.getBlogIdByCreateTime(ctime);
        int s2 = blogTagMapper.saveBlogTag(blogId, tds);

        if (s1 > 0 && s2 > 0)
            return 1;
        else
            return 0;
    }


    @Override
    public List<Blog> recommendBlogList() {
        return blogMapper.getRecommendBlogList();
    }

    @Override
    public List<Blog> getNewBlogList(Long number) {
        return blogMapper.getNewBlogList(number);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        int u1= blogMapper.updateBlog(blog);

        String[] tagIds = blog.getTagIds().split(",");
        List<Long> tds = new ArrayList<>();
        for (String id : tagIds) {
            tds.add(Long.valueOf(id));
        }
        int s2 = blogTagMapper.saveBlogTag(blog.getId(), tds);

        if (u1 > 0 && s2 > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public PageInfo<Blog> searchBlogList(Integer pn, String query) {
        PageHelper.startPage(pn, 5);
        List<Blog> searchBlogs = blogMapper.getSearchBlogList(query);
        return new PageInfo<Blog>(searchBlogs, 5);
    }

    @Override
    public int deleteBlog(Long id) {
        int d1 = blogMapper.deleteBlogById(id);
        int d2 = blogTagMapper.deleteBlogTagByBid(id);
        if (d1 > 0 && d2 > 0)
            return 1;
        else
            return 0;
    }
}
