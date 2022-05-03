package br.com.developer.foodservice.rest;

import br.com.developer.foodservice.model.Produto;
import br.com.developer.foodservice.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
@Tag(name = "Produto", description = "API responsável pelos produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastradas")
    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodos() {
        List<Produto> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Pesquisar produto", description = "Retorna um produto selecionado pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @Operation(summary = "Salvar produto", description = "Cria um novo produto e salva na base de dados")
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        produto = service.salvar(produto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @Operation(summary = "Deletar produto", description = "Deleta um produto selecionado pelo id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar produto", description = "Atualiza um produto selecionado pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        service.atualizar(id, produto);
        return ResponseEntity.ok().body(produto);
    }
}
