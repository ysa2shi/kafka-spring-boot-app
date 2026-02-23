package com.example.kafkaspringbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.kafkaspringbootapp.config")
public class KafkaSpringBootAppApplication {

    public static void main(String[] args) {
		SpringApplication.run(KafkaSpringBootAppApplication.class, args);
	}

}
