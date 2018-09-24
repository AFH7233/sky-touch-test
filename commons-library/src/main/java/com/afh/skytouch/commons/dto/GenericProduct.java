package com.afh.skytouch.commons.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity(name="generic_product")
@Data
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="findAll",procedureName = "find_all",resultClasses = GenericProduct.class),
        @NamedStoredProcedureQuery(name="save",procedureName = "insert_product",
        parameters = {
                @StoredProcedureParameter(name="id_param",mode = ParameterMode.IN,type  = String.class),
                @StoredProcedureParameter(name="product_name_param",mode = ParameterMode.IN,type  = String.class),
                @StoredProcedureParameter(name="creation_date_param",mode = ParameterMode.IN,type  = Date.class),
                @StoredProcedureParameter(name="description_param",mode = ParameterMode.IN,type  = String.class),
        })

})
public class GenericProduct
{
    @Setter(AccessLevel.NONE)
    @Id
    @Column(name="id")
    private String id;

    @Column(name="product_name")
    private String name;

    @Column(name="creation_date")
    @Setter(AccessLevel.NONE)
    private Date creationDate;

    @Column(name="description")
    private String description;

    public GenericProduct() {
        id = UUID.randomUUID().toString();
        creationDate = Date.from(Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericProduct)) return false;
        GenericProduct that = (GenericProduct) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
