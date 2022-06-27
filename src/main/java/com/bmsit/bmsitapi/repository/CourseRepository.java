package com.bmsit.bmsitapi.repository;

import com.bmsit.bmsitapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseName(String name);

    void deleteByCourseName(String name);
}
