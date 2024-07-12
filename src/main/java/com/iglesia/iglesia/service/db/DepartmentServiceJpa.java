package com.iglesia.iglesia.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Department;
import com.iglesia.iglesia.repository.DepartmentRepository;
import com.iglesia.iglesia.service.IDepartment;

@Service
public class DepartmentServiceJpa implements IDepartment {

	@Autowired
	private DepartmentRepository departmentRepos;
	
	@Override
	public List<Department> buscarTodas() {
		return departmentRepos.findAll();
	}

}
