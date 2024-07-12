package com.iglesia.iglesia.controller.vendors;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iglesia.iglesia.model.vendors.ProfilesVendors;
import com.iglesia.iglesia.model.vendors.Vendors;
import com.iglesia.iglesia.service.vendors.IProfilesVendors;
import com.iglesia.iglesia.service.vendors.IVendors;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value ="/vendors")
public class VendorsController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IVendors vendorService;

	@Autowired
	private IProfilesVendors profilVendorService;

	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session, RedirectAttributes attributes) {
		String userName = auth.getName(); // Obtener el nombre de usuario de la autenticación
		System.out.println("Nombre del vendor: " + userName);

		if (session.getAttribute("vendor") == null) {
			Vendors vendor = vendorService.buscarPorUsername(userName);

			if (vendor != null && !vendor.getStatus().equals("1")) { // Verificar el estado de la cuenta
				vendor.setPassword(null);
				System.out.println("Usuario: " + vendor);
				session.setAttribute("vendor", vendor);
			} else {
				// Si la cuenta está desactivada, envía un mensaje a la vista
				attributes.addFlashAttribute("msg",
						"Tu cuenta está desactivada. Por favor, contacta al administrador.");
				return "redirect:/login"; // Redirige a la página de inicio para mostrar el mensaje
			}
		}

		// Si la sesión ya tiene un usuario o si la cuenta no está desactivada, redirige
		// a /home
		return "redirect:/home";
	}

	@GetMapping("/create")
	public String crearVendedor(Model model) {
		Vendors vendor = new Vendors(); // crea una nueva instancia de Vendors
		model.addAttribute("vendor", vendor); // pasa el objeto vendors a la vista
		return "vendors/RegisterVendors"; // devuelve el nombre de la vista que contiene el formulario de creación
	}

	@PostMapping("/save")
	public String guardarVendors(Vendors vendor, BindingResult result, RedirectAttributes attributes) {
		try {
			// Recuperamos el password en texto plano
			String pwdPlano = vendor.getPassword();
			// Encriptamos el pwd BCryptPasswordEncoder
			String pwdEncriptado = passwordEncoder.encode(pwdPlano);
			// Hacemos un set al atributo password (ya viene encriptado)
			vendor.setPassword(pwdEncriptado);
			// Establecer el valor predeterminado para el campo Status
			vendor.setStatus("1");
			vendor.setConfirmEmail(2);

			// Validar que el correo electrónico no sea nulo
			if (vendor.getEmail() == null || vendor.getEmail().isEmpty()) {
				throw new IllegalArgumentException("El campo Email no puede estar vacío");
			}
			
			
			
			// Obtener el ID del perfil de alguna manera adecuada para tu lógica de negocio
			Integer perfilId = vendor.obtenerPerfilId();

			// Buscar el perfil por su ID
			ProfilesVendors perfil = profilVendorService.buscarPorId(perfilId);
			if (perfil == null) {
				throw new IllegalArgumentException("Perfil no encontrado con ID: " + perfilId);
			}

			// Agregar el perfil a la colección de perfiles del vendor
			vendor.getProfilesVendors().add(perfil);

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					System.out.println("Ocurrió un error: " + error.getDefaultMessage());
				}
				return "/vendors/save";
			}
			
			
			// Guardamos el vendor
			vendorService.guardar(vendor);
			      
	        
			
			attributes.addFlashAttribute("msg", "El usuario fue guardado correctamente!");
			System.out.println("Usuario guardado: " + vendor);
			return "redirect:/vendors/login";

		} catch (Exception e) {
			e.printStackTrace(); // Log de la excepción
			attributes.addFlashAttribute("error", "Error al guardar el usuario.");
			return "redirect:/";
		}
	}
	
			
	@GetMapping("/login")
	public String mostrarLogin(Model model) {
		return "loginVendor";
	}


	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
