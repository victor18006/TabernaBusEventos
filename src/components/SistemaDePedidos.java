package src.components;

import src.eventbus.EventBus;
import src.events.PedidoRealizadoEvent;
import java.util.List;

public class SistemaDePedidos {
    private final EventBus eventBus;

    public SistemaDePedidos(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void realizarPedido(String mesaId, String pedidoId, List<String> items) {
        System.out.println("[SistemaPedidos] Nuevo pedido: Mesa " + mesaId + " - Items: " + items);
        PedidoRealizadoEvent event = new PedidoRealizadoEvent(mesaId, pedidoId, items);
        eventBus.publicar(event);
    }
}