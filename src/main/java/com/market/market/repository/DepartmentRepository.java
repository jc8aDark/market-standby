package com.market.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
