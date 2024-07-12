package com.market.market.controller.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.market.market.model.Categories;
import ccom.market.market.service.ICategories;
import com.market.market.util.Utileria;

@Controller
@RequestMapping(value = "/categories")
public class CategoriesController {
	
	@Value("${iglesia.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private ICategories categorieService;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categories> list = categorieService.buscarTodas();
		model.addAttribute("categories", list);

		return "categorias/listCategorias";
	}


	@GetMapping("/create")
	public String createCategories(Model model, RedirectAttributes attributes) {
		Categories categorie = new Categories();

		model.addAttribute("categories", categorie);

		return "categorias/formCategory";
	}

	@PostMapping("/save")
	public String guardarCategories(Categories category, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		try {

			if (!multiPart.isEmpty()) {
				// La ruta se está reemplazando por la inyección de dependencia para guardar
				// imágenes en el archivo application.properties
				// String ruta = "c:/iglesia/img-users/";
				//String ruta = "c:/iglesia/img-users/";
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null) {
					// Significa que la imagen sí se subió y procesamos la variable nombreImagen
					category.setImagen(nombreImagen);
				}
			}

			categorieService.guardar(category);
			attributes.addFlashAttribute("msg", "El usuario fue guardado correctamente!");
			System.out.println("Categoria guardada: " + category);
			return "redirect:/categories/index";

		} catch (Exception e) {
			e.printStackTrace(); // Log de la excepción
			attributes.addFlashAttribute("error", "Error al guardar el usuario.");
			return "redirect:/categories/create";
		}
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		Categories categorie = categorieService.buscarPorId(id);
		model.addAttribute("categories", categorie);

		// List<Users> list1 = usersService.buscarTodas();
		// model.addAttribute("users", list1);

		return "categorias/formCategory";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		System.out.println("Borrando la categoria con id" + id);
		categorieService.eliminar(id);
		attributes.addFlashAttribute("msg", "La categoria se elimino con exito");
		return "redirect:/categories/index";
	}

}
