package com.bmsit.bmsitapi.repository;

import com.bmsit.bmsitapi.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
