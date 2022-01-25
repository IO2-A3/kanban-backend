package com.example.kanbanbackend;

import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;


@EnableSwagger2
@SpringBootApplication
public class KanbanBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanBackendApplication.class, args);
	}
}
