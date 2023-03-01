package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Country;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Long> {

}
