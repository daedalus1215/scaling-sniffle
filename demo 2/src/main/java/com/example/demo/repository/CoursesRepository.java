package com.example.demo.repository;

import com.example.demo.AllCourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<AllCourseData, String> {

}
