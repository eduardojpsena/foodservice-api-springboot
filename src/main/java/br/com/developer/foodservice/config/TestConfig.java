package br.com.developer.foodservice.config;

import br.com.developer.foodservice.model.*;
import br.com.developer.foodservice.model.emun.StatusPedido;
import br.com.developer.foodservice.repository.*;
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
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        Categoria c1 = new Categoria(null, "Sanduíches");
        Categoria c2 = new Categoria(null, "Pizzas");
        Categoria c3 = new Categoria(null, "Pastéis");
        Categoria c4 = new Categoria(null, "Bebidas");

        categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

        Produto pr1 = new Produto(null, "Hamburguer", "Pão, carne e salada", 5.00, "", c1);
        Produto pr2 = new Produto(null, "x-Bacon", "Pão, carne, salada e bacon", 7.10, "", c1);
        Produto pr3 = new Produto(null, "Coca-Cola", "Lata 350ml", 4.50, "", c4);

        produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3));

        Usuario u1 = new Usuario(null,"Eduardo Sena" ,"esena", "1234");
        Usuario u2 = new Usuario(null,"André Luis","aluis" ,"4321");
        Usuario u3 = new Usuario(null,"Marcelo Mendes","mmendes" ,"1496");

        usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));

        Pedido p1 = new Pedido(null, 2, Instant.parse("2020-03-21T13:21:50Z"), u1, StatusPedido.PREPARANDO);
        Pedido p2 = new Pedido(null, 2, Instant.parse("2020-03-22T20:20:20Z"), u2, StatusPedido.PREPARANDO);
        Pedido p3 = new Pedido(null, 10, Instant.parse("2020-04-25T14:23:03Z"), u3, StatusPedido.PAGO);

        pedidoRepository.saveAll(Arrays.asList(p1, p2, p3));

        ItemPedido ip1 = new ItemPedido(pr1, p1, 2);
        ItemPedido ip2 = new ItemPedido(pr3, p1, 2);

        ItemPedido ip3 = new ItemPedido(pr2, p2, 1);

        ItemPedido ip4 = new ItemPedido(pr1, p3, 3);
        ItemPedido ip5 = new ItemPedido(pr3, p3, 3);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5));

    }
}
