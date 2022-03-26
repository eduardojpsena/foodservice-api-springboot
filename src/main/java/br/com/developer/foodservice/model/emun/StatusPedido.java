package br.com.developer.foodservice.model.emun;

public enum StatusPedido {

    PREPARANDO(1),
    ENTREGUE(2),
    PAGO(3),
    CANCELADO(4);

    private int codido;

    private StatusPedido(int codido) {
        this.codido = codido;
    }

    public int getCodido() {
        return codido;
    }

    public static StatusPedido valueOf(int codido) {
        for (StatusPedido value: StatusPedido.values()) {
            if (value.getCodido() == codido) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código PedidoStatus Inválido");
    }
}
