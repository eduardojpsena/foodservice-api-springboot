package br.com.developer.foodservice.emun;

public enum StatusPedido {

    RECEBIDO(1),
    PREPARANDO(2),
    ENTREGUE(3),
    PAGO(4),
    CANCELADO(5);

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
