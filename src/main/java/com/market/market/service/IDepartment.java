package com.market.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.market.market.model.Department;

@Service
public interface IDepartment {
	List<Department> buscarTodas();

}
