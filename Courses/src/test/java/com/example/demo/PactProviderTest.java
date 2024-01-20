package com.example.demo;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("CoursesCatalogue")
@PactFolder("pacts")
public class PactProviderTest {

    @LocalServerPort
    public int port;
    @Autowired
    private CoursesRepository coursesRepository;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTest(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    public void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @State(value = "courses exist", action = StateChangeAction.SETUP)
    public void coursesExist() {
    }

    @State(value = "courses exist", action = StateChangeAction.TEARDOWN)
    public void coursesExistTearDown() {
    }

    @State(value = "Course Appium exist", action = StateChangeAction.SETUP)
    public void coursesAppiumExist() {
    }

    @State(value = "Course Appium exist", action = StateChangeAction.TEARDOWN)
    public void coursesAppiumTearDown() {
    }

    @State(value = "Get the Appium course details", action = StateChangeAction.SETUP)
    public void coursesAppiumDetailsExist() {
    }

    @State(value = "Get the Appium course details", action = StateChangeAction.TEARDOWN)
    public void coursesAppiumDetailsTearDown() {
    }

    @State(value = "Course Appium does not exist", action = StateChangeAction.SETUP)
    public void coursesAppiumNotExist() {

        final Optional<AllCourseData> appium = coursesRepository.findByCourseName("Appium");
        if (appium.isPresent()) {
            coursesRepository.delete(appium.get());
        }
    }

    @State(value = "Course Appium does not exist", action = StateChangeAction.TEARDOWN)
    public void coursesAppiumNotExistTearDown() {
        if (coursesRepository.findByCourseName("Appium").isEmpty()) {
            coursesRepository.save(new AllCourseData("12", "Appium", 44, "mobile"));
        }
    }
}