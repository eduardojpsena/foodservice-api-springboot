package br.com.developer.foodservice.services;

import br.com.developer.foodservice.model.Usuario;
import br.com.developer.foodservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario= repository.findById(id);
        return usuario.get();
    }
}
