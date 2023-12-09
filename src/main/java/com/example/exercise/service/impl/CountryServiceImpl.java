package com.example.exercise.service.impl;

import com.example.exercise.model.Country;
import com.example.exercise.model.exeptions.CountryNotFoundException;
import com.example.exercise.repository.CountryRepository;
import com.example.exercise.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;


    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() ->new CountryNotFoundException(id));
    }

    @Override
    public Country create(String name) {
        Country country = new Country(name);
        return  this.countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, String name) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(name);
        return this.countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        countryRepository.delete(country);

    }
}
