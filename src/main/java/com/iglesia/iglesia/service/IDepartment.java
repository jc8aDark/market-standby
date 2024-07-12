package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Department;

@Service
public interface IDepartment {
	List<Department> buscarTodas();

}
