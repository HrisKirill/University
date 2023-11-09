package com.khrystoforov.university.model;

import com.khrystoforov.university.model.enums.Degree;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = {"id"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private Long age;
    @Enumerated(EnumType.STRING)
    private Degree degree;
}
