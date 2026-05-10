package io.github.dougllasfps.salesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SalesApiApplication {

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String getEnvironment() {
        String currentEnvironment = "DEFAULT (none)";

        if (env.getActiveProfiles().length > 0) {
            currentEnvironment = env.getActiveProfiles()[0];
        }

        String appName = env.getProperty("spring.application.name");

        return String.format("Environment: %s | App Name: %s", currentEnvironment, appName);
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesApiApplication.class, args);
    }

}
