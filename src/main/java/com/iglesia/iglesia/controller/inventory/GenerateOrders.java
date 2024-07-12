package com.iglesia.iglesia.controller.inventory;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iglesia.iglesia.model.Order;
import com.iglesia.iglesia.model.Products;
import com.iglesia.iglesia.model.vendors.Vendors;
import com.iglesia.iglesia.service.IOrders;
import com.iglesia.iglesia.service.IProducts;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/generarOrder") 
public class GenerateOrders {
	
    @Autowired
    private IProducts productService;
    
    @Autowired 
    private IOrders orderService;
  
    
    @Autowired
    private JdbcTemplate jdbcTemplate; // Inyecta el bean JdbcTemplate
    
    
    
    @GetMapping("/ventasok")
	public String ventasok(Model model) {
		return "ventas/formGenerarVenta";
	}
    
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Order> list = orderService.buscarTodas();
		model.addAttribute("orders", list);
		return "ventas/listOrders";
	}
        
    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Products product, HttpSession session, Model model) {
        System.out.println("buscando por: " + product);

        // Obtener la lista de productos de la sesión
        List<Products> productosSegundaTabla = (List<Products>) session.getAttribute("productosSegundaTabla");

        // Obtener los productos que coinciden con el criterio de búsqueda
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("Description", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Products> example = Example.of(product, matcher);
        List<Products> lista = productService.buscarByExample(example);

        // Agregar la lista de productos de la segunda tabla al modelo
        model.addAttribute("productosSegundaTabla", productosSegundaTabla);

        // Agregar la lista de productos buscados al modelo
        model.addAttribute("product", lista);

        return "ventas/formGenerarOrders";
    }
       
    
    @GetMapping("/agregarProducto")
    public String agregarProducto(@RequestParam("id") Integer productId, HttpSession session, Model model) {
        // Obtener el producto correspondiente al ID
        Products product = productService.findById(productId);
        
        // Declarar la lista de productos de la segunda tabla
        List<Products> productosSegundaTabla;
        
        // Verificar si el producto existe
        if (product != null) {
            // Obtener la lista de productos de la sesión o inicializarla si es nula
            productosSegundaTabla = (List<Products>) session.getAttribute("productosSegundaTabla");
            if (productosSegundaTabla == null) {
                productosSegundaTabla = new ArrayList<>();
            }
            
            // Verificar si el producto ya está en la lista
            boolean productoExistente = productosSegundaTabla.stream()
                                          .anyMatch(p -> p.getId().equals(productId));

            if (productoExistente) {
                // Manejar el caso donde el producto ya está en la lista
                // Agregar un mensaje de error al modelo
                model.addAttribute("error", "El producto ya está en la lista.");
            } else {
                // Si el producto no está en la lista, agregarlo
                productosSegundaTabla.add(product);
                
                // Actualizar la lista en la sesión
                session.setAttribute("productosSegundaTabla", productosSegundaTabla);
            }
        }
        else {
            // Si el producto es nulo, inicializar la lista de productos de la segunda tabla
            productosSegundaTabla = new ArrayList<>();
        }
        
        // Actualizar el modelo con la lista de productos de la segunda tabla
        model.addAttribute("productosSegundaTabla", productosSegundaTabla);

        // Obtener la lista de productos de la primera tabla
        List<Products> productList = productService.buscarTodas();
        model.addAttribute("products", productList);

        // Retornar la vista principal
        return "ventas/formGenerarOrders";
    }



    
    
    @GetMapping("/eliminarProducto")
    public String eliminarProducto(@RequestParam("id") Integer productId, HttpSession session) {
        // Obtener la lista de productos de la sesión
        List<Products> productosSegundaTabla = (List<Products>) session.getAttribute("productosSegundaTabla");

        // Verificar si la lista existe y si contiene el producto a eliminar
        if (productosSegundaTabla != null) {
            Iterator<Products> iterator = productosSegundaTabla.iterator();
            while (iterator.hasNext()) {
                Products product = iterator.next();
                if (product.getId().equals(productId)) {
                    iterator.remove();
                    break; // Terminar el bucle una vez que se haya eliminado el producto
                }
            }

            // Actualizar la lista en la sesión
            session.setAttribute("productosSegundaTabla", productosSegundaTabla);
        }     
        // Redirigir a la vista principal
        return "redirect:/generarOrder/search";
    }
    
    @PostMapping("/procesarOrden")
    public String procesarOrden(@RequestParam("idProducto") Integer idProducto, 
                                @RequestParam("cantidad") Integer cantidad,
                                HttpSession session, 
                                RedirectAttributes attributes) {
        // Obtener el producto correspondiente al ID
        Products product = productService.findById(idProducto);
        
        if (product != null) {
            // Verificar si la cantidad disponible es suficiente para la orden
            if (product.getQuanty() >= cantidad) {
                // Crear una nueva orden y guardarla en la base de datos
                Order order = new Order();
                
                // Configurar los detalles de la orden
                order.setQuantity(cantidad); // Utilizar la cantidad especificada
                order.setPrice(product.getPrice()); // Calcular el precio total
                
                // Establecer otros detalles de la orden
                order.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(cantidad))); // Subtotal igual al precio total en este caso
                order.setStatus("Pendiente"); // Establecer el estado como pendiente
                
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
                    
                    // Asignar el vendor a la orden
                    order.setVendor(vendor);
                } else {
                    // Manejar la situación donde no se puede obtener el ID del vendor
                    throw new RuntimeException("No se puede obtener el ID del vendor.");
                }
                
                // Asignar el producto a la orden
                order.setIdProduct(product);
                order.setName(product.getName());
                
                // Guardar la orden en la base de datos
                orderService.guardar(order);
                
                // Descuentar la cantidad de productos de la tabla Products
                product.setQuanty(product.getQuanty() - cantidad);
                productService.guardar(product); // Actualizar la cantidad en la base de datos
                
                // Redirigir a la página de búsqueda después de procesar la orden
                return "redirect:/generarOrder/search";
            } else {
                // Manejar la situación donde la cantidad disponible no es suficiente
                attributes.addFlashAttribute("error", "La cantidad disponible del producto no es suficiente");
                return "redirect:/generarOrder/search";
            }
        } else {
            // Manejar la situación donde no se encuentra el producto
            attributes.addFlashAttribute("error", "El producto con ID " + idProducto + " no se encontró.");
            return "redirect:/generarOrder/search";
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
   
    
    @ModelAttribute
	public void setGenericos(Model model){
		Products prodSearch = new Products();
		prodSearch.reset();
		prodSearch.setStatus(null); // Establecer Status como null para evitar su inclusión en la consulta
	    prodSearch.setRegistrationDate(null); // Establecer RegistrationDate como null para evitar su inclusión en la consulta

		model.addAttribute("search", prodSearch);
		model.addAttribute("product", productService.buscarTodas());	
	}
      
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
