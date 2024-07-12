package com.iglesia.iglesia.controller.inventory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iglesia.iglesia.model.Categories;
import com.iglesia.iglesia.model.Products;

import com.iglesia.iglesia.model.Stores;
import com.iglesia.iglesia.model.Subcategories;
import com.iglesia.iglesia.model.Suppliers;
import com.iglesia.iglesia.model.TypeProducts;
import com.iglesia.iglesia.model.Unit;
import com.iglesia.iglesia.service.ICategories;
import com.iglesia.iglesia.service.IProducts;
import com.iglesia.iglesia.service.IStores;
import com.iglesia.iglesia.service.ISubcategories;
import com.iglesia.iglesia.service.ISuppliers;
import com.iglesia.iglesia.service.ITypeProducts;
import com.iglesia.iglesia.service.IUnit;
import com.iglesia.iglesia.util.Utileria;


@Controller
@RequestMapping(value= "/products")
public class ProductsController {
	
	@Value("${iglesia.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IProducts productService;

	@Autowired
	private ICategories categoriesService;
	
	@Autowired
	private ISubcategories subcateService;
	
	@Autowired
	private IStores storesService;
	
	@Autowired
	private ITypeProducts typeProd;
	
	@Autowired
	private IUnit unitService;
	
	@Autowired
	private ISuppliers supplierService;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Products> list = productService.buscarTodas();
		model.addAttribute("product", list);
		
		return "productos/listProducts";
	}

	
	@GetMapping("/create")
    public String nuevoProduct(Products product, Model model) {
        List<Categories> categoriesList = categoriesService.buscarTodas();
        model.addAttribute("categories", categoriesList);

        List<Subcategories> allSubcategories = subcateService.buscarTodas();
        model.addAttribute("subCategories", allSubcategories);

        // Inicializa el segundo select con todas las subcategorías disponibles
        model.addAttribute("selectedSubcategoryId", null);
        
        List<Stores> storesList = storesService.buscarTodas();
        model.addAttribute("store",storesList);
        
        //List<Products> productsList = productService.buscarTodas();
        //model.addAttribute("product",productsList);
        model.addAttribute("product", productService.buscarTodas());	
        
        List<TypeProducts >typeProducts  =typeProd.buscarTodas();
        model.addAttribute("typeProduct",typeProducts);
        
        List<Unit> unitList =  unitService.buscarTodas();
       //debe ser del tipo string atribbute
        model.addAttribute("unit", unitList);
        
        List<Suppliers> supplierList = supplierService.buscarTodas();
        model.addAttribute("supplier", supplierList);
       

        return "productos/formProducts";
    }

	@GetMapping("/obtenerSubcategoriasPorCategoria")
	@ResponseBody
	public List<Subcategories> obtenerSubcategoriasPorCategoria(@RequestParam Integer idCategoria) {
	    return subcateService.obtenerSubcategoriasPorCategoria(idCategoria);
	}
	
	
	@PostMapping("/save")
	public String guardarProduct(Products product, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		try {
			// Establecer el valor predeterminado para el campo Status
			//product.setStatus(1);
			//product.setVerification(1);
	     


			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					System.out.println("Ocurrió un error: " + error.getDefaultMessage());
				}
				return "productos/formProducts";
			}

			if (!multiPart.isEmpty()) {
				// La ruta se está reemplazando por la inyección de dependencia para guardar
				// imágenes en el archivo application.properties
				// String ruta = "c:/iglesia/img-users/";
				//String ruta = "c:/iglesia/img-users/";
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null) {
					// Significa que la imagen sí se subió y procesamos la variable nombreImagen
					product.setImagen(nombreImagen);
				}
			}

			productService.guardar(product);
			attributes.addFlashAttribute("msg", "El producto fue guardado correctamente!");
			
			System.out.println("Producto guardado: " + product);
			return "redirect:/products/index";

		} catch (Exception e) {
			e.printStackTrace(); // Log de la excepción
			attributes.addFlashAttribute("error", "Error al guardar el usuario.");
			return "redirect:/products/create";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		System.out.println("Borrando producto con id" + id);
		productService.eliminar(id);
		attributes.addFlashAttribute("msg", "El producto se elimino con exito");
		return "redirect:/products/index";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		Products product = productService.findById(id);
		model.addAttribute("products", product);
		
		
		
		 List<Categories> categoriesList = categoriesService.buscarTodas();
	        model.addAttribute("categories", categoriesList);

	        List<Subcategories> allSubcategories = subcateService.buscarTodas();
	        model.addAttribute("subCategories", allSubcategories);

	        // Inicializa el segundo select con todas las subcategorías disponibles
	        model.addAttribute("selectedSubcategoryId", null);
	        
	        List<Stores> storesList = storesService.buscarTodas();
	        model.addAttribute("store",storesList);
	        
	        List<TypeProducts >typeProducts  =typeProd.buscarTodas();
	        model.addAttribute("typeProduct",typeProducts);
	        
	        List<Unit> unitList =  unitService.buscarTodas();
	       //debe ser del tipo string atribbute
	        model.addAttribute("unit", unitList);
	        
	        List<Suppliers> supplierList = supplierService.buscarTodas();
	        model.addAttribute("supplier", supplierList);
	        
	        
		
		return "productos/formProducts";
	}
		
	 	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		
	}
		
	@ModelAttribute
	public void setGenericos(Model model){
		model.addAttribute("product", productService.buscarTodas());	
	}
	
}
