package com.example.exercise.repository;

import com.example.exercise.model.Company;
import com.example.exercise.model.Contact;
import com.example.exercise.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, PagingAndSortingRepository<Contact, Long> {

   Page<Contact> findAll(Pageable pageable);
   // List<Contact> findAllByCountry(Country country);
   @Query("SELECT c FROM Contact c WHERE c.Country = :country")
   Page<Contact> findAllByCountry(@Param("country") Country country, Pageable pageable);

   @Query("SELECT c FROM Contact c WHERE c.Company = :company")
   Page<Contact> findAllByCompany(@Param("company") Company company,  Pageable pageable);

   @Query("SELECT c FROM Contact c WHERE c.Country = :country AND c.Company = :company")
   Page<Contact> findAllByCountryAndCompany(@Param("country") Country country, @Param("company") Company company,  Pageable pageable);


   @Query("SELECT c FROM Contact c WHERE c.Country = :country")
   List<Contact> listAllByCountry(@Param("country") Country country);

   @Query("SELECT c FROM Contact c WHERE c.Company = :company")
   List<Contact> listAllByCompany(@Param("company") Company company);

   @Query("SELECT c FROM Contact c WHERE c.Country = :country AND c.Company = :company")
   List<Contact> listAllByCountryAndCompany(@Param("country") Country country, @Param("company") Company company);
}
