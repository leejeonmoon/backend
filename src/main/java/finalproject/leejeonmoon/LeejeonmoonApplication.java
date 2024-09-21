package finalproject.leejeonmoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LeejeonmoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeejeonmoonApplication.class, args);
    }

}
