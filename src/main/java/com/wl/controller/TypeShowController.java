package com.wl.controller;

import com.wl.pojo.Type;
import com.wl.service.BlogService;
import com.wl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1", value = "pn") Integer pn,  Model model) {
        List<Type> types = typeService.getTypeList();
        if (id == -1L) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        model.addAttribute("activeTypeId", id);
        model.addAttribute("pageInfo", blogService.getBlogListByTypeId(id, pn));
        return "types";
    }

}
