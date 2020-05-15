package com.airtel.xlabs.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Springboot Launcher for application.
 * @author :Pradeep Kumar
 * @modified : 03-May-2020
 */
@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationLauncher.class, args);

    }
}