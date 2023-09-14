package com.example.resfullapi.testapi;
import com.example.resfullapi.model.Libros;
import com.example.resfullapi.repository.LibrosRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibroController {

    private final LibrosRepository libroRepository;

    public LibroController(LibrosRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/libros")
    public List<Libros> getAllLibros() {
        return libroRepository.findAll();
    }

    @PostMapping("/libros")
    public Libros saveLibro(@RequestBody Libros libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/libros/{id}")
    public Libros updateLibro(@PathVariable Long id, @RequestBody Libros libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    @DeleteMapping("/libros/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }

}
