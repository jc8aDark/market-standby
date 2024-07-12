package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional; // Agrega esta importación
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Subcategories;
import com.iglesia.iglesia.repository.SubcategoriesRepository;
import com.iglesia.iglesia.service.ISubcategories;

@Service
public class SubcategoriesServiceJpa implements ISubcategories {

    @Autowired
    private SubcategoriesRepository subcategoryRepo;

    @Override
    public List<Subcategories> buscarTodas() {
        return subcategoryRepo.findAll();
    }

    @Override
    public void guardar(Subcategories subcategories) {
        subcategoryRepo.save(subcategories);
    }

    @Override
    public void eliminar(Integer id) {
        subcategoryRepo.deleteById(id);
    }

    @Override
    public Subcategories buscarPorId(Integer id) {
        Optional<Subcategories> optional = subcategoryRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Subcategories> obtenerSubcategoriasPorCategoria(Integer idCategoria) {
        List<Subcategories> todasLasSubcategorias = subcategoryRepo.findAll();
        List<Subcategories> subcategoriasFiltradas = todasLasSubcategorias.stream()
                .filter(subcategoria -> subcategoria.getCategories().getId().equals(idCategoria))
                .collect(Collectors.toList());
        return subcategoriasFiltradas;
    }
}
