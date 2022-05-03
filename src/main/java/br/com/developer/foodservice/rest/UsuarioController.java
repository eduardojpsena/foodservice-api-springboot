package br.com.developer.foodservice.rest;

import br.com.developer.foodservice.model.Usuario;
import br.com.developer.foodservice.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
@Tag(name = "Usuário", description = "API responsável pelos usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastradas")
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Pesquisar usuário", description = "Retorna um usuário selecionado pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @Operation(summary = "Salvar usuário", description = "Cria um novo usuário e salva na base de dados")
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody @Valid Usuario usuario) {
        usuario = service.salvar(usuario);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @Operation(summary = "Deletar usuário", description = "Deleta um usuário selecionado pelo id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário selecionado pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        service.atualizar(id, usuario);
        return ResponseEntity.ok().body(usuario);
    }
}
