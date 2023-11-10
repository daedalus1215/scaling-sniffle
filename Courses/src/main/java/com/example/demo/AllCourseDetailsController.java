package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


//    @CrossOrigin
//    @RequestMapping("/getCourseByName/{name}")
//    public AllCourseData getBookById(@PathVariable(value = "name") String name) {
//        try {
//            AllCourseData lib = repository.findById(name).get();
//            return lib;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }
}
