package com.example.demo;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("CoursesCatalogue")
public class PactProviderTest {
    @TestTemplate
    public void pactVerificationTest(PactVerificationContext context)  {
        context.verifyInteraction();
    }
}
