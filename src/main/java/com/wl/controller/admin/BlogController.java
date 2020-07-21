package com.wl.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wl.pojo.Blog;
import com.wl.pojo.Tag;
import com.wl.pojo.User;
import com.wl.service.BlogService;
import com.wl.service.TagService;
import com.wl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @Autowired
    TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1", value = "page") Integer page, Model model) {
        PageInfo<Blog> pageInfo = blogService.allBlogList(page);
        model.addAttribute("types", typeService.getTypeList());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1", value = "page") Integer page,
                         @RequestParam(value = "title") String title,
                         @RequestParam(value = "typeId") String typeId,
                         @RequestParam(value = "recommend") String recommend,
                         Model model) {
        if ("".equals(title))
            title = null;
        Long ty_id = "".equals(typeId) ? null : Long.valueOf(typeId);
        Long rec = recommend.equals("true") ? 1L : null;
        PageInfo<Blog> pageInfo = blogService.filterBlogList(page, title, ty_id, rec);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("tags", tagService.getTagList());
        model.addAttribute("types", typeService.getTypeList());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tags", tagService.getTagList());
        model.addAttribute("types", typeService.getTypeList());
        Blog blog = blogService.getBlogById(id);
        blogService.setTagIdsInBlog(blog);
        model.addAttribute("blog", blog);

        return "admin/blogs-input";
    }


    @PostMapping("/blog")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTags(tagService.getTagList(blog.getTagIds()));

        int n = 0;
        if (blog.getId() == null)
            n = blogService.saveBlog(blog);
        else {
            n = blogService.updateBlog(blog);
        }
        if (n > 0) {
            attributes.addFlashAttribute("message", "操作成功");
        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        int n = blogService.deleteBlog(id);
        if (n > 0) {
            attributes.addFlashAttribute("message", "操作成功");
        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/blogs";
    }

}
