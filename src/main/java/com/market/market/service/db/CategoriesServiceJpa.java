package com.market.market.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.market.model.Categories;
import com.market.market.repository.CategoriesRepository;
import com.market.market.service.ICategories;

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
