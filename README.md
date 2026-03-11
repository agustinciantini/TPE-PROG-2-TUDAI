#  Juego de la Vida - TPE Final PROG2

Este proyecto implementa una solución orientada a objetos para el **Juego de la Vida**, extendida con estados dinámicos (Enferma y Latente). Se centra en la aplicación de patrones de diseño para lograr un sistema robusto, escalable y fácil de mantener.

### Clases Principales y Responsabilidades:

| Clase / Interfaz | Responsabilidad |
| :--- | :--- |
| **`EstadoCelda`** | **Interfaz (Abstracción)**. Define el comportamiento polimórfico de cualquier estado posible. |
| **`Celda`** | **Contexto**. Mantiene la instancia del estado actual. Delega la lógica de evolución al objeto `Estado`. |
| **`Tablero`** | **Contenedor**. Gestiona la grilla de celdas, calcula la vecindad y asegura que la evolución sea atómica (no actualiza estados hasta que todos hayan sido calculados). |
| **`EstadoViva / Muerta`** |  Implementan las reglas básicas del juego original.
| **`EstadoLatente / Enferma`** | Estados adicionales que extienden la funcionalidad base.
| **`SimuladorJuego`** | **Interfaz de Usuario (CLI)**. Controla la E/S, carga de archivos y el ciclo de vida de la simulación. |

### Conceptos básicos:
* **Polimorfismo:** El `Tablero` no sabe qué reglas aplica cada celda; solo invoca el método `proximoEstado()` de la interfaz `EstadoCelda`.
* **Delegación:** La clase `Celda` no contiene lógica de juego; **delega** esa responsabilidad a sus estados concretos (`EstadoViva`, `EstadoMuerta`, etc.).
* **Abstracción:** Los detalles de cómo una celda se enferma o revive están encapsulados en sus respectivas clases, exponiendo solo lo necesario.

---

## Instrucciones de Compilación y Ejecución

### 1. Ejecución del Programa:
1. En el explorador de archivos de la izquierda, navega hasta `src/SimuladorJuego.java`.
2. Haz doble clic para abrir el archivo.
3. Haz clic en el botón **Run** (el triángulo pequeño) que aparece en la esquina superior derecha del editor, o presiona `F5`.

### 2. Carga de archivo:
Una vez que el programa inicie, verás mensajes en la **Terminal** inferior:

1. El programa te pedirá: `Ingrese el nombre del archivo (ej: latencia.txt):`.
2. Escribe el nombre de uno de los archivos que están en la carpeta `ejemplos` (por ejemplo: `latencia.txt`) y presiona **Enter**.
3. El programa buscará automáticamente dentro de `src/ejemplos/` y comenzará la simulación.