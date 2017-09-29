
package com.example.demo.controller;

import com.example.demo.servicio.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SeguridadControlador {
    @Autowired
    private ServicioUsuario servicioUsuario;
    
    @RequestMapping("/user/users")
    public String loginCorrecto(){
        return "Login correcto";
    }
}
