import java.util.Scanner;
import java.io.File;

    // Clase principal que gestiona la ejecucion del Juego de la Vida.
    // Se encarga de la interaccion con el usuario y la carga de datos.

public class SimuladorJuego {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- JUEGO DE LA VIDA ---");
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));
        System.out.print("Ingrese el nombre del archivo (ej: tablero.txt): ");
        
        String nombre = sc.next();
        
        // Busqueda del archivo en diferentes rutas para mayor flexibilidad
        Tablero tablero = buscarYCargarArchivo(nombre);

        if (tablero != null) {
            System.out.println("Archivo cargado con exito.");
            ejecutarSimulacion(tablero);
        } else {
            System.out.println("ERROR: No se encontro el archivo '" + nombre + "'.");
            System.out.println("Asegurate de que este en la carpeta 'ejemplos/' o en la raiz.");
        }
        sc.close();
    }

     // Busca el archivo en la raiz, en ejemplos/ o en src/ejemplos/.
    private static Tablero buscarYCargarArchivo(String nombre) {
        String[] rutasPosibles = {
            nombre,
            "ejemplos/" + nombre,
            "src/ejemplos/" + nombre
        };

        for (int i = 0; i < rutasPosibles.length; i++) {
            Tablero t = cargarDesdeArchivo(rutasPosibles[i]);
            if (t != null) {
                return t;
            }
        }
        return null;
    }
   
    //  Lee el archivo, extrae las dimensiones y construye el tablero inicial.
    public static Tablero cargarDesdeArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                return null;
            }

            Scanner lector = new Scanner(archivo);
            
            // Saltamos lineas que no empiecen con numeros (posibles encabezados)
            while (lector.hasNextLine() && !lector.hasNextInt()) {
                lector.nextLine();
            }

            if (lector.hasNextInt()) {
                int filas = lector.nextInt();
                int columnas = lector.nextInt();
                
                // Limpieza del buffer: consumimos el resto de la linea de dimensiones
                if (lector.hasNextLine()) {
                    lector.nextLine(); 
                }

                Tablero nuevo = new Tablero(filas, columnas);
                int fCont = 0;

                // Leemos las lineas de la grilla segun el numero de filas indicado
                while (lector.hasNextLine() && fCont < filas) {
                    String linea = lector.nextLine();
                    
                    // Si la linea esta vacia, la saltamos para evitar errores
                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    // Procesamos cada caracter segun las columnas indicadas
                    for (int j = 0; j < columnas && j < linea.length(); j++) {
                        char c = linea.charAt(j);
                        nuevo.setCelda(fCont, j, fabricarEstado(c));
                    }
                    fCont++;
                }
                lector.close();
                return nuevo;
            }
            lector.close();
        } catch (Exception e) {
            // Manejo basico de errores de lectura o formato invalido
            return null;
        }
        return null;
    }

    // Soporta los estados base y los de extension (Latente y Enferma).
    private static EstadoCelda fabricarEstado(char c) {
        // En el archivo de ejemplo, 'x' minuscula representa una celda viva
        if (c == 'x' || c == 'O' || c == 'o') {
            return new EstadoViva();
        } else if (c == 'X') {
            // 'X' mayuscula se reserva para el estado Latente segun el enunciado
            return new EstadoLatente();
        } else if (c == 'E') {
            return new EstadoEnferma();
        }
        // Cualquier otro caracter (como '.') se considera una celda muerta
        return new EstadoMuerta();
    }
    
     // Ejecuta el ciclo de vida del juego hasta que no haya mas cambios.
    private static void ejecutarSimulacion(Tablero t) {
        while (true) {
            // Mostramos el tablero actual por consola
            System.out.println("\n" + t.dibujar());
            
            // Se calcula la nueva generacion y se verifica si el tablero cambio
            boolean huboCambios = t.evolucionar();

            // Si el tablero no sufrio modificaciones, la simulacion se detiene
            if (!huboCambios) {
                break; 
            }
            
            try {
                // Pausa configurable para observar la evolucion (600ms)
                Thread.sleep(600);
            } catch (Exception e) {
                // Manejo silencioso de interrupciones del hilo
            }
        }
        System.out.println("\nSimulacion finalizada: El tablero se ha estabilizado.");
    }
}