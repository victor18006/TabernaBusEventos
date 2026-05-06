package src.components;

import src.eventbus.EventBus;
import src.events.ComidaPreparadaEvent;
import src.events.PedidoRealizadoEvent;
import java.util.List;

public class Cocina {
    private final EventBus eventBus;

    public Cocina(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.suscribir(PedidoRealizadoEvent.class, this::handlePedidoRealizado);
    }

    private void handlePedidoRealizado(Object event) {
        PedidoRealizadoEvent pedido = (PedidoRealizadoEvent) event;
        List<String> comidas = List.of("hamburguesa", "pizza", "ensalada", "sopa", "tacos");
        
        for (String item : pedido.getItems()) {
            if (comidas.contains(item.toLowerCase())) {
                cocinarPlato(pedido.getPedidoId(), item);
            }
        }
    }

    private void cocinarPlato(String pedidoId, String plato) {
        new Thread(() -> {
            try {
                System.out.println("[Cocina] Cocinando " + plato + " para pedido " + pedidoId + "...");
                Thread.sleep(5000); // 5 segundos
                System.out.println("[Cocina] " + plato + " listo para pedido " + pedidoId);
                ComidaPreparadaEvent event = new ComidaPreparadaEvent(pedidoId, plato);
                eventBus.publicar(event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}