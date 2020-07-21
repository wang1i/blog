package com.wl.controller;

import com.wl.pojo.Tag;
import com.wl.service.BlogService;
import com.wl.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1", value = "pn") Integer pn, Model model) {
        List<Tag> tags = tagService.getTagList();
        if (id == -1L) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("activeTagId", id);
        model.addAttribute("pageInfo", blogService.getBlogListByTagId(id, pn));
        return "tags";
    }

}
