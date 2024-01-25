package com.example.libraryservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@RestController
public class LibraryController {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    @Autowired
    ProductsPrices productPrices;
    String baseUrl = "http://localhost:8080";

    @GetMapping("/getProductDetails/{name}")
    public SpecificProduct getProductFullDetails(@PathVariable(value = "name") String name) throws JsonProcessingException, HttpClientErrorException {
        final SpecificProduct specificProduct = new SpecificProduct();
        specificProduct.setProduct(new Library("Appium", "ttefs36", "ttefs", "36", "Shetty"));
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/getCourseByName/" + name, String.class);

        if (response.getStatusCode().is4xxClientError()) {
            specificProduct.setMsg(name + "Category and price details are not available at this time");
            return specificProduct;
        }

        ObjectMapper mapper = new ObjectMapper();

        AllCourseDetails allCourseDetails = mapper.readValue(response.getBody(), AllCourseDetails.class);

        specificProduct.setCategory(allCourseDetails.getCategory());
        specificProduct.setPrice(allCourseDetails.getPrice());

        return specificProduct;
    }

    @CrossOrigin
    @GetMapping("/getProductPrices")
    public ProductsPrices getProductPrices() throws JsonProcessingException {
        productPrices.setBooksPrice(250);

        long sum = 0;
        for (int i = 0; i < getAllCoursesDetails().length; i++) {
            sum = sum + getAllCoursesDetails()[i].getPrice();
        }

        productPrices.setCoursesPrice(sum);

        return productPrices;
    }

    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    public AllCourseDetails[] getAllCoursesDetails() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/allCourseDetails", String.class);
        ObjectMapper mapper = new ObjectMapper();

        AllCourseDetails[] allCourseDetails = mapper.readValue(response.getBody(), AllCourseDetails[].class);
        return allCourseDetails;
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

