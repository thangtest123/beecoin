package com.example.beecoin;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Collections;

@SpringBootApplication
@EnableMongoAuditing
@EnableAsync
public class BeeCoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeCoinApplication.class, args);
    }

    @Bean
    DozerBeanMapper mapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();

        return beanMapper;
    }

}
