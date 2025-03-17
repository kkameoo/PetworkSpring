package com.himedia.kdt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.himedia.mappers")
@ComponentScan(basePackages = { "com.himedia" })
public class PetworkSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetworkSpringApplication.class, args);
	}

}
