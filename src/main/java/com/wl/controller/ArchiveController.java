package com.wl.controller;

import com.wl.pojo.Blog;
import com.wl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(@RequestParam(defaultValue = "1", value = "pn") Integer pn, Model model) {
        int blogCount = blogService.countBlogs();
        Map<String, List<Blog>> archiveMap = blogService.getBlogArchivesWithYear();
        model.addAttribute("blogCount", blogCount);
        model.addAttribute("archiveMap", archiveMap);
        return "archives";
    }

}
