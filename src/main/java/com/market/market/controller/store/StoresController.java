package com.market.market.controller.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.market.market.model.Countries;
import com.market.market.model.Department;
import com.market.market.model.Stores;
import com.market.market.model.vendors.Vendors;
import com.market.market.service.ICountries;
import com.market.market.service.IDepartment;
import com.market.market.service.IStores;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/stores")
public class StoresController {
	
	@Value("${iglesia.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private ICountries countriesService;
	
	@Autowired
	private IDepartment departmentService;
	
	@Autowired
	private IStores storeService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate; // Inyecta el bean JdbcTemplate
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Stores> lista = storeService.buscarTodas();
		model.addAttribute("store", lista);
		return "stores/listStores";
	}
	
	@GetMapping("create")
	public String nueva_store(Model model) {
		List<Countries> countriesList = countriesService.buscarTodas();
		List<Department> departmentList = departmentService.buscarTodas();
		
		
		model.addAttribute("countries", countriesList);
		model.addAttribute("department", departmentList);
		model.addAttribute("stores", new Stores());
		

		return "stores/formStores";
	}
	
		    
	    @PostMapping("/save")
	    public String guardarStores(Stores store, BindingResult result, RedirectAttributes attributes,
	                                @RequestParam("archivoImagen") MultipartFile multiPart, HttpSession session) {
	        try {
	            // Establecer el valor predeterminado para el campo Status
	            store.setStatus(1);
	            store.setVerification(1);

	            // Obtener el objeto Authentication que contiene los detalles del usuario autenticado
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            String username = authentication.getName(); // Obtener el nombre de usuario del vendedor autenticado

	            // Consultar el ID del vendedor por su nombre de usuario
	            Integer idVendor = obtenerIdVendorPorUsername(username);

	            // Si se puede obtener el ID del vendedor
	            if (idVendor != null) {
	                // Crear un objeto Vendors con el ID obtenido
	                Vendors vendor = new Vendors();
	                vendor.setId(idVendor);

	                // Asignar el vendor a la tienda
	                store.setVendor(vendor);
	            } else {
	                // Manejar la situación donde no se puede obtener el ID del vendor
	                throw new RuntimeException("No se puede obtener el ID del vendor.");
	            }

	            // Resto del código para guardar la tienda...

	            // Guardar la tienda
	            storeService.guardar(store);
	            attributes.addFlashAttribute("msg", "La tienda fue guardada correctamente!");
	            return "redirect:/stores/index";

	        } catch (Exception e) {
	            e.printStackTrace(); // Log de la excepción
	            attributes.addFlashAttribute("error", "Error al guardar la tienda.");
	            return "redirect:/stores/create";
	        }
	    }

	    // Método para obtener el ID del vendedor por su nombre de usuario
	    private Integer obtenerIdVendorPorUsername(String username) {
	        // Consulta SQL para obtener el ID del vendedor por su nombre de usuario
	        String sql = "SELECT Id FROM Vendors WHERE UserName = ?";
	        // Ejecutar la consulta y obtener el resultado
	        Integer idVendor = jdbcTemplate.queryForObject(sql, Integer.class, username);
	        return idVendor;
	    }
	

}
