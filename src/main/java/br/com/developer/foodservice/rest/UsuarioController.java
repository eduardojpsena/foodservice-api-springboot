package br.com.developer.foodservice.rest;

import br.com.developer.foodservice.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<Usuario> findAll() {
        Usuario usuario = new Usuario(1L, "dudusn","rocklee");
        return ResponseEntity.ok().body(usuario);
    }
}
