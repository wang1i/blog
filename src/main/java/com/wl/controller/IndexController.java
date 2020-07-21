package com.wl.controller;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Blog;
import com.wl.service.BlogService;
import com.wl.service.TagService;
import com.wl.service.TypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1", value = "pn") Integer pn, Model model) {
        PageInfo<Blog> pageInfo = blogService.allBlogList(pn);
        model.addAttribute("types", typeService.getTypeList());
        model.addAttribute("tags", tagService.getTagList());
        model.addAttribute("recommendBlogs", blogService.recommendBlogList());
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1", value = "pn") Integer pn,
                         @RequestParam String query,
                         Model model) {
        PageInfo<Blog> pageInfo = blogService.searchBlogList(pn, query);
        model.addAttribute("pageInfo", pageInfo);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.getBlogAndConvertById(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlog(Model model){
        List<Blog> blogList = blogService.getNewBlogList(3L);
        model.addAttribute("blogList", blogList);
        return "_fragments :: newblogs";
    }

}
