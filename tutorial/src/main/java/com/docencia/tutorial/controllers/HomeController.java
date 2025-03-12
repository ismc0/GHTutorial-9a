package com.docencia.tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to Spring Boot");
        model.addAttribute("subtitle", "A Spring Boot Eafit App");
        return "home/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us - Online Store");
        model.addAttribute("subtitle", "About Us");
        model.addAttribute("description", "This is an about page ...");
        model.addAttribute("author", "Developed by: Isabella Márquez");
        return "home/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contáctanos");
        model.addAttribute("email", "contacto@tienda.com");
        model.addAttribute("address", "Calle C 123, Ciudad M, 12345");
        model.addAttribute("phone", "(123)4567890");
        return "home/contact";
    }
}
