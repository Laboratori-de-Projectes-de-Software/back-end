package jaumesitos.backend.demo;

import jaumesitos.backend.demo.infrastructure.db.dbo.UserDBO;
import jaumesitos.backend.demo.infrastructure.db.repository.SpringDataUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("jaumesitos.backend.demo.infrastructure.res.api") // ✅ Add this if needed
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}