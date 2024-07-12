package com.iglesia.iglesia.controller.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/inventory")

public class InventoryController {
	@GetMapping("/home")
	public String mostrarInventory(Model model) {
		model.addAttribute("mensaje", "¡Bienvenido a mi aplicación!");
		return "inventory/home";
	}

}
