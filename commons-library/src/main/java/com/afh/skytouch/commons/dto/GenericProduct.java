package com.afh.skytouch.commons.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class GenericProduct
{
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String Name;

    @Setter(AccessLevel.NONE)
    private Instant creationDate;

    private String description;

    {
        id = UUID.randomUUID();
        creationDate = Instant.now();
    }
}
