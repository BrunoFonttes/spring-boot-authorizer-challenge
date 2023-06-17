package br.com.ideale.configs.properties.spring;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix="spring.jpa.hibernate")
public class JpaHibernateProperties {

    @NotBlank
    private final String ddlAuto;

    public JpaHibernateProperties(String ddlAuto) {
        this.ddlAuto = ddlAuto;
    }

}
