package br.com.ideale;

import br.com.ideale.configs.properties.AppProperties;
import br.com.ideale.configs.properties.spring.DataSourceProperties;
import br.com.ideale.configs.properties.spring.JpaHibernateProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties({AppProperties.class, DataSourceProperties.class, JpaHibernateProperties.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}