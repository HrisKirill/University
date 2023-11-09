package com.khrystoforov.university.model;

import com.khrystoforov.university.model.enums.Degree;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"id","departments"})
@ToString(exclude = {"id","departments"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    @OneToMany(
            mappedBy = "lector",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<DepartmentLector> departments = new HashSet<>();
}
