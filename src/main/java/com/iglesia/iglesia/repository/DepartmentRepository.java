package com.iglesia.iglesia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iglesia.iglesia.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
