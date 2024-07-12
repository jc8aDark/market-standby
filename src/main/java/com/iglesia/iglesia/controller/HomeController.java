package com.iglesia.iglesia.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iglesia.iglesia.model.Products;
import com.iglesia.iglesia.service.IProducts;


@Controller
public class HomeController {
	@Autowired
	private IProducts prodService;
	
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		List<Products> list = prodService.buscarTodas();
		model.addAttribute("product", list);
		
		//return "productos/listProducts";
		return "index";
	}
	
	@GetMapping("/addProduct")
    public String detailProducto(Model model) {
		List<Products> list = prodService.buscarTodas();
		model.addAttribute("product", list);
        return "sites/products/addProduct";
    }
	
	@GetMapping("/home")
	public String mostrarHome(Model model) {
		
		//List<Users> list= usersService.buscarTodas();
        //model.addAttribute("cantidadUsuarios", list);
		return "home";
	}
	  
	
	@GetMapping("/register")
	public String registrerView() {
		return "register";
	}
	
}
