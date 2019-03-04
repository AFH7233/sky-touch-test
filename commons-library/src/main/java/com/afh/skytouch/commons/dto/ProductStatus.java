package com.afh.skytouch.commons.dto;

import com.afh.skytouch.commons.constans.StatusCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Data
public class ProductStatus
{
    @Setter(AccessLevel.NONE)
    private String id;

    private String productId;

    private StatusCode code;

    private String message;

    {
        id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStatus)) return false;
        ProductStatus status = (ProductStatus) o;
        return Objects.equals(getId(), status.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
