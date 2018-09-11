package com.afh.skytouch.managment.controllers;

import com.afh.skytouch.commons.dto.GenericProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/genericProduct")
public class GenericProductController {

    private static final String productAtributeName = "product";
    private static final String creationPage = "create-product";
    private static final String homePage = "home";
    private static final String showProductsPage = "list-products";

    @GetMapping("/showProduct")
    public String getProducts(Model model){
        return showProductsPage;
    }

    @PostMapping("")
    public String addProduct(@ModelAttribute(productAtributeName) GenericProduct product,Model model){
        model.addAttribute("message","The product with the id: " + product.getId() + " has been created");
        return homePage;
    }

    @GetMapping("")
    public String productHome(){
        return homePage;
    }

    @GetMapping("/create")
    public String createProduct(Model model){
        model.addAttribute(productAtributeName,new GenericProduct());
        return creationPage;
    }
}
