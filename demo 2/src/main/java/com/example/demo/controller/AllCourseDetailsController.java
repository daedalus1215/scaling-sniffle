package com.example.demo.controller;

import com.example.demo.AllCourseData;
import com.example.demo.repository.CoursesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class AllCourseDetailsController {

    final private CoursesRepository repository;

    public AllCourseDetailsController(CoursesRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/allCourseDetails")
    public List<AllCourseData> GetCourses() {

        return repository.findAll();


    }


    @CrossOrigin
    @RequestMapping("/getCourseByName/{name}")
    public AllCourseData getBookById(@PathVariable(value = "name") String name) {
        try {
            AllCourseData lib = repository.findById(name).get();
            return lib;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
