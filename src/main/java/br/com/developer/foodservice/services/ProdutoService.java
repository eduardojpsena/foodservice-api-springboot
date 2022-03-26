package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Produto;
import br.com.developer.foodservice.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> Produto= repository.findById(id);
        return Produto.get();
    }
}
