package com.example.exercise.web;

import com.example.exercise.model.Company;
import com.example.exercise.model.Contact;
import com.example.exercise.model.Country;
import com.example.exercise.model.requests.ContactRequest;
import com.example.exercise.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/contacts")
@Api(tags = "Contact API")
public class ContactRestController {

    private final ContactService contactService;

    @Autowired
    public ContactRestController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ApiOperation("Get all contacts or filter by optional parameters")
    public List<Contact> getFilteredContacts(
            @RequestParam(required = false) Country country,
            @RequestParam(required = false) Company company) {

        if (country != null && company != null) {
            // Filter by country and company
            return contactService.findContactsByCountriesAndCompanies(country, company);
        } else if (country != null) {
            // Filter by country
            return contactService.findContactsByCountries(country);
        } else if (company != null) {
            // Filter by company
            return contactService.findContactsByCompanies(company);
        } else {
            // No filters
            return contactService.listAll();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a contact by ID")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.findById(id);
        return ResponseEntity.ok(contact);
    }

    private ResponseEntity<String> validateRequest(ContactRequest request) {
        if (request.getName() == null || request.getName().isEmpty() || request.getCompany() == null || request.getCountry() == null) {
            return new ResponseEntity<>("Invalid request parameters", HttpStatus.BAD_REQUEST);
        }
        return null; // No validation issues
    }

    @PostMapping
    @ApiOperation("Create a new contact")
    public ResponseEntity<String> createContact(@RequestBody ContactRequest request) {
        ResponseEntity<String> validationResponse = validateRequest(request);
        if (validationResponse != null) {
            return validationResponse;
        }

        contactService.create(request.getName(), request.getCompany(), request.getCountry());
        return new ResponseEntity<>("Contact created successfully!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing contact")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @RequestBody ContactRequest request) {
        if (request.getName() == null || request.getName().isEmpty() || request.getCompany() == null || request.getCountry() == null) {
            return new ResponseEntity<>("Invalid request parameters", HttpStatus.BAD_REQUEST);
        }

        contactService.edit(id, request.getName(), request.getCompany(), request.getCountry());
        return new ResponseEntity<>("Contact updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a contact by ID")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {

        Contact contactToDelete = contactService.findById(id);
        if (contactToDelete == null) {
            return new ResponseEntity<>("Contact not found", HttpStatus.NOT_FOUND);
        }
        contactService.delete(id);
        return new ResponseEntity<>("Contact deleted successfully!", HttpStatus.OK);
    }


}
