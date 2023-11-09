package com.khrystoforov.university.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentLectorId implements Serializable {
    private Long departmentId;
    private Long lectorId;
}
