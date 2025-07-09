package org.example.diplomabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DiplomaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaBackendApplication.class, args);
    }

}
