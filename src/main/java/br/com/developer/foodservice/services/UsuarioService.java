package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Usuario;
import br.com.developer.foodservice.repository.UsuarioRepository;
import br.com.developer.foodservice.services.exceptions.DatabaseException;
import br.com.developer.foodservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario= repository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFoundException(id, "Usuário não encontrado"));
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public void deletarPorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id, "Usuário não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void atualizar(Long id, Usuario usuario) {
        repository.findById(id).map(u -> {
            usuario.setId(u.getId());
            return repository.save(usuario);
        }).orElseThrow(() -> new ResourceNotFoundException(id, "Usuário não encontrado"));
    }
}
