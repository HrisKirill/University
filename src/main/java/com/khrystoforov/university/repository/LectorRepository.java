package com.khrystoforov.university.repository;

import com.khrystoforov.university.model.Lector;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LectorRepository extends BaseJpaRepository<Lector, Long> {
    @Query("from Lector")
    Set<Lector> findAll();

    Optional<Lector> findByName(String name);
}
