package com.example.ejercicio4;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encriptador {
	public static void main(String[] args) {
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 String rawPassword = "user123";
		 String encodedPassword = encoder.encode(rawPassword);
		 System.out.println("Contraseña encriptada: " + encodedPassword);
		 }
		
}
