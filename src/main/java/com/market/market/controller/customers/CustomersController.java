package com.market.market.controller.customers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")

public class CustomersController {

	@GetMapping("/create")
	public String createCostumer(Model model) {
		return "customers/customersForm";
	}
}
