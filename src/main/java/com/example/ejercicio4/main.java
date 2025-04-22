package com.example.ejercicio4;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class main {
	public static void main(String[] args) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String storedHash = "$2a$10$oJZZy/4HCQ9Sy3wrxyfqOusQufPXWarPTozG5SBoQTQkn4VNp38zW";
	    System.out.println("Matches: " + encoder.matches("admin123", storedHash));
	}

}
