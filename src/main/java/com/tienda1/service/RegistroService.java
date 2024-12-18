package com.tienda1.service;

import com.tienda1.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface RegistroService {
    
    //Sirve para el pre-registro
    public Model activar(Model model, String usuario, String clave);

    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException;
    
    //Completar el proceso de activación
    public void activar(Usuario usuario, MultipartFile imagenFile);
    
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;
}

