# Taberna El Golem Alquimico - Sistema con Event Bus

## Que es este proyecto?

Sistema que simula una taberna universitaria donde los componentes (cocina, barra, banda, panel LED, sistema de sonido) se comunican entre si sin conocerse directamente. Toda la comunicacion pasa a traves de un Event Bus central, implementando el patron Publisher/Subscriber.

## Que problema resuelve?

- El problema tradicional: Cuando la banda toca una cancion, hay que modificar el codigo de la banda para que avise al sistema de sonido, al panel LED y al sistema de humo. Cada nueva funcionalidad requiere cambios en multiples clases.
- Nuestra solucion: La banda solo publica un evento en el Event Bus. Cualquier componente puede suscribirse a ese evento. Para agregar algo nuevo, solo lo creas y lo suscribes al bus. No tocas el codigo existente.

## Tecnologias

- Java 11 o superior
- Solo librerias estandar

## Estructura del proyecto

- src/eventbus/EventBus.java
- src/events/PedidoRealizadoEvent.java
- src/events/BebidaServidaEvent.java
- src/events/ComidaPreparadaEvent.java
- src/events/BandaTocandoEvent.java
- src/components/SistemaDePedidos.java
- src/components/Barra.java
- src/components/Cocina.java
- src/components/Banda.java
- src/components/SistemaDeSonido.java
- src/components/PanelLED.java
- src/components/SistemaDeHumo.java
- src/Main.java

## Como ejecutar

1. Abrir CMD (simbolo del sistema)
2. Navegar a la carpeta del proyecto:
   cd ruta_donde_esta_tu_proyecto
3. Compilar:
   javac -d out src\eventbus\EventBus.java src\events\*.java src\components\*.java src\Main.java
4. Ejecutar:
   java -cp out src.Main

O todo en uno:

javac -d out src\eventbus\EventBus.java src\events\*.java src\components\*.java src\Main.java && java -cp out src.Main

## Componentes

Componente               Tipo                      Funcion
SistemaDePedidos         Publicador                Crea y publica pedidos
Barra                    Publicador + Suscriptor   Prepara bebidas (2 segundos)
Cocina                   Publicador + Suscriptor   Cocina comida (5 segundos)
Banda                    Publicador                Toca canciones (cada 8 segundos)
SistemaDeSonido          Suscriptor                Ajusta ecualizador
PanelLED                 Suscriptor                Muestra mensajes
SistemaDeHumo            Suscriptor (extension)    Activa humo en cancion especifica

## Flujo de eventos

1. SistemaDePedidos publica PedidoRealizadoEvent
2. Barra y Cocina lo reciben y procesan
3. Barra publica BebidaServidaEvent (tras 2 segundos)
4. Cocina publica ComidaPreparadaEvent (tras 5 segundos)
5. PanelLED muestra ambos eventos
6. Banda publica BandaTocandoEvent cada 8 segundos
7. SistemaDeSonido y PanelLED reaccionan a la musica
8. SistemaDeHumo solo reacciona si la cancion es "Through the Fire and Flames"

## Ejemplo de salida

[SistemaPedidos] Nuevo pedido: Mesa 5 - Items: [cerveza, hamburguesa]
[PANEL LED] Nuevo pedido registrado - Mesa 5
[Barra] Preparando cerveza para Mesa 5...
[Cocina] Cocinando hamburguesa...
[Banda] Ahora suena: El Algoritmo del Amor
[SistemaSonido] Ajustando ecualizador para: El Algoritmo del Amor
[PANEL LED] La banda esta tocando "El Algoritmo del Amor"
[Barra] cerveza lista
[PANEL LED] Bebida servida en Mesa 5
[Cocina] hamburguesa lista
[PANEL LED] Comida preparada
[Banda] Ahora suena: Through the Fire and Flames
[SISTEMA HUMO] Cancion EPICA detectada! Disparando efectos de humo...
[SISTEMA HUMO] Humo en el escenario!

## Demostracion de extensibilidad

Se agrego SistemaDeHumo sin modificar Banda.java, EventBus.java ni ningun otro componente. Solo se añadio una linea en Main.java:

SistemaDeHumo sistemaHumo = new SistemaDeHumo(eventBus);

## Solucion de problemas

- javac: command not found -> Instalar Java JDK
- ClassNotFoundException -> Usar -d out y -cp out correctamente
- El humo no aparece -> Esperar cancion "Through the Fire and Flames"