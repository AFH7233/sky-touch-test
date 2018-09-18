package com.afh.skytouch.commons.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class GenericProduct
{
    @Setter(AccessLevel.NONE)
    @NonNull
    private UUID id;

    @NonNull
    private String name;

    @Setter(AccessLevel.NONE)
    @NonNull
    private Instant creationDate;

    private String description;

    {
        id = UUID.randomUUID();
        creationDate = Instant.now();
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof GenericProduct){
            GenericProduct otherProduct = (GenericProduct)object;
            return otherProduct.getId().equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
}
