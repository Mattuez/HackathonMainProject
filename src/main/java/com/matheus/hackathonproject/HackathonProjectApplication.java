package com.matheus.hackathonproject;

import com.matheus.hackathonproject.domain.repository.CustomJpaRepository;
import com.matheus.hackathonproject.infraestructure.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class HackathonProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackathonProjectApplication.class, args);
    }

}
