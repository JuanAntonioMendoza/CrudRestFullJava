package com.example.resfullapi.testapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/com/example/resfullapi/testapi")
@RequestMapping("testapi")
public class FirstController {
    @GetMapping("/obtenerTodosLosLibros")
    public String obtenerTodosLosLibros() {
        String  test = "Hello World";

        return test;
    }
}
