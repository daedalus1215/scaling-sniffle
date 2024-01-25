package com.example.libraryservice;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.example.libraryservice.controller.LibraryController;
import com.example.libraryservice.controller.ProductsPrices;
import com.example.libraryservice.controller.SpecificProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "CoursesCatalogue")
public class PactConsumerTest {

    @Autowired
    private LibraryController libraryController;

    @Pact(consumer = "BooksCatalogue")
    public RequestResponsePact PactallCoursesDetailsConfig(PactDslWithProvider builder) {
        return builder.given("courses exist")
                .uponReceiving("getting all courses details")
                .path("/allCourseDetails")
                .willRespondWith()
                .status(200)
                .body(PactDslJsonArray.arrayMinLike(3)
                        .stringType("course_name")
                        .stringType("id")
                        .integerType("price", 10)
                        .stringType("category")
                        .closeObject())
                .toPact();
    }

    @Pact(consumer = "BooksCatalogue")
    public RequestResponsePact PactallCoursesDetailsPriceCheck(PactDslWithProvider builder) {
        return builder.given("courses exist")
                .uponReceiving("getting all courses details")
                .path("/allCourseDetails")
                .willRespondWith()
                .status(200)
                .body(PactDslJsonArray.arrayMinLike(3)
                        .integerType("price", 10)
                        .closeObject())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "PactallCoursesDetailsPriceCheck", port = "9999")
    public void testAllProductsSum(MockServer mockServer) throws JsonMappingException, JsonProcessingException {
        // Arrange
        final String expectedJson = "{\"booksPrice\":250,\"coursesPrice\":30}";
        libraryController.setBaseUrl(mockServer.getUrl());
        final ObjectMapper obj = new ObjectMapper();

        // Act
        ProductsPrices productsPrices = libraryController.getProductPrices();
        final String jsonActual = obj.writeValueAsString(productsPrices);

        // Assert
        Assertions.assertEquals(expectedJson, jsonActual);
    }

    @Pact(consumer = "BooksCatalogue")
    public RequestResponsePact getCourseByName(PactDslWithProvider builder) {
        return builder.given("Course Appium exist")
                .uponReceiving("Get the Appium course details")
                .path("/getCourseByName/Appium")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody()
                        .integerType("price", 44)
                        .stringType("category", "mobile"))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "PactallCoursesDetailsConfig", port = "9999")
    public void testAllProductsSums(MockServer mockServer) throws JsonMappingException, JsonProcessingException {
        // Arrange
        final String expectedJson = "{\"booksPrice\":250,\"coursesPrice\":30}";
        libraryController.setBaseUrl(mockServer.getUrl());

        final ObjectMapper obj = new ObjectMapper();

        // Act
        final ProductsPrices productsPrices = libraryController.getProductPrices();
        final String jsonActual = obj.writeValueAsString(productsPrices);

        // Assert
        Assertions.assertEquals(expectedJson, jsonActual);
    }

    @Test
    @PactTestFor(pactMethod = "getCourseByName", port = "9999")
    public void testByProductName(MockServer mockServer) throws JsonMappingException, JsonProcessingException {
        // Arrange
        libraryController.setBaseUrl(mockServer.getUrl());
        final String expectedJson = "{\"product\":{\"book_name\":\"Appium\",\"id\":\"ttefs36\",\"isbn\":\"ttefs\",\"aisle\":\"36\",\"author\":\"Shetty\"},\"price\":44,\"category\":\"mobile\"}";

        // Act
        final SpecificProduct specificProduct = libraryController.getProductFullDetails("Appium");
        final ObjectMapper obj = new ObjectMapper();
        final String jsonActual = obj.writeValueAsString(specificProduct);

        // Assert
          Assertions.assertEquals(expectedJson, jsonActual);
    }

    @Pact(consumer = "BooksCatalogue")
    public RequestResponsePact getCourseByNameNotExist(PactDslWithProvider builder) {
        return builder.given("Course Appium does not exist", "name", "Appium")
                .uponReceiving("Appium course does not exist")
                .path("/getCourseByName/Appium")
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getCourseByNameNotExist", port = "9999")
    public void testByProductNameNotExist(MockServer mockServer) throws HttpClientErrorException, JsonProcessingException {
        // Arrange
        libraryController.setBaseUrl(mockServer.getUrl());

        try {
            // Act
            final SpecificProduct specificProduct = libraryController.getProductFullDetails("Appium");

            // Assert
            System.out.println("Hi");
            System.out.println(specificProduct);
        } catch (Exception e) {

        }

    }
}
