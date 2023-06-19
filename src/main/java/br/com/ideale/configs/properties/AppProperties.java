package br.com.ideale.configs.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import br.com.ideale.configs.properties.spring.DataSourceProperties;
import br.com.ideale.configs.properties.spring.JpaHibernateProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@ConfigurationProperties(prefix="app.properties")
public class AppProperties {

    @Autowired
    private JpaHibernateProperties jpaHibernateProperties;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private final Boolean trace;

    public AppProperties(@NotNull Boolean trace){
        this.trace = trace;
    }
}
