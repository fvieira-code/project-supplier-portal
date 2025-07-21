package com.portal.supplierportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.projeto.supplierportal")
public class SupplierPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierPortalApplication.class, args);
    }
}