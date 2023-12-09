package com.example.exercise.web;

import com.example.exercise.model.Company;
import com.example.exercise.model.Contact;
import com.example.exercise.model.Country;
import com.example.exercise.service.CompanyService;
import com.example.exercise.service.ContactService;
import com.example.exercise.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/contacts","/"})
public class ContactController {

    private final CompanyService companyService;
    private final ContactService contactService;
    private final CountryService countryService;

    public ContactController(CompanyService companyService, ContactService contactService, CountryService countryService) {
        this.companyService = companyService;
        this.contactService = contactService;
        this.countryService = countryService;
    }

    @GetMapping
    public String showContacts(@RequestParam(required = false) Country country, @RequestParam(required = false) Company company,  @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Contact> contacts;

        if (company != null && country != null) {
            contacts = contactService.listContactsByCountriesAndCompanies(country, company, pageable);
        } else if (company != null) {
            contacts = contactService.listContactsByCompanies(company, pageable);
        } else if (country != null) {
            contacts = contactService.listContactsByCountries(country, pageable);
        } else {
            contacts = contactService.findAll(pageable);
        }

        model.addAttribute("contacts", contacts);
        model.addAttribute("country", countryService.listAll());
        model.addAttribute("company", companyService.listAll());
        return "list.html";
    }

    @GetMapping("/contacts/add") //samo prikazuvame forma
    public String showAdd(Model model) {
        List<Contact> contacts = this.contactService.listAll();
        model.addAttribute("contacts", contacts);
        model.addAttribute("companies", companyService.listAll());
        model.addAttribute("countries",countryService.listAll());
        return "form.html";
    }

    @GetMapping("/contacts/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        this.contactService.findById(id);
        Contact contact = this.contactService.findById(id);
        model.addAttribute("contact", contact);
        model.addAttribute("companies", companyService.listAll());
        model.addAttribute("countries",countryService.listAll());
        return "form.html";
    }


    @PostMapping("/contacts")
    public String create(@RequestParam String name,
                         @RequestParam Long company,
                         @RequestParam Long country) {
        this.contactService.create(name, company, country);
        return "redirect:/contacts";
    }

    @PostMapping("/contacts/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam Long company,
                         @RequestParam Long country) {
        this.contactService.edit(id, name, company, country);
        return "redirect:/contacts";
    }

    @PostMapping(path = "/contacts/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.contactService.delete(id);
        return "redirect:/contacts";
    }




}
