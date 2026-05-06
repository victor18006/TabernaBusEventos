package src.components;

import src.eventbus.EventBus;
import src.events.BandaTocandoEvent;
import java.util.Random;

public class Banda {
    private final EventBus eventBus;
    private final Random random = new Random();
    private boolean activa = true;

    private final String[][] canciones = {
        {"Los Seguidores de Dijkstra", "El Algoritmo del Amor", "180"},
        {"Los Seguidores de Dijkstra", "Through the Fire and Flames", "240"},
        {"Los Lunares 202", "Null Pointer Blues", "150"},
        {"Los Viento 104", "The Darks", "200"}
    };

    public Banda(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void iniciar() {
        new Thread(() -> {
            while (activa) {
                try {
                    String[] cancion = canciones[random.nextInt(canciones.length)];
                    Thread.sleep(8000); // Toca cada 8 segundos
                    BandaTocandoEvent event = new BandaTocandoEvent(
                        cancion[0], cancion[1], Integer.parseInt(cancion[2])
                    );
                    System.out.println("\n[Banda] Ahora suena: " + cancion[1] + " por " + cancion[0] + "!");
                    eventBus.publicar(event);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    public void detener() {
        activa = false;
    }
}