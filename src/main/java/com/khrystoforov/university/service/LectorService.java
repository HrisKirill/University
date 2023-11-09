package com.khrystoforov.university.service;

import com.khrystoforov.university.model.Lector;
import com.khrystoforov.university.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LectorService {
    private final LectorRepository lectorRepository;

    public Set<Lector> findAll() {
        return lectorRepository.findAll();
    }

    public Lector createLector(Lector lector) {
        return lectorRepository.persist(lector);
    }
}
