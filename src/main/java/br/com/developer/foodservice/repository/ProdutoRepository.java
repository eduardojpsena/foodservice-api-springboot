package br.com.developer.foodservice.repository;

import br.com.developer.foodservice.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
