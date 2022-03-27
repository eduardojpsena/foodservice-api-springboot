package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.ItemPedido;
import br.com.developer.foodservice.model.Pedido;
import br.com.developer.foodservice.model.emun.StatusPedido;
import br.com.developer.foodservice.repository.ItemPedidoRepository;
import br.com.developer.foodservice.repository.PedidoRepository;
import br.com.developer.foodservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    public List<Pedido> buscarTodos() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(() -> new ResourceNotFoundException(id, "Pedido não encontrado"));
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setMomento(Instant.now());
        pedido.setUsuario(usuarioService.buscarPorId(pedido.getUsuario().getId()));
        pedido.setStatus(StatusPedido.PEDIDO_RECEBIDO);
        pedido = repository.save(pedido);

        for (ItemPedido ip: pedido.getItens()) {
            ip.setPedido(pedido);
            ip.setProduto(produtoService.buscarPorId(ip.getProduto().getId()));
        }

        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }

    public void atualizarStatus(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = buscarPorId(id);
        pedidoAtualizado.setId(pedido.getId());
        pedidoAtualizado.setMesa(pedido.getMesa());
        pedidoAtualizado.setMomento(Instant.now());
        pedidoAtualizado.setUsuario(pedido.getUsuario());

        repository.save(pedidoAtualizado);
    }


}
