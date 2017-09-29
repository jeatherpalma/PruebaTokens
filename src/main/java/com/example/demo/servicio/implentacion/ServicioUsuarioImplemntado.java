/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.servicio.implentacion;

import com.example.demo.entidad.Usuario;
import com.example.demo.repositorio.RepositorioUsuario;
import com.example.demo.servicio.ServicioUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarioImplemntado implements ServicioUsuario{
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    public Usuario save(Usuario user){
        return repositorioUsuario.save(user);
    }
    
    public Usuario findByNombre(String nombre){
        return repositorioUsuario.findByNombre(nombre);
    }
    
    public List<Usuario> findAll(){
        return repositorioUsuario.findAll();
    }
}
