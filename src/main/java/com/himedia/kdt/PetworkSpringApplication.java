package com.himedia.kdt;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.himedia.mappers")

@ComponentScan(basePackages = { "com.himedia" })

public class PetworkSpringApplication {

	public static void main(String[] args) throws Exception {
		File envFile = new File(".env");

	    if (envFile.exists()) {
	        Properties env = new Properties();
	        env.load(new FileInputStream(envFile));
	        env.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
	        System.out.println(".env 파일을 로드했습니다.");
	    } else {
	            System.out.println(".env 파일이 없어 환경변수를 로드하지 않습니다.");
	    }
		SpringApplication.run(PetworkSpringApplication.class, args);
	}

}
