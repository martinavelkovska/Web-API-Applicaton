package com.example.exercise.web;

import com.example.exercise.model.Company;
import com.example.exercise.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@Api(tags = "Company API")
public class CompanyRestController {

    private final CompanyService companyService;

    @Autowired
    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @ApiOperation("Get all companies")
    public List<Company> getAllCompanies() {
            return companyService.listAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a company by ID")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    @ApiOperation("Create a new company")
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.create(company.getName());
        return new ResponseEntity<>("Company created successfully!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing company")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        if (company.getName() == null || company.getName().isEmpty()) {
            return new ResponseEntity<>("Invalid request parameters", HttpStatus.BAD_REQUEST);
        }
        companyService.edit(id, company.getName());
        return new ResponseEntity<>("Company updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a company by ID")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {

        Company companyToDelete = companyService.findById(id);
        if (companyToDelete == null) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        companyService.delete(id);
        return new ResponseEntity<>("Company deleted successfully!", HttpStatus.OK);
    }

}
