import java.util.ArrayList;

public class Tablero {
    private ArrayList<ArrayList<Celda>> grilla;
    private int filas;
    private int columnas;

    public Tablero(int f, int c) {
        this.filas = f;
        this.columnas = c;
        this.grilla = new ArrayList<ArrayList<Celda>>();
        
        // Usamos for tradicionales con i y j
        for (int i = 0; i < this.filas; i++) {
            ArrayList<Celda> filaNueva = new ArrayList<Celda>();
            for (int j = 0; j < this.columnas; j++) {
                // Todas las celdas empiezan como muertas
                filaNueva.add(new Celda(new EstadoMuerta()));
            }
            this.grilla.add(filaNueva);
        }
    }

     // Coloca una celda con un estado especifico en una posicion.
    public void setCelda(int f, int c, EstadoCelda estado) {
        if (f >= 0 && f < filas && c >= 0 && c < columnas) {
            // Creamos una nueva celda con el estado que pasamos
            this.grilla.get(f).set(c, new Celda(estado));
        }
    }

    
     // Mira alrededor de una posicion y cuenta cuantas celdas estan vivas.
    public int contarVecinosVivos(int fila, int columna) {
        int vivos = 0;
        // Recorremos el cuadradito de 3x3 alrededor de la celda
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                
                // No contamos la celda del medio (la celda misma)
                if (i == fila && j == columna) { 
                    continue; 
                }
                
                // Verificamos no salirnos de los bordes del tablero
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    // Le preguntamos a la celda vecina si esta viva
                    if (this.grilla.get(i).get(j).estaViva()) {
                        vivos++;
                    }
                }
            }
        }
        return vivos;
    }

     // Hace que el juego avance a la siguiente generacion.
    public boolean evolucionar() {
        // Todas las celdas calculan su proximo estado (sin cambiar todavia)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int vecinos = contarVecinosVivos(i, j);
                this.grilla.get(i).get(j).calcularProximoEstado(vecinos);
            }
        }

        //  Ahora que todas calcularon, les pedimos que cambien al nuevo estado
        boolean huboCambios = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Si la celda efectivamente cambio de estado, anotamos que hubo cambios
                if (this.grilla.get(i).get(j).actualizarEstado()) {
                    huboCambios = true;
                }
            }
        }
        return huboCambios;
    }

    
     // Devuelve el dibujo del tablero para imprimir por pantalla.
    public String dibujar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Pedimos el dibujito de cada celda y un espacio
                sb.append(this.grilla.get(i).get(j).getRepresentacion()).append(" ");
            }
            sb.append("\n"); // Salto de linea al terminar la fila
        }
        return sb.toString();
    }
}