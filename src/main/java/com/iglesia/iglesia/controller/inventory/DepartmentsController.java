package com.iglesia.iglesia.controller.inventory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iglesia.iglesia.model.Departments;
import com.iglesia.iglesia.service.IDepartments;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentsController {
	@Autowired
	private IDepartments deparmentServi;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Departments> lista = deparmentServi.buscarTodas();
		model.addAttribute("departments", lista);
		return "departamentos/listDepartments";
	}

	@GetMapping("/create")
	public String nuevoDepartamento(Departments departments, Model model) {
		return "departamentos/formDepartamentos";
	}

	@PostMapping("/save")
	public String guardarDepartments(Departments departments, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error:" + error.getDefaultMessage());
			}
			return "departamentos/formDepartamentos";
		}

		deparmentServi.guardar(departments);
		attributes.addFlashAttribute("msg", "El departamento fue guardado correctamente!");
		System.out.println("department:" + departments);
		return "redirect:/departments/index";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		System.out.println("Borrando departamento con id" + id);
		deparmentServi.eliminar(id);
		attributes.addFlashAttribute("msg", "El departamento se elimino con exito");
		return "redirect:/departments/index";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		Departments departments = deparmentServi.buscarPorId(id);
		model.addAttribute("departments", departments);
		return "departamentos/formDepartamentos";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
