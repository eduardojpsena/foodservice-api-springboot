package br.com.developer.foodservice.repository;

import br.com.developer.foodservice.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
