package com.market.market.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class DatabaseWebSecurity {
	
	/*
	@Bean
	UserDetailsManager usersUserDetailsManager(DataSource dataSource) {
	    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
	    users.setUsersByUsernameQuery("SELECT UserName, Password, Status FROM Users WHERE UserName = ?");
	    users.setAuthoritiesByUsernameQuery("SELECT Users.UserName, Profiles.Profile FROM Users " +
	            "INNER JOIN Roles ON Users.Id = Roles.UsersId " +
	            "INNER JOIN Profiles ON Roles.ProfilesId = Profiles.Id " +
	            "WHERE Users.UserName = ?");
	    return users;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(authorize -> authorize
	        // Configuración de autorización para las rutas
	        .requestMatchers("/dist/**","/site/**","/logout/**","/index/**","/plugins/**","/vendors/loginVendor/**","/tinymce/**").permitAll()
	        .requestMatchers("/**","/bcrypt/**","/addProduct/**").permitAll()
	        .requestMatchers("/users/logout/**").permitAll()
	        .requestMatchers("/users/**").hasAnyAuthority("ADMINISTRADOR","VENDEDOR")
	        .requestMatchers("/vendors/**").hasAnyAuthority("ADMINISTRADOR","VENDEDOR")
	        .requestMatchers("/generarOrder/create/**").authenticated()
	       
	        .anyRequest().authenticated());

	    // Configuración del formulario de inicio de sesión para usuarios
	   http.formLogin(form -> form
	        .loginPage("/users/login")
	        .permitAll());
	   return http.build();
	}*/
	
	@Bean
	UserDetailsManager vendorsUserDetailsManager(DataSource dataSource) {
	    JdbcUserDetailsManager vendors = new JdbcUserDetailsManager(dataSource);
	    vendors.setUsersByUsernameQuery("SELECT UserName, Password, Status FROM Vendors WHERE UserName = ?");
	    vendors.setAuthoritiesByUsernameQuery("SELECT Vendors.UserName, ProfilesVendors.Profile FROM Vendors " +
	            "INNER JOIN RolesVendors ON Vendors.Id = RolesVendors.VendorsId " +
	            "INNER JOIN ProfilesVendors ON RolesVendors.ProfilesId = ProfilesVendors.Id " +
	            "WHERE Vendors.UserName = ?");
	    return vendors;
	}
	
	 @Bean
	    SecurityFilterChain vendorSecurityFilterChain(HttpSecurity http) throws Exception {
	        http.authorizeHttpRequests(authorize -> authorize
	            // Configuración de autorización para las rutas de vendedores
	        	.requestMatchers("/**","/bcrypt/**","/dist/**","/site/**","/logout/**","/index/**","/plugins/**","/tinymce/**").permitAll()
	            .requestMatchers("/vendors/**").hasAnyAuthority("ADMINISTRADOR", "VENDEDOR")
	            .requestMatchers("/generarOrder/**").authenticated()
	            .anyRequest().authenticated());

	        // Configuración del formulario de inicio de sesión para vendedores
	        http.formLogin(form -> form
	            .loginPage("/vendors/login")
	            .permitAll());
	        return http.build();
	    }
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
