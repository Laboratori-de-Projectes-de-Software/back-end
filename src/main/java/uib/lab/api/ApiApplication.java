package uib.lab.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ApiApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
