package com.khrystoforov.university.dto;

import com.khrystoforov.university.model.enums.Degree;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectorDTO {
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Degree degree;
}
