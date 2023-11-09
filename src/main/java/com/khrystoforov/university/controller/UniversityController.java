package com.khrystoforov.university.controller;

import com.khrystoforov.university.dto.LectorDTO;
import com.khrystoforov.university.model.enums.Degree;
import com.khrystoforov.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    public LectorDTO findHeadOfDepartment(String departmentName) {
        return universityService.findDepartmentHead(departmentName);
    }

    public Map<Degree, Long> showDepartmentStatistics(String departmentName) {
        return universityService.countLectorsInDepartmentByDegree(departmentName);
    }


    public BigDecimal findDepartmentAverageSalary(String departmentName) {
        return universityService.findAverageSalaryInDepartment(departmentName);
    }

    public Integer countOfLectorsInDepartment(String departmentName) {
        return universityService.countOfLectorsForDepartment(departmentName);
    }

    public Set<LectorDTO> globalLectorSearch(String template) {
        return universityService.globalLectorsSearch(template);
    }
}
