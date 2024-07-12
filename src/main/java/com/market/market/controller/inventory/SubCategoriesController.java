package com.market.market.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.market.market.model.Categories;
import com.market.market.model.Subcategories;
import com.market.market.service.ICategories;
import com.market.market.service.ISubcategories;

@Controller
@RequestMapping(value = "/subcategories")
public class SubCategoriesController {

	@Autowired
	private ICategories categoriesServices;

	@Autowired
	private ISubcategories subcategoryServices;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Subcategories> lista = subcategoryServices.buscarTodas();
		model.addAttribute("subcategories", lista);
		return "subcategorias/listSubcategorias";
	}

	@GetMapping("create")
	public String nueva_subcategory(Model model) {
		List<Categories> categoryList = categoriesServices.buscarTodas();
		model.addAttribute("categories", categoryList);
		model.addAttribute("subcategories", new Subcategories());

		return "subcategorias/formSubcategory";
	}


	@PostMapping("/save")
	public String guardarSubcategories(Subcategories subcategories, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			return "subcategorias/formSubcategory";
		}

		// Obtén la categoría seleccionada por su id
		Categories selectedCategory = categoriesServices.buscarPorId(subcategories.getCategories().getId());

		// Establece la relación entre la subcategoría y la categoría
		subcategories.setCategories(selectedCategory);

		subcategoryServices.guardar(subcategories);

		attributes.addFlashAttribute("msg", "La subcategoria fue guardada correctamente!");
		System.out.println("subcategories:" + subcategories);
		return "redirect:/subcategories/index";
	}
}
