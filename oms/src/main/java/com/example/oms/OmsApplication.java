package com.example.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.example.inventory.config.InventoryConfig;

@SpringBootApplication
@Import(InventoryConfig.class)
@EntityScan(basePackages = "com.example.oms.model")
@EnableJpaRepositories(basePackages = "com.example.oms.repository")
public class OmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class, args);
    }
}
