package com.docencia.tutorial.controllers;

import com.docencia.tutorial.models.Product;
import com.docencia.tutorial.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String index(HttpSession session, Model model) {

        Map<Long, Integer> cartProductData = (Map<Long, Integer>) session.getAttribute("cart_product_data");
        Map<Long, Product> cartProducts = new HashMap<>();
        if (cartProductData != null) {
            for (Long id : cartProductData.keySet()) {
                Optional<Product> product = productRepository.findById(id);
                product.ifPresent(p -> cartProducts.put(id, p));
            }
        }


        Iterable<Product> allProducts = productRepository.findAll();
        model.addAttribute("title", "Cart - Online Store");
        model.addAttribute("subtitle", "Shopping Cart");
        model.addAttribute("cartProductData", cartProductData);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("products", allProducts); // Agregar todos los productos al modelo

        return "cart/index";
    }


    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id, HttpSession session) {

        Map<Long, Integer> cartProductData = (Map<Long, Integer>) session.getAttribute("cart_product_data");

        if (cartProductData == null) {
            cartProductData = new HashMap<>();
        }

        cartProductData.put(id, cartProductData.getOrDefault(id, 0) + 1);
        session.setAttribute("cart_product_data", cartProductData);

        System.out.println("Producto agregado al carrito. Estado actual:");
        System.out.println(cartProductData);

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session) {
        Map<Long, Integer> cartProductData = (Map<Long, Integer>) session.getAttribute("cart_product_data");

        if (cartProductData != null && cartProductData.containsKey(id)) {
            cartProductData.remove(id);
            session.setAttribute("cart_product_data", cartProductData);
        }

        return "redirect:/cart";
    }

    @GetMapping("/removeAll")
    public String removeAll(HttpSession session) {
        session.removeAttribute("cart_product_data");
        return "redirect:/cart";
    }
}
