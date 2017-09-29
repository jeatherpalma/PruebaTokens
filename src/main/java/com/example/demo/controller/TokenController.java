package com.example.demo.controller;

import com.example.demo.entidad.Usuario;
import com.example.demo.servicio.ServicioUsuario;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TokenController {
    private BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
    @Autowired
    private ServicioUsuario repositorioUsuario;
    
    /*Este método lista todos los usuarios utilizando el servicio JpaRepository*/
    @RequestMapping("/")
    public List<Usuario> listaUsuarios(){
        return repositorioUsuario.findAll();
    }
    
    /*Método para insertar un nuevo usuario en la base de datos, se puso para pruebas*/
    @RequestMapping(method = RequestMethod.POST, path = "/registro")
    public String registro(@RequestBody Usuario usuario){
        String passwordencode = bcryptpasswordencoder.encode(usuario.getPassword());
        usuario.setPassword(passwordencode);
        repositorioUsuario.save(usuario);
        return "Usuario registrado";
    }
    
    /*Método de login el cual necesita un nombre y una contraseña para verificar si se
    encuentra en la base de datos y crear un Token (el método es String sólo para ver
    el Token que se genero)*/
    @RequestMapping("/login")
    public String login(@RequestBody Usuario login)throws ServletException{
        /*String en donde se almacenará el Token*/
        String jwtToken = "";
        
        /*If para verigficar si en la petición se mando el nombre y la contraseña*/
        if(login.getNombre() == null || login.getPassword()== null){
            throw  new ServletException("Por favor inserta tu usuario y contraseña");
        }
        
        /*Variables del login nombre y contrasena que se recuperán de la petición*/
        String usuario = login.getNombre();
        String contrasena = login.getPassword();
        
        /*Se crea un nuevo usuario a través del método findByNombre, el cual realiza
        una consula en la base de datos para verificar si el usuario se ecnuentra en
        la base de datos*/
        Usuario usuario1 = repositorioUsuario.findByNombre(usuario);
        
        /*Si el usuario es nulo queiere decir que no se encuentra el usuario*/
        if(usuario1 == null){
            //Exception documentada que regresa el error se prefirió dejar un String
            //throw  new ServletException("Usuario no encontrado");
            return "Usuario no encontrado";
        }
        /*Si el usuario se encuentra se verifica la contraseña de la base de datos con,
        la que el usuario envió en la petición*/
        String pwd = usuario1.getPassword();
        /*Si son diferentes el login es incorrecto*/
        if(!bcryptpasswordencoder.matches(contrasena, pwd)){
           // throw new ServletException("Login invalido verifique su password");
           return "Login invalido verifique su password";
        }
        /*se crea el token utilizando algoritmo de encriptación HS256*/
        jwtToken = Jwts.builder().setSubject(usuario).claim("roles", "admin").signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        //Se regresa el Token que se genero
        return jwtToken;
        
    }
    
    
}
