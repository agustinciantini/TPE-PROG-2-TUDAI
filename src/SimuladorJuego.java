import java.io.File;
import java.util.Scanner;

public class SimuladorJuego {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- JUEGO DE LA VIDA ---");
        System.out.print("Ingrese el nombre del archivo: ");
        String nombre = sc.next();
        
        Tablero tablero = buscarYCargarArchivo(nombre);

        if (tablero != null) {
            System.out.println("Tablero cargado con éxito.");
            ejecutarSimulacion(tablero);
        } else {
            System.out.println("ERROR: No se pudo cargar el archivo.");
        }
        sc.close();
    }

private static Tablero buscarYCargarArchivo(String nombre) {
    String[] rutasPosibles = {
        nombre,                                          // Raíz: PROGPARCIALES/
        "ejemplos/" + nombre,                           // PROGPARCIALES/ejemplos/
        "src/ejemplos/" + nombre,                       // PROGPARCIALES/src/ejemplos/
        "TPEPROG/src/ejemplos/" + nombre        // <--- LA RUTA DONDE ESTÁN REALMENTE
    };

    for (String ruta : rutasPosibles) {
        File archivo = new File(ruta);
        // Imprime para que veas qué está pasando
        if (archivo.exists()) {
            System.out.println("¡Encontrado en: " + ruta + "!");
            return LectorArchivo.cargar(ruta);
        }
    }
    return null;
}

    private static void ejecutarSimulacion(Tablero t) {
        while (true) {
            System.out.println("\n");
            t.imprimir();
            if (!t.avanzarGeneracion()) {
                System.out.println("\nFin.");
                break; 
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}