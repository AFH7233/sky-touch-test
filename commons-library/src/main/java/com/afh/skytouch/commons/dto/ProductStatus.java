package com.afh.skytouch.commons.dto;

import com.afh.skytouch.commons.constans.StatusCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class ProductStatus
{
    @Setter(AccessLevel.NONE)
    private UUID id;

    private UUID productId;

    private StatusCode code;

    private String message;

    {
        id = UUID.randomUUID();
    }

}
