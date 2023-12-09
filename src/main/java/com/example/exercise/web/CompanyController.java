package com.example.exercise.web;


import com.example.exercise.model.Company;

import com.example.exercise.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String showCompanies(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Company> companies = companyService.findAll(PageRequest.of(page, size));
        model.addAttribute("companies", companies);
        return "AllCompanies.html";
    }

    @GetMapping("/add") //samo prikazuvame forma
    public String showAdd(Model model ) {
        model.addAttribute("companies", companyService.listAll());
        return "AddCompany.html";
    }

    @PostMapping("/add")
    public String create(@RequestParam String name) {
        this.companyService.create(name);
        return "redirect:/companies";
    }


    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Company company = this.companyService.findById(id);
        model.addAttribute("company", company);
        model.addAttribute("companies", companyService.listAll());
        return "AddCompany.html";
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam String name) {
        this.companyService.edit(id, name);
        return "redirect:/companies";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.companyService.delete(id);
        return "redirect:/companies";
    }


}
