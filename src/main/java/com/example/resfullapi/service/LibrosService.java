package com.example.resfullapi.service;

import com.example.resfullapi.model.Libros;
import com.example.resfullapi.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibrosService {
    private final LibrosRepository librosRepository;

    @Autowired
    public LibrosService(LibrosRepository productoRepository) {
        this.librosRepository = productoRepository;
    }

    public List<Libros> obtenerTodosLosProductos() {
        return librosRepository.findAll();
    }

    public Libros obtenerProductoPorId(Long id) {
        return librosRepository.findById(id);
    }

    public Libros crearLibro(Libros libros) {
        return librosRepository.save(libros);
    }

    public Libros actualizarLibro(Long id, Libros libros) {
        libros.setId(id); // Asegurarse de que el ID coincida
        return librosRepository.save(libros);
    }

    public void eliminarLibro(Long id) {
        librosRepository.deleteById(id);
    }
}
