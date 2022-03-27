package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Categoria;
import br.com.developer.foodservice.repository.CategoriaRepository;
import br.com.developer.foodservice.services.exceptions.DatabaseException;
import br.com.developer.foodservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> buscarTodos() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria= repository.findById(id);
        return categoria.orElseThrow(() -> new ResourceNotFoundException(id, "Categoria não encontrado"));
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public void deletarPorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id, "Categoria não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void atualizar(Long id, Categoria categoria) {
        repository.findById(id).map(u -> {
            categoria.setId(u.getId());
            return repository.save(categoria);
        }).orElseThrow(() -> new ResourceNotFoundException(id, "Categoria não encontrado"));
    }
}
