package com.tienda1.service.impl;


import com.tienda1.dao.CategoriaDao;
import com.tienda1.domain.Categoria;
import com.tienda1.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;
    
    
    @Override
    public List<Categoria> getCategorias(boolean activos) {
       List<Categoria> lista = categoriaDao.findAll();
       
       if (activos) {
           //Remueve de la lista los elemetos donde
           //el atributo activo es false
           lista.removeIf(e -> !e.isActivo());
       }
       
       return lista;
    }
    
}
