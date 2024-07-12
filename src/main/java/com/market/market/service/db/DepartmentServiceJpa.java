package com.market.market.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Department;
import com.market.market.repository.DepartmentRepository;
import com.market.market.service.IDepartment;

@Service
public class DepartmentServiceJpa implements IDepartment {

	@Autowired
	private DepartmentRepository departmentRepos;
	
	@Override
	public List<Department> buscarTodas() {
		return departmentRepos.findAll();
	}

}
