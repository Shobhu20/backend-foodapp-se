package com.windsor.foodapp.foodapp;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class FoodappApplication {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodappApplication.class, args);
	}
}
