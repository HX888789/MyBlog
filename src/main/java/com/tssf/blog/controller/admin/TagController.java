package com.tssf.blog.controller.admin;

import com.tssf.blog.domain.Tag;
import com.tssf.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String list(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){

        Page<Tag> tags = tagService.listTag(pageable);
        model.addAttribute("page",tags);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag", tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String postSubmit(Tag tag, RedirectAttributes attributes){
        String name = tag.getName().trim();
        Tag tag2 = tagService.getTagByName(name);
        if (tag2 != null){
            attributes.addFlashAttribute("message","标签名已存在");
            return "redirect:/admin/tags/input";
        }
        if("".equals(name)){
            attributes.addFlashAttribute("message","标签名不能为空");
            return "redirect:/admin/tags/input";
        }
        Tag tag1 = tagService.saveTag(tag);
        if(tag1 ==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}")
    public String editPost(Tag tag,@PathVariable Long id, RedirectAttributes attributes){
        String name = tag.getName().trim();
        Tag tag2 = tagService.getTagByName(name);
        if (tag2 != null){
            attributes.addFlashAttribute("message","标签名已存在");
            return "redirect:/admin/tags/input";
        }
        if("".equals(name)){
            attributes.addFlashAttribute("message","标签名不能为空");
            return "redirect:/admin/tags/input";
        }
        Tag tag1 = tagService.updateTag(id, tag);
        if(tag1 ==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
