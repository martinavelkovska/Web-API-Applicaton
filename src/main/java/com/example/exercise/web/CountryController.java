package com.example.exercise.web;

import com.example.exercise.model.Country;
import com.example.exercise.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public String showCountries(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {
        Page<Country> countries = countryService.findAll(PageRequest.of(page, size));
        model.addAttribute("countries", countries);
        return "AllCountries.html";
    }

    @GetMapping("/add") //samo prikazuvame forma
    public String showAdd(Model model ) {
        model.addAttribute("countries", countryService.listAll());
        return "AddCountry.html";
    }

    @PostMapping("/add")
    public String create(@RequestParam String name) {
        this.countryService.create(name);
        return "redirect:/countries";
    }


    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Country country = this.countryService.findById(id);
        model.addAttribute("country", country);
        model.addAttribute("countries", countryService.listAll());
        return "AddCountry.html";
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @RequestParam String name) {
        this.countryService.edit(id, name);
        return "redirect:/countries";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.countryService.delete(id);
        return "redirect:/countries";
    }


}
