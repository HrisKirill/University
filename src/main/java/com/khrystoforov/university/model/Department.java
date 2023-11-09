package com.khrystoforov.university.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(exclude = {"id", "lectors"})
@ToString(exclude = {"id", "lectors"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Lector head;

    @OneToMany(
            mappedBy = "department",
            orphanRemoval = true
    )
    private Set<DepartmentLector> lectors = new HashSet<>();

    public void addLectors(Lector lector, BigDecimal lectorSalary) {
        DepartmentLector departmentLector = new DepartmentLector(this, lector, lectorSalary);
        lectors.add(departmentLector);
    }

    public void removeLector(Lector lector) {
        for (Iterator<DepartmentLector> iterator = lectors.iterator();
             iterator.hasNext(); ) {
            DepartmentLector departmentLector = iterator.next();

            if (departmentLector.getDepartment().equals(this) &&
                    departmentLector.getLector().equals(lector)) {
                iterator.remove();
                departmentLector.setDepartment(null);
                departmentLector.setLector(null);
            }
        }
    }

    @Transient
    public BigDecimal getAverageSalary() {
        BigDecimal averageSalary = BigDecimal.ZERO;
        int countOfLectors = this.getLectors().size();
        for (DepartmentLector departmentLector :
                this.getLectors()) {
            averageSalary = averageSalary.add(departmentLector.getLectorSalary());
        }

        return averageSalary.divide(BigDecimal.valueOf(countOfLectors), 4, RoundingMode.CEILING);
    }

}
