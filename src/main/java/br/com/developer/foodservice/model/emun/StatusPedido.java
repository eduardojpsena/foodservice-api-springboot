package br.com.developer.foodservice.model.emun;

public enum StatusItemPedido {

    PREPARANDO(1),
    ENTREGUE(2),
    PAGO(3),
    CANCELADO(4);

    private int codido;

    private StatusItemPedido(int codido) {
        this.codido = codido;
    }

    public int getCodido() {
        return codido;
    }

    public static StatusItemPedido valueOf(int codido) {
        for (StatusItemPedido value: StatusItemPedido.values()) {
            if (value.getCodido() == codido) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código PedidoStatus Inválido");
    }
}
