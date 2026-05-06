package src.components;

import src.eventbus.EventBus;
import src.events.BandaTocandoEvent;

public class SistemaDeSonido {
    public SistemaDeSonido(EventBus eventBus) {
        eventBus.suscribir(BandaTocandoEvent.class, this::handleBandaTocando);
    }

    private void handleBandaTocando(Object event) {
        BandaTocandoEvent bandaEvent = (BandaTocandoEvent) event;
        System.out.println("[SistemaSonido] Ajustando ecualizador para: " + bandaEvent.getNombreCancion());
    }
}