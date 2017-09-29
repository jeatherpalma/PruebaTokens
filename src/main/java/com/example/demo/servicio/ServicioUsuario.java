/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.servicio;

import com.example.demo.entidad.Usuario;
import java.util.List;


public interface ServicioUsuario {
    Usuario save(Usuario user);
    List<Usuario> findAll();
    Usuario findByNombre(String nombre);
}
