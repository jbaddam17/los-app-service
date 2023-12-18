package com.loanorignx.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication(scanBasePackages = {"com.loanorignx"})
@OpenAPIDefinition(info = @Info(title = "${info.app.name}", version = "${info.app.version}", description = "${info.app.description}", contact = @Contact(url = "", name = "Jagan Reddy Baddam", email = "jbaddam17@gmail.com")))
public class LosAppServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(LosAppServiceApplication.class, args);
	}
}
