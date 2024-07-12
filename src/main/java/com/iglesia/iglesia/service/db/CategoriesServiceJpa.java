package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Categories;
import com.iglesia.iglesia.repository.CategoriesRepository;
import com.iglesia.iglesia.service.ICategories;

@Service
public class CategoriesServiceJpa implements ICategories {

	@Autowired
	private CategoriesRepository categoriesRepo;

	@Override
	public void guardar(Categories category) {
		categoriesRepo.save(category);
	}

	@Override
	public List<Categories> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriesRepo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		categoriesRepo.deleteById(id);
	}

	@Override
	public Categories buscarPorId(Integer id) {
		Optional<Categories> optional = categoriesRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
