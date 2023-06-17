package br.com.ideale.configs.properties.spring;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix="spring.datasource")
public class DataSourceProperties {

    @NotBlank
    private final String driverClassName;
    @NotBlank
    private final String url ;
    @NotBlank
    private final String username ;
    @NotBlank
    private final String password;

    public DataSourceProperties(String driverClassName, String url, String username, String password){
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
