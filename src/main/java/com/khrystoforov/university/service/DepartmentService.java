package com.khrystoforov.university.service;

import com.khrystoforov.university.exception.DepartmentNotFoundException;
import com.khrystoforov.university.model.Department;
import com.khrystoforov.university.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department findDepartmentByName(String name) {
        return departmentRepository.findDepartmentByName(name)
                .orElseThrow(() -> new DepartmentNotFoundException(
                        String.format("Department with name %s does not exist", name)));
    }

    public Department createDepartment(Department department) {
        return departmentRepository.persist(department);
    }

    public void mergeDepartment(Department department) {
        departmentRepository.merge(department);
    }
}
