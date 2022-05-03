package br.com.developer.foodservice.rest;

import br.com.developer.foodservice.model.Categoria;
import br.com.developer.foodservice.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
@Tag(name = "Categoria", description = "API responsável pelas categorias dos produtos")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas")
    @GetMapping
    public ResponseEntity<List<Categoria>> buscarTodos() {
        List<Categoria> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Pesquisar categoria", description = "Retorna uma categoria selecionada pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = service.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @Operation(summary = "Salvar categoria", description = "Cria uma nova categorias e salva na base de dados")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        categoria = service.salvar(categoria);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @Operation(summary = "Deletar categoria", description = "Deleta uma categoria selecionada pelo id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria selecionada pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        service.atualizar(id, categoria);
        return ResponseEntity.ok().body(categoria);
    }
}
