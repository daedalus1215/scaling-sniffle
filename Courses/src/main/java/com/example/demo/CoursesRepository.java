package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<AllCourseData, String> {
    Optional<AllCourseData> findByCourseName(String courseName);

}
