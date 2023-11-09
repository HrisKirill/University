package com.khrystoforov.university.mapper;

import com.khrystoforov.university.dto.LectorDTO;
import com.khrystoforov.university.model.Lector;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LectorMapper {

    LectorDTO lectorToLectorDTO(Lector lector);
}
