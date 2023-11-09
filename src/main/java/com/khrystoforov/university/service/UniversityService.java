package com.khrystoforov.university.service;

import com.khrystoforov.university.dto.LectorDTO;
import com.khrystoforov.university.mapper.LectorMapper;
import com.khrystoforov.university.model.Department;
import com.khrystoforov.university.model.DepartmentLector;
import com.khrystoforov.university.model.Lector;
import com.khrystoforov.university.model.enums.Degree;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UniversityService {
    private final DepartmentService departmentService;
    private final LectorService lectorService;
    private final LectorMapper lectorMapper;
    @Value("${department.averageSalary.scale}")
    private Integer averageSalaryScale;

    public LectorDTO findDepartmentHead(String departmentName) {
        Department department = departmentService.findDepartmentByName(departmentName);
        return lectorMapper.lectorToLectorDTO(department.getHead());
    }

    public Map<Degree, Long> countLectorsInDepartmentByDegree(String departmentName) {
        Department department = departmentService.findDepartmentByName(departmentName);
        return department.getLectors().stream()
                .map(DepartmentLector::getLector)
                .collect(Collectors.groupingBy(Lector::getDegree, Collectors.counting()));
    }

    public BigDecimal findAverageSalaryInDepartment(String departmentName) {
        Department department = departmentService.findDepartmentByName(departmentName);
        BigDecimal averageSalary = BigDecimal.ZERO;
        int countOfLectors = department.getLectors().size();

        for (DepartmentLector departmentLector :
                department.getLectors()) {
            averageSalary = averageSalary.add(departmentLector.getLectorSalary());
        }

        return averageSalary.divide(BigDecimal.valueOf(countOfLectors),
                averageSalaryScale, RoundingMode.CEILING);
    }

    public Integer countOfLectorsForDepartment(String departmentName) {
        Department department = departmentService.findDepartmentByName(departmentName);
        return department.getLectors().size();
    }

    public Set<LectorDTO> globalLectorsSearch(String template) {
        return lectorService.findAll().stream()
                .filter(lector -> lector.getName().toLowerCase().contains(template.toLowerCase()))
                .map(lectorMapper::lectorToLectorDTO)
                .collect(Collectors.toSet());
    }
}
