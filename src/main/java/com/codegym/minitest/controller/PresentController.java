package com.codegym.minitest.controller;

import com.codegym.minitest.model.Present;
import com.codegym.minitest.model.PresentForm;
import com.codegym.minitest.service.IPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/present")
@PropertySource("classpath:upload_file.properties")
public class PresentController {
    @Value("${upload}")
    private String upload;
    @Autowired
    private IPresentService presentService;
    @GetMapping("")
    public String index(Model model) {
        List<Present> customerList = presentService.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("present", new PresentForm());
        return "/create";
    }
    @PostMapping("/save")
    public String save(PresentForm presentForm) throws IOException {
        MultipartFile file = presentForm.getImg();
        String nameImg = file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(upload+nameImg));
        Present present = new Present();
        present.setName(presentForm.getName());
        present.setPrice(presentForm.getPrice());
        present.setId(presentForm.getId());
        present.setCode(presentForm.getCode());
        present.setImg(nameImg);
        present.setShip(presentForm.getShip());
        presentService.save(present);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("present", presentService.findById(id));
        return "/update";
    }
    @PostMapping("/update")
    public String update(Present present) {
        presentService.save(present);
        return "redirect:/presents";
    }@GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("present", presentService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String delete(Present present, RedirectAttributes redirect) {
        presentService.remove(present.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/presents";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("present", presentService.findById(id));
        return "/view";
    }
}
