package com.example.exercise.web;


import com.example.exercise.model.Country;
import com.example.exercise.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Api(tags = "Country API")
public class CountryRestController {

    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @ApiOperation("Get all counties")
    public List<Country> getAllCountries() {
        return countryService.listAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a country by ID")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.findById(id);
        return ResponseEntity.ok(country);
    }

    @PostMapping
    @ApiOperation("Create a new country")
    public ResponseEntity<String> createCountry(@RequestBody Country country) {
        countryService.create(country.getName());
        return new ResponseEntity<>("Country created successfully!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing country")
    public ResponseEntity<String> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        if (country.getName() == null || country.getName().isEmpty()) {
            return new ResponseEntity<>("Invalid request parameters", HttpStatus.BAD_REQUEST);
        }
        countryService.edit(id, country.getName());
        return new ResponseEntity<>("Country updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a country by ID")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {

        Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND);
        }
        countryService.delete(id);
        return new ResponseEntity<>("Country deleted successfully!", HttpStatus.OK);
    }
}
