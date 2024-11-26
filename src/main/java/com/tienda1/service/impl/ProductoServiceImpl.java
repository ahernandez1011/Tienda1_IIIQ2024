package com.tienda1.service.impl;


import com.tienda1.dao.ProductoDao;
import com.tienda1.domain.Producto;
import com.tienda1.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;
    
    @Override
    public List<Producto> getProductos(boolean activos) {
       List<Producto> lista = productoDao.findAll();
       
       if (activos) {
           //Remueve de la lista los elemetos donde
           //el atributo activo es false
           lista.removeIf(e -> !e.isActivo());
       }
       
       return lista;
    }
     @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup) {
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    @Override
    @Transactional(readOnly=true)    
    public List<Producto> metodoJPQL(double precioInf, double precioSup) {
        return productoDao.metodoJPQL(precioInf, precioSup);
    }

    @Override
    @Transactional(readOnly=true)    
    public List<Producto> metodoNativo(double precioInf, double precioSup) {
        return productoDao.metodoNativo(precioInf, precioSup);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Producto> buscarPorNombre(String nombre) {
       return productoDao.findByDescripcionContainingOrderByPrecio(nombre);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorDisponibilidadYEstado(boolean activo, int existenciasMinimas) {
        return productoDao.findByActivoAndExistenciasGreaterThan(activo, existenciasMinimas);
    }
    
}
