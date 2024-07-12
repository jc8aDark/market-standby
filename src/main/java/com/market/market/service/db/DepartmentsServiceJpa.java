package com.market.market.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Departments;
import com.market.market.repository.DepartmentsRepository;
import com.market.market.service.IDepartments;

@Service

public class DepartmentsServiceJpa implements IDepartments {

	@Autowired
	private DepartmentsRepository departmentRepo;

	@Override
	public List<Departments> buscarTodas() {

		return departmentRepo.findAll();
	}

	@Override
	public void guardar(Departments departments) {
		departmentRepo.save(departments);

	}

	@Override
	public void eliminar(Integer id) {
		departmentRepo.deleteById(id);

	}

	@Override
	public Departments buscarPorId(Integer id) {
		Optional<Departments> optional = departmentRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
