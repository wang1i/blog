package com.wl.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Tag;
import com.wl.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1", value = "pn") Integer pn,
                       Model model) {
        PageInfo<Tag> pageInfo = tagService.TagList(pn);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }

    @PostMapping("/tag")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        int n = tagService.saveTag(tag);
        if (n > 0) {
            attributes.addFlashAttribute("message", "新增成功");
        }else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tag/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        int n = tagService.updateTag(tag);
        if (n > 0) {
            attributes.addFlashAttribute("message", "更新成功");
        }else {
            attributes.addFlashAttribute("message", "更新失败");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
