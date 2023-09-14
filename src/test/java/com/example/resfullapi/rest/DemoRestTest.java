package com.example.resfullapi.rest;

import com.example.resfullapi.service.LibrosService;
import org.junit.jupiter.api.Test;
import com.example.resfullapi.model.Libros;
import com.example.resfullapi.repository.LibrosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DemoRestTest {
    @InjectMocks
    LibrosService librosService;

    @Mock
    LibrosRepository librosRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void obtenerLibroPorId() {
        Libros Libros = new Libros();
        Libros.setId(1L);
        Libros.setDescripcion("Test");
        Libros.setEstado("pendiente");

        when(librosRepository.findById(1L)).thenReturn(Optional.of(Libros));

        Libros found = librosService.obtenerProductoPorId(1L);

        assertEquals(Libros.getDescripcion(), found.getDescripcion());

    }

    @Test
    void crearLibro() {
        Libros libro = new Libros();
        libro.setId(1L);
        libro.setDescripcion("Nuevo Libro");
        libro.setEstado("pendiente");
        when(librosRepository.save(any())).thenReturn(libro);

        Libros createdLibro = librosService.crearLibro(libro);
        assertEquals("Nuevo Libro", createdLibro.getDescripcion());
    }

    @Test
    void actualizarLibro() {
        Libros libro = new Libros();
        libro.setId(1L);
        libro.setDescripcion("Libro Actualizado");
        libro.setEstado("pendiente");
        when(librosRepository.findById(1L)).thenReturn(Optional.of(libro));
        librosService.actualizarLibro(1L,libro);
    }

    @Test
    void eliminarLibro() {
        Libros libro = new Libros();
        libro.setId(1L);
        libro.setDescripcion("Libro de Prueba");
        libro.setEstado("pendiente");
        when(librosRepository.findById(1L)).thenReturn(Optional.of(libro));
        librosService.eliminarLibro(1L);
    }
}