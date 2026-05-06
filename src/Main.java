package src;
import src.eventbus.EventBus;
import src.components.Banda;
import src.components.Barra;
import src.components.Cocina;
import src.components.PanelLED;
import src.components.SistemaDeHumo;
import src.components.SistemaDePedidos;
import src.components.SistemaDeSonido;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println("     TABERNA EL GOLEM ALQUIMICO - SISTEMA CON EVENT BUS");
        System.out.println("==================================================\n");

        // Crear el Event Bus central
        EventBus eventBus = new EventBus();

        // Inicializar todos los componentes (sin referencias directas entre ellos)
        SistemaDePedidos sistemaPedidos = new SistemaDePedidos(eventBus);
        Barra barra = new Barra(eventBus);
        Cocina cocina = new Cocina(eventBus);
        Banda banda = new Banda(eventBus);
        SistemaDeSonido sonido = new SistemaDeSonido(eventBus);
        PanelLED panelLED = new PanelLED(eventBus);

        // DEMOSTRACIÓN DE EXTENSIBILIDAD:
        // Nuevo sistema agregado SIN modificar ningún código existente
        SistemaDeHumo sistemaHumo = new SistemaDeHumo(eventBus);
        System.out.println("[EXTENSIBILIDAD] SistemaDeHumo agregado sin modificar Banda ni EventBus\n");

        // Iniciar la banda (toca en segundo plano)
        banda.iniciar();

        // Simular un pedido
        Thread.sleep(1000);
        sistemaPedidos.realizarPedido("5", "PED-001", java.util.List.of("cerveza", "hamburguesa"));

        // Mantener el programa vivo para ver todos los eventos
        Thread.sleep(15000);
        
        System.out.println("\n==================================================");
        System.out.println("                     DEMOSTRACION COMPLETADA");
        System.out.println("==================================================");
        
        banda.detener();
    }
}