package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Departments;
import com.iglesia.iglesia.repository.DepartmentsRepository;
import com.iglesia.iglesia.service.IDepartments;

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
