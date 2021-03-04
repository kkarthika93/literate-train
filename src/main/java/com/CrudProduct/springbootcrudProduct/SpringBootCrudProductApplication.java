package com.CrudProduct.springbootcrudProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.CrudProduct.*")
@EntityScan("com.CrudProduct.*")
public class SpringBootCrudProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudProductApplication.class, args);
		System.out.println("hi");
	}

}
