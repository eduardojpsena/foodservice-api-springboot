package br.com.developer.foodservice.model;

import br.com.developer.foodservice.model.pk.ItemPedidoPrimaryKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemPedidoPrimaryKey id = new ItemPedidoPrimaryKey();

    private Integer quantidade = 1;

    //private Double preço;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido, Integer quantidade) {
        id.setProduto(produto);
        id.setPedido(pedido);
        this.quantidade = quantidade;
        //this.preço = getProduto().getPreco();
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
/*
    public Double getPreço() {
        return preço;
    }

    public void setPreço(Double preço) {
        this.preço = preço;
    }
*/

    public Double getSubtotal() {
        Double preço = getProduto().getPreco();
        return preço * quantidade;
    }

}
