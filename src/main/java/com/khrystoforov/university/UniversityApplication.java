package com.khrystoforov.university;

import com.khrystoforov.university.model.Department;
import com.khrystoforov.university.model.Lector;
import com.khrystoforov.university.model.enums.Degree;
import com.khrystoforov.university.service.DepartmentService;
import com.khrystoforov.university.service.LectorService;
import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.HashSet;

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

    @Bean
    CommandLineRunner runner(LectorService lectorService,
                             DepartmentService departmentService,
                             ConsoleInterface consoleInterface) {
        return args -> {
            Lector assistant1 = lectorService.createLector(Lector.builder()
                    .name("Kirill Slezandt")
                    .age(26)
                    .degree(Degree.ASSISTANT)
                    .build());

            Lector assistant2 = lectorService.createLector(Lector.builder()
                    .name("Vlad Vladlen")
                    .age(27)
                    .degree(Degree.ASSISTANT)
                    .build());

            Lector assistant3 = lectorService.createLector(Lector.builder()
                    .name("Sergey Aasfa")
                    .age(28)
                    .degree(Degree.ASSISTANT)
                    .build());

            Lector associate_professor1 = lectorService.createLector(Lector.builder()
                    .name("Andrew Megandel")
                    .age(35)
                    .degree(Degree.ASSOCIATE_PROFESSOR)
                    .build());

            Lector associate_professor2 = lectorService.createLector(Lector.builder()
                    .name("Karkaz Vladmer")
                    .age(37)
                    .degree(Degree.ASSOCIATE_PROFESSOR)
                    .build());


            Lector professor1 = lectorService.createLector(Lector.builder()
                    .name("Velgelm Plerton")
                    .age(54)
                    .degree(Degree.PROFESSOR)
                    .build());

            Department department1 = departmentService.createDepartment(Department.builder()
                    .name("Computer science")
                    .head(associate_professor1)
                    .lectors(new HashSet<>())
                    .build());

            Department department2 = departmentService.createDepartment(Department.builder()
                    .name("Biological")
                    .head(professor1)
                    .lectors(new HashSet<>())
                    .build());

            department1.addLectors(assistant1, BigDecimal.valueOf(322.0));
            department1.addLectors(assistant2, BigDecimal.valueOf(322.0));
            department1.addLectors(associate_professor1, BigDecimal.valueOf(1400.0));
            department1.addLectors(professor1, BigDecimal.valueOf(2250.0));
            departmentService.mergeDepartment(department1);

            department2.addLectors(assistant1, BigDecimal.valueOf(346.0));
            department2.addLectors(assistant2, BigDecimal.valueOf(346.0));
            department2.addLectors(assistant3, BigDecimal.valueOf(346.0));
            department2.addLectors(associate_professor1, BigDecimal.valueOf(1100.0));
            department2.addLectors(professor1, BigDecimal.valueOf(2543.0));
            departmentService.mergeDepartment(department2);

            consoleInterface.start();
        };
    }
}
