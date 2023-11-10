package com.khrystoforov.university.controller;

import com.khrystoforov.university.dto.LectorDTO;
import com.khrystoforov.university.model.enums.Degree;
import com.khrystoforov.university.service.UniversityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UniversityControllerTest {

    @Mock
    private UniversityService universityService;

    @InjectMocks
    private UniversityController universityController;

    @Test
    public void testFindHeadOfDepartment() {
        String departmentName = "Computer Science";
        LectorDTO expectedDto = LectorDTO.builder()
                .name("Test Name")
                .build();
        when(universityService.findDepartmentHead(departmentName)).thenReturn(expectedDto);
        LectorDTO result = universityController.findHeadOfDepartment(departmentName);

        assertEquals(expectedDto, result);
    }

    @Test
    public void testShowDepartmentStatistics() {
        String departmentName = "Computer Science";
        Map<Degree, Long> expectedStatistics = new HashMap<>();
        expectedStatistics.put(Degree.PROFESSOR, 2L);
        when(universityService.countLectorsInDepartmentByDegree(departmentName))
                .thenReturn(expectedStatistics);
        Map<Degree, Long> result = universityController.showDepartmentStatistics(departmentName);

        assertEquals(expectedStatistics, result);
    }

    @Test
    public void testFindDepartmentAverageSalary() {
        String departmentName = "Computer Science";
        BigDecimal expectedAverageSalary = new BigDecimal("2000.0");
        when(universityService.findAverageSalaryInDepartment(departmentName))
                .thenReturn(expectedAverageSalary);
        BigDecimal result = universityController.findDepartmentAverageSalary(departmentName);

        assertEquals(expectedAverageSalary, result);
    }

    @Test
    public void testCountOfLectorsInDepartment() {
        String departmentName = "Computer Science";
        int expectedCount = 5;
        when(universityService.countOfLectorsForDepartment(departmentName))
                .thenReturn(expectedCount);
        int result = universityController.countOfLectorsInDepartment(departmentName);

        assertEquals(expectedCount, result);
    }

    @Test
    public void testGlobalLectorSearch() {
        String template = "Doe";
        Set<LectorDTO> expectedSet = Set.of(LectorDTO.builder()
                        .name("Test Name 1")
                        .build(),
                LectorDTO.builder()
                        .name("Test Name 2")
                        .build());

        when(universityService.globalLectorsSearch(template)).thenReturn(expectedSet);
        Set<LectorDTO> result = universityController.globalLectorSearch(template);

        assertEquals(expectedSet, result);
    }
}
