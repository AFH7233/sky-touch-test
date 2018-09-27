package com.afh.skytouch.services.product.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@ConfigurationProperties(prefix = "db.stored.procedures.insert.parameters")
public class InsertProcedureConfiguration {
    private String id;
    private String productName;
    private String creationDate;
    private String description;
}
