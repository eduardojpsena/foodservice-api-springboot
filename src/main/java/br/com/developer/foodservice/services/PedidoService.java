package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Pedido;
import br.com.developer.foodservice.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        Optional<Pedido> Pedido= repository.findById(id);
        return Pedido.get();
    }
}
