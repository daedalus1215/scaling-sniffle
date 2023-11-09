package com.example.demo;

import com.example.demo.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demo")
public class DemoApplication implements	 CommandLineRunner {
	@Autowired
	CoursesRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String[] args) {
		List<AllCourseData> lib = repository.findAll();
		System.out.println(lib.get(0).getCourse_name() + "is this");


	}
}
