package com.afh.skytouch.managment.controllers;

import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.managment.queue.producers.ProductSender;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ProductSender sender;

    @GetMapping("/showProduct")
    public String getProducts(Model model){
        return showProductsPage;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(productAtributeName) GenericProduct product,Model model){
        sender.sendProduct(product);
        return homePage;
    }

    @GetMapping("/home")
    public String productHome(){
        return homePage;
    }

    @GetMapping("/create")
    public String createProduct(Model model){
        model.addAttribute(productAtributeName,new GenericProduct());
        return creationPage;
    }
}
