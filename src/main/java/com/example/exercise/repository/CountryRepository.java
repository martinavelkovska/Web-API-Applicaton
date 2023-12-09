package com.example.exercise.repository;

import com.example.exercise.model.Contact;
import com.example.exercise.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>,PagingAndSortingRepository<Country, Long> {
    Page<Country> findAll(Pageable pageable);
}
