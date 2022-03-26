package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Categoria;
import br.com.developer.foodservice.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria findById(Long id) {
        Optional<Categoria> Categoria= repository.findById(id);
        return Categoria.get();
    }
}
