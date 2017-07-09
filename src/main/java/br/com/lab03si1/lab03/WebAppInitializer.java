package br.com.lab03si1.lab03;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

public class WebAppInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppInitializer.class, args);
		
		
	
	}
	
}

