package com.example.sportradar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages={"com.example.sportradar.*"})
@EnableSwagger2
public class SportradarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportradarApplication.class, args);
	}

}
