package com.bmsit.bmsitapi.repository;

import com.bmsit.bmsitapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
