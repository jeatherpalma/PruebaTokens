package com.example.demo;

import com.example.demo.configuracion.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class TokensApplication {
       
        /*Se crea un Bean que contienen un filtro hacia las direcciones seleccionadas url que
         requerirán autenticación */
        @Bean
	public FilterRegistrationBean jwtFilter() {
                /*Se crea el filtro de registro el contendrá la url que necesitará autenticación
            en este caso se selecciono (/secure/*), que quiere decir que todas las direcciones que vengan seguidas
            de secure/ necesitarán autenticación por Token*/
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
                /*Este Mapeo se encuentra en la clase SeguridadControlador*/
		registrationBean.addUrlPatterns("/secure/*");

		return registrationBean;
	}
        
	public static void main(String[] args) {
		SpringApplication.run(TokensApplication.class, args);
	}
}
