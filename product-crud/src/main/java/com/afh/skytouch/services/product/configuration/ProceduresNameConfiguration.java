package com.afh.skytouch.services.product.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@ConfigurationProperties(prefix = "db.stored.procedures")
public class ProceduresNameConfiguration {
    private String findAll;
    private String save;
}
