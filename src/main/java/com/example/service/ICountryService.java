package com.example.service;

import java.util.List;
import com.example.model.Country;

public interface ICountryService {

	List<Country> findPaginated(int pageNo, int pageSize);
	
	List<Country> findAll();

	List<Country> findPaginatedCountriesUsingUtil(int pageNo, int pageSize, String sortBy);
	
}
