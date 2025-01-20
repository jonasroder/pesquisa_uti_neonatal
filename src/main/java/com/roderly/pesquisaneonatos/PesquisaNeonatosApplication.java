package com.roderly.pesquisaneonatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PesquisaNeonatosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PesquisaNeonatosApplication.class, args);
    }
}
