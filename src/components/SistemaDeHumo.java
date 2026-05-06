package src.components;

import src.eventbus.EventBus;
import src.events.BandaTocandoEvent;

// NUEVO COMPONENTE AGREGADO SIN MODIFICAR CODIGO EXISTENTE
public class SistemaDeHumo {
    public SistemaDeHumo(EventBus eventBus) {
        eventBus.suscribir(BandaTocandoEvent.class, this::handleBandaTocando);
    }

    private void handleBandaTocando(Object event) {
        BandaTocandoEvent e = (BandaTocandoEvent) event;
        if ("Through the Fire and Flames".equalsIgnoreCase(e.getNombreCancion())) {
            System.out.println("[SISTEMA HUMO] Cancion EPICA detectada! Disparando efectos de humo...");
            System.out.println("[SISTEMA HUMO] Humo en el escenario!");
        }
    }
}