package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.model.Country;
import com.example.repository.CountryJpaRepository;
import com.example.repository.CountryPaginationAndSortingRepository;
import com.example.service.ICountryService;
import com.example.util.PaginationAndSortingUtility;

@Service
public class CountryService implements ICountryService {

	@Autowired
	private CountryPaginationAndSortingRepository countryPaginatedRepository;
	
	@Autowired
	private CountryJpaRepository countryJpaRepository;

	/**
	 * Returns the paginated data from repository which is extending Paging and Sorting interface.
	 */
	@Override
	public List<Country> findPaginated(int pageNo, int pageSize) {

		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Country> pagedResult = countryPaginatedRepository.findAll(paging);

		return pagedResult.toList();
	}

	/**
	 * Returns the non-paginated data from repository which is extending Jpa repository.
	 */
	@Override
	public List<Country> findAll() {
		return countryJpaRepository.findAll();
	}

	@Override
	public List<Country> findPaginatedCountriesUsingUtil(int pageNo, int pageSize, String sortBy) {
		List<Country> allCountries = countryJpaRepository.findAll();
		Page<Country> pageAndSort = PaginationAndSortingUtility.pageAndSort(pageNo, pageSize, sortBy, allCountries);
		return pageAndSort.getContent();
	}
}
