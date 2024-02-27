package com.example.testing_001.repository;

import com.example.testing_001.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

