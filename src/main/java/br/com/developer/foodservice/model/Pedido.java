package br.com.developer.foodservice.model;

import br.com.developer.foodservice.model.emun.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer mesa;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant momento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Integer status;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Long id, Integer mesa, Instant momento, Usuario usuario, StatusPedido status) {
        this.id = id;
        this.mesa = mesa;
        this.momento = momento;
        this.usuario = usuario;
        setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }


    public StatusPedido getStatus() {
        return StatusPedido.valueOf(status);
    }

    public void setStatus(StatusPedido status) {
        if (status != null) {
            this.status = status.getCodido();
        }
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public Double getTotal(){
        double total = 0.0;
        for (ItemPedido item: itens) {
            total += item.getSubtotal();
        }
        return total;
    }
}
