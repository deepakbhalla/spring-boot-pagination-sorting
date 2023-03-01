package com.example.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.model.Country;

public final class PaginationAndSortingUtility {

	private PaginationAndSortingUtility() {
		throw new IllegalStateException("PaginationAndSortingUtility is a utility class which cannot be instantiated");
	}
	
	public static Page<Country> pageAndSort(int pageNum, int pageSize, String sortBy, List<Country> countries) {
		
		if (Objects.isNull(countries)) {
			return new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 0L);
		}
		
		if (countries.isEmpty()) {
			return new PageImpl<>(countries, Pageable.unpaged(), 0L);
		}
		
		/* Sort the input list */
		List<Country> sortedCountries = sort(countries, sortBy);
		
		/* If page size or page number is not set by client, send the entire sorted countries */
		if (pageNum < 1 || pageSize < 1) {
			return new PageImpl<>(sortedCountries, Pageable.unpaged(), countries.size());
		}
		
		/* spring page starts with 0 */
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		
		/* when pageNum * size is too big (bigger than list.size()), sortedCountries.size() will throw an exception, apply this fix */
		if (pageable.getOffset() >= sortedCountries.size()) {
			return new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), sortedCountries.size());
		}
		
		List<Country> pageCountries = sortedCountries.subList(
				(int) pageable.getOffset(),
				(int) Math.min(pageable.getOffset() + pageable.getPageSize(), sortedCountries.size()));
		
		return new PageImpl<>(pageCountries, pageable, sortedCountries.size());
	}

	/**
	 * Sorts the given list based upon given field.
	 * 
	 * @param countries
	 * @param sortBy
	 * @return
	 */
	private static List<Country> sort(List<Country> countries, String sortBy) {
		Comparator<Country> comparator = null;
		if ("name".equalsIgnoreCase(sortBy)) {
			comparator = Comparator.comparing(Country::getName);
		} else if ("population".equalsIgnoreCase(sortBy)) {
			comparator = Comparator.comparing(Country::getPopulation);
		}
		
		if (comparator == null) {
			return countries; 
		}
		return countries.stream().sorted(comparator).collect(Collectors.toList());
		
	}
	
}
