package src.events;

public class BandaTocandoEvent {
    private final String nombreBanda;
    private final String nombreCancion;
    private final int duracionSegundos;

    public BandaTocandoEvent(String nombreBanda, String nombreCancion, int duracionSegundos) {
        this.nombreBanda = nombreBanda;
        this.nombreCancion = nombreCancion;
        this.duracionSegundos = duracionSegundos;
    }

    public String getNombreBanda() { return nombreBanda; }
    public String getNombreCancion() { return nombreCancion; }
    public int getDuracionSegundos() { return duracionSegundos; }
}