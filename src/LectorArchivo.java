import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase de utilidad para cargar la configuración inicial desde un archivo.
 */
public class LectorArchivo {

    public static Tablero cargar(String ruta) {
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()) return null;

            Scanner lector = new Scanner(archivo);
            
            // 1. Saltar posibles encabezados hasta encontrar los números de dimensión
            while (lector.hasNextLine() && !lector.hasNextInt()) {
                lector.nextLine();
            }

            if (!lector.hasNextInt()) {
                lector.close();
                return null;
            }

            // 2. Leer dimensiones
            int filas = lector.nextInt();
            int columnas = lector.nextInt();
            if (lector.hasNextLine()) lector.nextLine(); // Limpiar buffer

            Tablero tablero = new Tablero(filas, columnas);
            
            // 3. Procesar la grilla
            for (int f = 0; f < filas && lector.hasNextLine(); f++) {
                String linea = lector.nextLine();
                // Saltamos líneas vacías
                if (linea.trim().isEmpty()) {
                    f--; // No contamos esta fila
                    continue;
                }

                for (int c = 0; c < columnas && c < linea.length(); c++) {
                    char simbolo = linea.charAt(c);
                    tablero.setCelda(f, c, fabricarCelda(simbolo));
                }
            }

            lector.close();
            return tablero;

        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado en " + ruta);
            return null;
        }
    }

    /**
     * Lógica de traducción de caracteres a Celdas con sus estados.
     */
    private static Celda fabricarCelda(char simbolo) {
        switch (simbolo) {
            case 'O': case 'o': case 'x':
                return new Celda(new EstadoViva());
            case 'E': case 'e':
                return new Celda(new EstadoEnferma());
            case 'L': case 'X':
                return new Celda(new EstadoLatente());
            default:
                return new Celda(new EstadoMuerta());
        }
    }
}