package com.tienda1.controller;

import com.tienda1.domain.Categoria;
import com.tienda1.domain.Producto;
import com.tienda1.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tienda1.service.ProductoService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(categoria).getProductos();
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }
    
    //Los métodos siguientes son para la prueba de consultas ampliadas
    @GetMapping("/consulta")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        return "/pruebas/listado2";
    }

    @PostMapping("/query1")
    public String consultaQuery1(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model model) {
        List<Producto> productos = productoService.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query2")
    public String consultaQuery2(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model model) {
        List <Producto> productos = productoService.metodoJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);        
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }

     @PostMapping("/query3")
    public String consultaQuery3(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model model) {
        List <Producto> productos = productoService.metodoNativo(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }
    
     @PostMapping("/QueryName")
    public String consultaQueryName(@RequestParam(value = "productName") String nombre, Model model) {
        List <Producto> productos = productoService.buscarPorNombre(nombre);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("productName", nombre);
        return "/pruebas/listado2";
    }

    @PostMapping("/consultaDisponibilidad")
    public String consultaDisponibilidad(@RequestParam(value = "activo") boolean activo,
            @RequestParam(value = "existenciasMinimas") int existenciasMinimas,
            Model model) {
        List<Producto> productos = productoService.buscarPorDisponibilidadYEstado(activo, existenciasMinimas);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("activo", activo);
        model.addAttribute("existenciasMinimas", existenciasMinimas);
        return "/pruebas/listado2"; // Asegúrate de que esta vista esté configurada
    }
    
}
