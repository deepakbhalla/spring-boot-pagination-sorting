package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Country;
import com.example.service.ICountryService;

@RestController
@RequestMapping(value = "/countries/v1")
public class CountryController {

	@Autowired
    private ICountryService countryService;

	/**
	 * Get paginated data from paginated repository via service layer.
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
    @GetMapping("/page/repo/{pageNo}/{pageSize}")
    public List<Country> getPaginatedCountries(@PathVariable int pageNo, 
            @PathVariable int pageSize) {

        return countryService.findPaginated(pageNo, pageSize);
    }


    /**
     * Returns all the countries without pagination and sorting.
     * 
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Country>> getAllCountries() {
    	return new ResponseEntity<List<Country>>(countryService.findAll(), HttpStatus.OK);
    }
   
    @GetMapping("/page/util/{pageNo}/{pageSize}/{sortBy}")
    public List<Country> getCountriesUsingPageUtils(@PathVariable int pageNo, 
            @PathVariable int pageSize, @PathVariable String sortBy) {

        return countryService.findPaginatedCountriesUsingUtil(pageNo, pageSize, sortBy);
    }
    
}
