package com.market.market.controller.inventory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.market.market.model.Suppliers;
import com.market.market.model.vendors.Vendors;
import com.market.market.service.ISuppliers;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/suppliers")
public class SuppliersController {
	
	
	@Autowired
	private ISuppliers supplierService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate; // Inyecta el bean JdbcTemplate
	
	@GetMapping("/listaProveedores")
	public String mostrarIndex(Model model) {
		List<Suppliers> list = supplierService.buscarTodas();
		model.addAttribute("supplier", list);

		return "suppliers/listSuppliers";
	}
	
	 @GetMapping("/create")
	    public String addSuppliers(Model model) {
	        //List<Suppliers> suppliers = supplierService.buscarTodas();
	        model.addAttribute("supplier", new Suppliers()); // Cambiado a "supplier" en singular
	        return "suppliers/formSuppliers";
	    }
	

	 @PostMapping("/save")
	 public String guardarSuppliers(@ModelAttribute("supplier") Suppliers supplier, BindingResult result,
	                                RedirectAttributes attributes,HttpSession session) {
		 supplier.setStatus("1");

	     if (result.hasErrors()) {
	         for (ObjectError error : result.getAllErrors()) {
	             System.out.println("Ocurrió un error: " + error.getDefaultMessage());
	         }
	         return "suppliers/formSuppliers";
	     }
	     
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
             supplier.setVendor(vendor);
            
         } else {
             // Manejar la situación donde no se puede obtener el ID del vendor
             throw new RuntimeException("No se puede obtener el ID del vendor.");
         }
	     
	     

	     supplierService.guardar(supplier);

	     attributes.addFlashAttribute("msg", "El proveedor fue guardado correctamente!");
	     System.out.println("Proveedor guardado: " + supplier);
	     return "redirect:/suppliers/listaProveedores";
	     
	     
	 }
	 
	 // Método para obtener el ID del vendedor por su nombre de usuario
	    private Integer obtenerIdVendorPorUsername(String username) {
	        // Consulta SQL para obtener el ID del vendedor por su nombre de usuario
	        String sql = "SELECT Id FROM Vendors WHERE UserName = ?";
	        // Ejecutar la consulta y obtener el resultado
	        Integer idVendor = jdbcTemplate.queryForObject(sql, Integer.class, username);
	        return idVendor;
	    }
	 
	 @InitBinder
		public void initBinder(WebDataBinder webDataBinder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}


}
