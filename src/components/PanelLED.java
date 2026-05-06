package src.components;

import src.eventbus.EventBus;
import src.events.*;

public class PanelLED {
    public PanelLED(EventBus eventBus) {
        // Suscribirse a todos los eventos relevantes
        eventBus.suscribir(BebidaServidaEvent.class, this::handleBebidaServida);
        eventBus.suscribir(ComidaPreparadaEvent.class, this::handleComidaPreparada);
        eventBus.suscribir(PedidoRealizadoEvent.class, this::handlePedidoRealizado);
        eventBus.suscribir(BandaTocandoEvent.class, this::handleBandaTocando);
    }

    private void handleBebidaServida(Object event) {
        BebidaServidaEvent e = (BebidaServidaEvent) event;
        System.out.println("[PANEL LED] Nueva bebida servida en la Mesa " + e.getMesaId() + "! (" + e.getBebida() + ")");
    }

    private void handleComidaPreparada(Object event) {
        ComidaPreparadaEvent e = (ComidaPreparadaEvent) event;
        System.out.println("[PANEL LED] Comida preparada! " + e.getPlato() + " - Pedido: " + e.getPedidoId());
    }

    private void handlePedidoRealizado(Object event) {
        PedidoRealizadoEvent e = (PedidoRealizadoEvent) event;
        System.out.println("[PANEL LED] Nuevo pedido registrado - Mesa " + e.getMesaId() + " (" + e.getItems() + ")");
    }

    private void handleBandaTocando(Object event) {
        BandaTocandoEvent e = (BandaTocandoEvent) event;
        System.out.println("[PANEL LED] La banda " + e.getNombreBanda() + " esta tocando \"" + e.getNombreCancion() + "\"!");
    }
}