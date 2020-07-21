package com.wl.controller;

import com.wl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @Autowired
    BlogService blogService;

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
