
import java.util.Scanner;
import java.io.File;

public class SimuladorJuego {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- JUEGO DE LA VIDA ---");
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));
        System.out.print("Ingrese el nombre del archivo (ej: latencia.txt): ");
        
        String nombre = sc.next();
        
        // Intentamos cargar el archivo buscando en varias rutas posibles
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

    /**
     * Intenta encontrar el archivo en la raiz, en ejemplos/ o en src/ejemplos/
     */
    private static Tablero buscarYCargarArchivo(String nombre) {
        String[] rutasPosibles = {
            nombre,
            "ejemplos/" + nombre,
            "src/ejemplos/" + nombre
        };

        for (String ruta : rutasPosibles) {
            Tablero t = cargarDesdeArchivo(ruta);
            if (t != null) return t;
        }
        return null;
    }

    public static Tablero cargarDesdeArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                return null;
            }

            Scanner lector = new Scanner(archivo);
            
            // Saltamos lineas vacias hasta encontrar el primer numero
            while (lector.hasNextLine() && !lector.hasNextInt()) {
                lector.nextLine();
            }

            if (lector.hasNextInt()) {
                int filas = lector.nextInt();
                int columnas = lector.nextInt();
                if (lector.hasNextLine()) lector.nextLine(); // Limpiar buffer

                Tablero nuevo = new Tablero(filas, columnas);
                int fCont = 0;

                while (lector.hasNextLine() && fCont < filas) {
                    String linea = lector.nextLine().trim();
                    if (linea.isEmpty()) continue; // Ignorar lineas vacias

                    for (int j = 0; j < columnas && j < linea.length(); j++) {
                        char c = linea.charAt(j);
                        nuevo.setCelda(fCont, j, fabricarEstado(c)); //
                    }
                    fCont++;
                }
                lector.close();
                return nuevo;
            }
            lector.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private static EstadoCelda fabricarEstado(char c) {
        // Mantenemos la logica polimorfica para los nuevos estados
        if (c == 'x' || c == 'O' || c == 'o') {
            return new EstadoViva();
        } else if (c == 'X') {
            return new EstadoLatente();
        } else if (c == 'E') {
            return new EstadoEnferma();
        }
        return new EstadoMuerta();
    }

    private static void ejecutarSimulacion(Tablero t) {
        boolean hayCambios = true;
        while (hayCambios) {
            System.out.println("\n" + t.dibujar()); //
            hayCambios = t.evolucionar(); //
            try { Thread.sleep(600); } catch (Exception e) {}
        }
        System.out.println("Simulacion finalizada por falta de cambios.");
    }
}