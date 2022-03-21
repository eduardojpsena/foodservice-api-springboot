package br.com.developer.foodservice.config;

import br.com.developer.foodservice.model.Usuario;
import br.com.developer.foodservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario u1 = new Usuario(1L, "eduardo", "1234");
        Usuario u2 = new Usuario(2L, "marcelo", "4321");

        usuarioRepository.saveAll(Arrays.asList(u1, u2));
    }
}
