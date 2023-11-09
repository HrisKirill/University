package com.khrystoforov.university.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = {"id"})
public class DepartmentLector {
    @EmbeddedId
    private DepartmentLectorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departmentId")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lectorId")
    private Lector lector;

    private BigDecimal lectorSalary;

    public DepartmentLector(Department department, Lector lector, BigDecimal lectorSalary) {
        this.department = department;
        this.lector = lector;
        this.id = new DepartmentLectorId(department.getId(), lector.getId());
        this.lectorSalary = lectorSalary;
    }
}
