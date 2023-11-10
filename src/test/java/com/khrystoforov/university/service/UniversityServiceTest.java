package com.khrystoforov.university.service;

import com.khrystoforov.university.dto.LectorDTO;
import com.khrystoforov.university.mapper.LectorMapper;
import com.khrystoforov.university.model.Department;
import com.khrystoforov.university.model.Lector;
import com.khrystoforov.university.model.enums.Degree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniversityServiceTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private LectorService lectorService;

    @Mock
    private LectorMapper lectorMapper;

    @InjectMocks
    private UniversityService universityService;


    @BeforeEach
    void init_mocks() {
        ReflectionTestUtils.setField(universityService,
                "averageSalaryScale", 2);
    }


    private Lector getTestLector() {
        return Lector.builder()
                .name("Test Name")
                .degree(Degree.ASSISTANT)
                .age(50)
                .build();
    }

    private LectorDTO getTestLectorDTO() {
        return LectorDTO.builder()
                .name("Test Name")
                .degree(Degree.ASSISTANT)
                .age(50)
                .build();
    }

    @Test
    public void testFindDepartmentHead() {
        Department department = Department.builder()
                .name("Test Name")
                .head(getTestLector())
                .build();

        LectorDTO lectorDTO = LectorDTO.builder()
                .name("Test Name")
                .age(50)
                .build();

        when(departmentService.findDepartmentByName(anyString()))
                .thenReturn(department);

        when(lectorMapper.lectorToLectorDTO(getTestLector())).thenReturn(lectorDTO);
        assertEquals(lectorDTO, universityService.findDepartmentHead(department.getName()));
    }

    @Test
    public void testCountLectorsInDepartmentByDegree() {
        Department department = Department.builder()
                .name("Test in Method")
                .lectors(new HashSet<>())
                .build();

        department.addLectors(getTestLector(), BigDecimal.TEN);
        when(departmentService.findDepartmentByName(anyString()))
                .thenReturn(department);
        Map<Degree, Long> result = universityService
                .countLectorsInDepartmentByDegree(department.getName());
        assertEquals(1, result.get(getTestLector().getDegree()));
    }

    @Test
    public void testFindAverageSalaryInDepartment() {
        Department department = Department.builder()
                .name("Test Name")
                .head(getTestLector())
                .lectors(new HashSet<>())
                .build();

        Lector lector = Lector.builder()
                .name("Test")
                .degree(Degree.PROFESSOR)
                .age(55)
                .build();

        department.addLectors(getTestLector(), BigDecimal.valueOf(1000.0));
        department.addLectors(lector, BigDecimal.valueOf(2500.0));
        when(departmentService.findDepartmentByName(anyString())).thenReturn(department);

        BigDecimal result = universityService
                .findAverageSalaryInDepartment(department.getName());

        assertEquals(new BigDecimal("1750.00"), result);
    }

    @Test
    public void testCountOfLectorsForDepartment() {
        Department department = Department.builder()
                .name("Test in Method")
                .lectors(new HashSet<>())
                .build();

        department.addLectors(getTestLector(), BigDecimal.TEN);
        when(departmentService.findDepartmentByName(anyString())).thenReturn(department);
        int result = universityService.countOfLectorsForDepartment(department.getName());
        assertEquals(1, result);
    }

    @Test
    public void testGlobalLectorsSearch() {
        Lector lector1 = Lector.builder()
                .name("Genry Testination")
                .build();

        LectorDTO lectorDTO1 = LectorDTO.builder()
                .name("Genry Testination")
                .build();

        when(lectorService.findAll()).thenReturn(Set.of(lector1, getTestLector()));
        when(lectorMapper.lectorToLectorDTO(getTestLector())).thenReturn(getTestLectorDTO());
        when(lectorMapper.lectorToLectorDTO(lector1)).thenReturn(lectorDTO1);

        Set<LectorDTO> result = universityService.globalLectorsSearch("test");
        Set<LectorDTO> expectedSet = Set.of(getTestLectorDTO(), lectorDTO1);
        assertEquals(expectedSet, result);
    }
}