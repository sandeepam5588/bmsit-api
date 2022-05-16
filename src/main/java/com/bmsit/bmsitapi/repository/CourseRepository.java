package com.bmsit.bmsitapi.repository;

import com.bmsit.bmsitapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
