package com.example.ejercicio4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
// Maneja la página de login
@GetMapping("/login")
public String loginPage() {
return ("login");} // Retorna la vista login.html desde src/main/resources/templates/}
//Maneja la página de inicio después del login
@GetMapping("/admin/home")
public String homePage() {
return "home"; // Retorna la vista home.html desde src/main/resources/templates/
}
}