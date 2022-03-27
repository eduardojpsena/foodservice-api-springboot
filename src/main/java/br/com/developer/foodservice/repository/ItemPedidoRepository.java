package br.com.developer.foodservice.repository;

import br.com.developer.foodservice.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Transactional
    @Modifying
    @Query("delete from ItemPedido i where i.id.pedido.id=:#{#id_pedido}")
    void deletarPorIdPedido(@Param("id_pedido") Long id_pedido);
}
