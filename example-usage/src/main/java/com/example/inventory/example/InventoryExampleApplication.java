package com.example.inventory.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.example.inventory.config.InventoryConfig;

@SpringBootApplication
@Import(InventoryConfig.class)
public class InventoryExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryExampleApplication.class, args);
    }
}
