package com.afh.skytouch.commons.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FindAllMessage {

    private String id;

    private List<GenericProduct> products;

    {
        id = UUID.randomUUID().toString();
        products = new ArrayList<>();
    }

    public void addAll(List<GenericProduct> otherProducts){
        products.addAll(otherProducts);
    }

    public List<GenericProduct> getProducts(){
        return new ArrayList<>(products);
    }

    public String getId(){
        return id;
    }

}
