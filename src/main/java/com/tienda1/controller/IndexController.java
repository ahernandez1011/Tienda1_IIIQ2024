package com.tienda1.controller;

import com.tienda1.domain.Item;
import com.tienda1.service.ItemService;
import com.tienda1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    
    @Autowired
    ProductoService productoService;
    
    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/")
    public String page(Model model) { 
        var listaProductos = productoService.getProductos(true);
        model.addAttribute("productos", listaProductos);
        return "index";
    }
		
    @RequestMapping("/refrescarBoton")
    public ModelAndView refrescarBoton(Model model) { 
        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }    
}

