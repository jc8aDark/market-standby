package com.market.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
