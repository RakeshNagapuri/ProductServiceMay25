package com.productservice.productservicemay25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductServiceMay25Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceMay25Application.class, args);
    }

}
