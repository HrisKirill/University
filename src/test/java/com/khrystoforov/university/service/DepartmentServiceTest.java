package com.khrystoforov.university.service;

import com.khrystoforov.university.exception.DepartmentNotFoundException;
import com.khrystoforov.university.model.Department;
import com.khrystoforov.university.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepartment() {
        when(departmentRepository.persist(any(Department.class))).thenReturn(getTestDepartment());
        assertEquals(getTestDepartment(), departmentService.createDepartment(getTestDepartment()));
        verify(departmentRepository, times(1))
                .persist(any(Department.class));
    }

    @Test
    public void testFindDepartmentByName_ExistingDepartment() {
        when(departmentRepository.findDepartmentByName(getTestDepartment().getName()))
                .thenReturn(Optional.of(getTestDepartment()));
        assertEquals(getTestDepartment(),
                departmentService.findDepartmentByName(getTestDepartment().getName()));
        verify(departmentRepository, times(1))
                .findDepartmentByName(getTestDepartment().getName());
    }

    @Test
    public void testFindDepartmentByName_DepartmentNotFound() {
        when(departmentRepository.findDepartmentByName(getTestDepartment().getName()))
                .thenReturn(Optional.empty());
        assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.findDepartmentByName(getTestDepartment().getName()));
        verify(departmentRepository, times(1))
                .findDepartmentByName(getTestDepartment().getName());
    }

    private Department getTestDepartment() {
        return Department.builder()
                .name("Test Department")
                .build();
    }
}