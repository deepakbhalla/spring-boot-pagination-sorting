package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Country;

@Repository
public interface CountryPaginationAndSortingRepository extends PagingAndSortingRepository<Country, Long> {

}
