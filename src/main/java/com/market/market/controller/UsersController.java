package com.market.market.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.JdbcTemplate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.market.market.model.Departments;
import com.market.market.model.Profiles;
import com.market.market.model.Users;
import com.market.market.model.vendors.Vendors;
import com.market.market.service.IDepartments;
import com.market.market.service.IProfiles;
import com.market.market.service.IUsers;
import com.market.market.util.Utileria;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/users")
public class UsersController {
	
	@Value("${iglesia.ruta.imagenes}")
	private String ruta;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsers usersService;

	@Autowired
	private IProfiles profilService;
	
	@Autowired
	private IDepartments departmentService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate; // Inyecta el bean JdbcTemplate

	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session, RedirectAttributes attributes) {        

	    // Como el usuario ya ingresó, ya podemos agregar a la sesión el objeto usuario.
	    String userName = auth.getName();        
	    System.out.println("Nombre del usuario: " + userName);
	    for (GrantedAuthority rol : auth.getAuthorities()) {
	        System.out.println("ROL: " + rol.getAuthority());
	    }
	    
	    if (session.getAttribute("user") == null) {
	        Users user = usersService.buscarPorUsername(userName);
	        
	        if (user != null && !user.getStatus().equals("1")) { // Verificar el estado de la cuenta
	            user.setPassword(null);
	            System.out.println("Usuario: " + user);
	            session.setAttribute("user", user);
	        } else {
	            // Si la cuenta está desactivada, envía un mensaje a la vista
	            attributes.addFlashAttribute("msg", "Tu cuenta está desactivada. Por favor, contacta al administrador.");
	            return "redirect:/login"; // Redirige a la página de inicio para mostrar el mensaje
	        }
	    }
	    
	    // Si la sesión ya tiene un usuario o si la cuenta no está desactivada, redirige a /home
	    return "redirect:/home";
	}
	
	
		@GetMapping("/mostrarUsuarios")
		public String mostrarIndex(Model model) {		
			List<Users> list = usersService.buscarTodas();
			model.addAttribute("user", list);
	
			List<Profiles> lista = profilService.buscarTodas();
			model.addAttribute("profiles", lista);
	
			return "users/listUsers";
		}

	@GetMapping("/create")
	public String createUsers(Model model, RedirectAttributes attributes) {
					
		Users user = new Users();
		Users userFromFlash = (Users) attributes.getFlashAttributes().get("user");

		// Si hay un user en flash, úsalo; de lo contrario, establece el valor por
		// defecto
		if (userFromFlash != null) {
			user = userFromFlash;
		} else {
			user.setStatus("Activo");
		}
		
		if (user.getImagen() == null || user.getImagen().isEmpty()) {
		    user.setImagen("boxed-bg.jpg");  // Establece el nombre de la imagen predeterminada
		}

		List<Profiles> listProfiles = profilService.buscarTodas();
		List<Departments> departmentList = departmentService.buscarTodas();
		

		model.addAttribute("user", user);
		model.addAttribute("profiles", listProfiles);
		model.addAttribute("departments",departmentList );

		return "users/formUsers";
	}
	
	
	@PostMapping("/save")
	public String guardarUsers(Users user, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart,@RequestParam("profiles.id") Integer perfilId) {
		try {
			// Recuperamos el password en texto plano
			String pwdPlano = user.getPassword();
			// Encriptamos el pwd BCryptPasswordEncoder
			String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
			// Hacemos un set al atributo password (ya viene encriptado)
			user.setPassword(pwdEncriptado);	
			// Establecer el valor predeterminado para el campo Status
			user.setStatus("1");
			// Creamos el Perfil que le asignaremos al usuario nuevo
			//Profiles perfil = new Profiles();
			//perfil.setId(); // Perfil USUARIO
			//user.agregar(perfil);
			Profiles perfil = profilService.buscarPorId(perfilId);
			if (perfil == null) {
			    throw new IllegalArgumentException("Perfil no encontrado con ID: " + perfilId);
			}
	        // Agregar el perfil al conjunto de perfiles del usuario
	        user.agregar(perfil);
		
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					System.out.println("Ocurrió un error: " + error.getDefaultMessage());
				}
				return "users/formUsers";
			}

			if (!multiPart.isEmpty()) {
				// La ruta se está reemplazando por la inyección de dependencia para guardar
				// imágenes en el archivo application.properties
				// String ruta = "c:/iglesia/img-users/";
				//String ruta = "c:/iglesia/img-users/";
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null) {
					// Significa que la imagen sí se subió y procesamos la variable nombreImagen
					user.setImagen(nombreImagen);
				}
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
                user.setVendor(vendor);
            } else {
                // Manejar la situación donde no se puede obtener el ID del vendor
                throw new RuntimeException("No se puede obtener el ID del vendor.");
            }
			
			
			usersService.guardar(user);
			attributes.addFlashAttribute("msg", "El usuario fue guardado correctamente!");
			System.out.println("Usuario guardado: " + user);
			return "redirect:/users/mostrarUsuarios";

		} catch (Exception e) {
			e.printStackTrace(); // Log de la excepción
			attributes.addFlashAttribute("error", "Error al guardar el usuario.");
			return "redirect:/users/create";
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

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		Users user = usersService.buscarPorId(id);
		
		model.addAttribute("user", user);

		// List<Users> list1 = usersService.buscarTodas();
		// model.addAttribute("users", list1);

		List<Profiles> ProfilesList = profilService.buscarTodas();
		model.addAttribute("profiles", ProfilesList);
		
		List<Departments> departmentList = departmentService.buscarTodas();
		model.addAttribute("departments", departmentList);

		return "users/formUsers";
	}
	
	@GetMapping("/login")
	public String mostrarLogin(Model model) {		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes attributes) {
	    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	    logoutHandler.logout(request, null, null);
	    //model.addAttribute("message", "¡Has cerrado sesión exitosamente!");
	    attributes.addFlashAttribute("message", "¡Has cerrado sesión exitosamente!");
	    return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		System.out.println("Borrando el usuario con id" + id);
		usersService.eliminar(id);
		attributes.addFlashAttribute("msg", "El usuario se elimino con exito");
		return "redirect:/users/index";
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
