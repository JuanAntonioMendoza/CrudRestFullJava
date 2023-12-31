package com.example.resfullapi.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.resfullapi.model.Libros;
import com.example.resfullapi.repository.LibrosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.example.resfullapi.service.JwtUtilService;
import com.example.resfullapi.model.TokenInfo;
import com.example.resfullapi.model.AuthenticationReq;
@RestController
@RequestMapping
public class DemoRest {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;
    private static final Logger logger = LoggerFactory.getLogger(DemoRest.class);

    @GetMapping("/mensaje")
    public ResponseEntity<?> getMensaje() {
        logger.info("Obteniendo el mensaje");

        var auth =  SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del Usuario: {}", auth.getPrincipal());
        logger.info("Datos de los Roles {}", auth.getAuthorities());
        logger.info("Esta autenticado {}", auth.isAuthenticated());

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("contenido", "Hola");
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getMensajeAdmin() {

        var auth =  SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del Usuario: {}", auth.getPrincipal());
        logger.info("Datos de los Permisos {}", auth.getAuthorities());
        logger.info("Esta autenticado {}", auth.isAuthenticated());

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("contenido", "Hola Admin");


        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/publico")
    public ResponseEntity<?> getMensajePublico() {
        var auth =  SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del Usuario: {}", auth.getPrincipal());
        logger.info("Datos de los Permisos {}", auth.getAuthorities());
        logger.info("Esta autenticado {}", auth.isAuthenticated());

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("contenido", "Hola. esto es publico");
        return ResponseEntity.ok(mensaje);
    }



    @PostMapping("/publico/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
        logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                            authenticationReq.getClave()));

            final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                    authenticationReq.getUsuario());

            final String jwt = jwtUtilService.generateToken(userDetails);

            return ResponseEntity.ok(new TokenInfo(jwt));
        } catch (AuthenticationException e) {

            logger.info("Autenticando al usuario {}", authenticationReq.getUsuario() + "Autenticando al password" +authenticationReq.getClave());
            logger.info(e.getMessage());
            return null;
        }

    }


    @Autowired
    private LibrosRepository librosRepository;

    @GetMapping("/publico/obtenerTodosLosLibros")
    public List<Libros> obtenerTodosLosLibros() {
        return librosRepository.findAll();
    }
    @GetMapping("/publico/{id}")
    public Libros obtenerLibroPorId(@PathVariable Long id) {
        return librosRepository.findById(id).orElse(null);
    }
    @PostMapping("/publico/")
    public Libros crearLibro(@RequestBody Libros libro) {
        return librosRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Libros actualizarLibro(@PathVariable Long id, @RequestBody Libros libro) {
        libro.setId(id);
        return librosRepository.save(libro);
    }
    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        librosRepository.deleteById(id);
    }

}