package src.events;

public class ComidaPreparadaEvent {
    private final String pedidoId;
    private final String plato;

    public ComidaPreparadaEvent(String pedidoId, String plato) {
        this.pedidoId = pedidoId;
        this.plato = plato;
    }

    public String getPedidoId() { return pedidoId; }
    public String getPlato() { return plato; }
}