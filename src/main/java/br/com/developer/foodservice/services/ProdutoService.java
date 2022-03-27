package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Produto;
import br.com.developer.foodservice.repository.ProdutoRepository;
import br.com.developer.foodservice.services.exceptions.DatabaseException;
import br.com.developer.foodservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaService categoriaService;

    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        Optional<Produto> produto= repository.findById(id);
        return produto.orElseThrow(() -> new ResourceNotFoundException(id, "Produto não encontrado"));
    }

    public Produto salvar(Produto produto) {
        produto.setCategoria(categoriaService.buscarPorId(produto.getCategoria().getId()));
        return repository.save(produto);
    }

    public void deletarPorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id, "Produto não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void atualizar(Long id, Produto produto) {
        repository.findById(id).map(u -> {
            produto.setId(u.getId());
            produto.setCategoria(categoriaService.buscarPorId(produto.getCategoria().getId()));
            return repository.save(produto);
        }).orElseThrow(() -> new ResourceNotFoundException(id, "Produto não encontrado"));
    }
}
