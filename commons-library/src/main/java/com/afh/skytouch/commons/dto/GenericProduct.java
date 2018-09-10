package com.afh.skytouch.commons.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class GenericProduct
{
    private UUID id;
    private String Name;
    private Instant creationDate;
    private String description;

    {
        id = UUID.randomUUID();
        creationDate = Instant.now();
    }
}
