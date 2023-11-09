package com.khrystoforov.university;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        value = "com.khrystoforov.university.repository",
        repositoryBaseClass = BaseJpaRepositoryImpl.class
)
@RequiredArgsConstructor
public class UniversityApplication {
    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

}
