package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public IDialect java8TimeDialect() {
	    return new Java8TimeDialect();
	}
}
