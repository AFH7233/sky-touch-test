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

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProduct(@PathVariable UUID uuid){
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody GenericProduct product){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/create")
    public String createProduct(){
        return "create-product";
    }
}
