package com.afh.skytouch.commons.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindAllMessage)) return false;
        FindAllMessage that = (FindAllMessage) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
