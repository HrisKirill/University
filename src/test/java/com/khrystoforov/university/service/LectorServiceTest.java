package com.khrystoforov.university.service;

import com.khrystoforov.university.model.Lector;
import com.khrystoforov.university.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LectorServiceTest {

    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorService lectorService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDepartment() {
        when(lectorRepository.persist(any(Lector.class))).thenReturn(getTestLector());
        assertEquals(getTestLector(), lectorService.createLector(getTestLector()));
        verify(lectorRepository, times(1))
                .persist(any(Lector.class));
    }

    @Test
    public void testFindAll() {
        when(lectorRepository.findAll()).thenReturn(Set.of(getTestLector()));
        assertEquals(1, lectorService.findAll().size());
    }

    private Lector getTestLector() {
        return Lector.builder()
                .name("Test Lector")
                .age(53)
                .build();
    }
}