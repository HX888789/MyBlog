package com.tssf.blog.controller.admin;

import com.tssf.blog.domain.Type;
import com.tssf.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){

        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String postSubmit(Type type, RedirectAttributes attributes){
        String name = type.getName().trim();
        Type type2 = typeService.getTypeByName(name);
        if (type2 != null){
            attributes.addFlashAttribute("message","类型名已存在");
            return "redirect:/admin/types/input";
        }
        if("".equals(name)){
            attributes.addFlashAttribute("message","类型名不能为空");
            return "redirect:/admin/types/input";
        }
        Type type1 = typeService.saveType(type);
        if(type1 ==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost(Type type,@PathVariable Long id, RedirectAttributes attributes){
        String name = type.getName().trim();
        Type type2 = typeService.getTypeByName(name);
        if (type2 != null){
            attributes.addFlashAttribute("message","类型名已存在");
            return "redirect:/admin/types/input";
        }
        if("".equals(name)){
            attributes.addFlashAttribute("message","类型名不能为空");
            return "redirect:/admin/types/input";
        }
        Type type1 = typeService.updateType(id,type);
        if(type1 ==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
