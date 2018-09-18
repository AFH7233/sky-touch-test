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

    private static final String PRODUCT_ATTRIBUTE = "product";
    private static final String CREATE = "create-product";
    private static final String HOME = "home";
    private static final String SHOW_PRODUCTS = "list-products";

    private ProductSender sender;

    @Autowired
    public void setProductSender(ProductSender sender){
        this.sender = sender;
    }

    @GetMapping("/showProduct")
    public String getProducts(Model model){
        return SHOW_PRODUCTS;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(PRODUCT_ATTRIBUTE) GenericProduct product,Model model){
        sender.sendProduct(product);
        return HOME;
    }

    @GetMapping("/home")
    public String productHome(){
        return HOME;
    }

    @GetMapping("/create")
    public String createProduct(Model model){
        model.addAttribute(PRODUCT_ATTRIBUTE,new GenericProduct());
        return CREATE;
    }
}
