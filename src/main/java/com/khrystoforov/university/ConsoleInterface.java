package com.khrystoforov.university;

import com.khrystoforov.university.controller.UniversityController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Log4j2
public class ConsoleInterface {
    private final UniversityController universityController;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            log.info("Choose an option:");
            log.info("1. Who is the head of the department?");
            log.info("2. Show department statistics.");
            log.info("3. Show average salary for the department.");
            log.info("4. Show count of employees for the department.");
            log.info("5. Global search.");

            int choice = scanner.nextInt();
            scanner.nextLine();
            String departmentName;
            switch (choice) {
                case 1 -> {
                    log.info("Enter department name:");
                    departmentName = scanner.nextLine();
                    log.info(universityController.findHeadOfDepartment(departmentName));
                }
                case 2 -> {
                    log.info("Enter department name:");
                    departmentName = scanner.nextLine();
                    log.info(universityController.showDepartmentStatistics(departmentName));
                }
                case 3 -> {
                    log.info("Enter department name:");
                    departmentName = scanner.nextLine();
                    log.info(universityController.findDepartmentAverageSalary(departmentName));
                }
                case 4 -> {
                    log.info("Enter department name:");
                    departmentName = scanner.nextLine();
                    log.info(universityController.countOfLectorsInDepartment(departmentName));
                }
                case 5 -> {
                    log.info("Enter search template:");
                    String template = scanner.nextLine();
                    log.info(universityController.globalLectorSearch(template));
                }
                default -> log.info("Invalid choice. Please try again.");
            }

            log.info("Do you want to exit? (yes/no)");
            String response = scanner.nextLine().toLowerCase();
            exit = response.equals("yes");
        }

        log.info("Exiting the application.");
    }
}
