package com.khrystoforov.university.repository;

import com.khrystoforov.university.model.Department;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends BaseJpaRepository<Department, Long> {
    Optional<Department> findDepartmentByName(String name);
}
