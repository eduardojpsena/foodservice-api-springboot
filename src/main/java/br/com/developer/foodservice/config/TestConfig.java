package br.com.developer.foodservice.config;

import br.com.developer.foodservice.emun.StatusPedido;
import br.com.developer.foodservice.model.Categoria;
import br.com.developer.foodservice.model.Pedido;
import br.com.developer.foodservice.model.Produto;
import br.com.developer.foodservice.model.Usuario;
import br.com.developer.foodservice.repository.CategoriaRepository;
import br.com.developer.foodservice.repository.PedidoRepository;
import br.com.developer.foodservice.repository.ProdutoRepository;
import br.com.developer.foodservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(1L, "Sanduíches");
        Categoria c2 = new Categoria(2L, "Pizzas");
        Categoria c3 = new Categoria(3L, "Pastéis");
        Categoria c4 = new Categoria(4L, "Bebidas");

        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

        Produto pr1 = new Produto(1L, "Hamburguer", "Pão, carne e salada", 5.00, "", c1);
        Produto pr2 = new Produto(2L, "x-Bacon", "Pão, carne, salada e bacon", 7.10, "", c1);
        Produto pr3 = new Produto(3L, "Coca-Cola", "Lata 350ml", 4.50, "", c4);

        produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3));

        Usuario u1 = new Usuario(1L,"Eduardo Sena" ,"esena", "1234");
        Usuario u2 = new Usuario(2L,"André Luis","amoreira" ,"4321");

        usuarioRepository.saveAll(Arrays.asList(u1, u2));

        Pedido p1 = new Pedido(1L, 2, StatusPedido.ENTREGUE, Instant.parse("2020-03-21T13:21:50Z"), u1);
        Pedido p2 = new Pedido(2L, 2, StatusPedido.PREPARANDO, Instant.parse("2020-03-21T13:21:50Z"), u1);
        Pedido p3 = new Pedido(3L, 10, StatusPedido.RECEBIDO, Instant.parse("2020-04-25T14:23:03Z"), u2);

        pedidoRepository.saveAll(Arrays.asList(p1, p2, p3));


    }
}
