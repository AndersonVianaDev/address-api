package com.anderson.address_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "address-api", version = "1", description = "API for querying and saving addresses"))
public class AddressApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressApiApplication.class, args);
	}

}
