package src.components;

import src.eventbus.EventBus;
import src.events.BebidaServidaEvent;
import src.events.PedidoRealizadoEvent;
import java.util.List;

public class Barra {
    private final EventBus eventBus;

    public Barra(EventBus eventBus) {
        this.eventBus = eventBus;
        // Suscribirse a PedidoRealizadoEvent
        eventBus.suscribir(PedidoRealizadoEvent.class, this::handlePedidoRealizado);
    }

    private void handlePedidoRealizado(Object event) {
        PedidoRealizadoEvent pedido = (PedidoRealizadoEvent) event;
        List<String> bebidas = List.of("cerveza", "vino", "refresco", "agua", "jugo");
        
        for (String item : pedido.getItems()) {
            if (bebidas.contains(item.toLowerCase())) {
                prepararYBirrir(pedido.getMesaId(), item);
            }
        }
    }

    private void prepararYBirrir(String mesaId, String bebida) {
        new Thread(() -> {
            try {
                System.out.println("[Barra] Preparando " + bebida + " para Mesa " + mesaId + "...");
                Thread.sleep(2000); // 2 segundos
                System.out.println("[Barra] " + bebida + " lista para Mesa " + mesaId);
                BebidaServidaEvent event = new BebidaServidaEvent(mesaId, bebida);
                eventBus.publicar(event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}