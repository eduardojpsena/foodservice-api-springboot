package br.com.developer.foodservice.rest;

import br.com.developer.foodservice.model.Pedido;
import br.com.developer.foodservice.services.PedidoService;
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
@RequestMapping(value = "/pedidos")
@Tag(name = "Pedido", description = "API responsável pelos pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(summary = "Listar pedidos", description = "Retorna todos os pedidos cadastradas")
    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodos() {
        List<Pedido> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Pesquisar pedido", description = "Retorna um pedido selecionado pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        return ResponseEntity.ok().body(pedido);
    }

    @Operation(summary = "Salvar pedido", description = "Cria um novo pedido e salva na base de dados")
    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) {
        pedido = service.salvar(pedido);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @Operation(summary = "Atualizar pedido", description = "Atualiza um pedido selecionado pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        service.atualizarStatus(id, pedido);
        return ResponseEntity.ok().body(pedido);
    }


}
