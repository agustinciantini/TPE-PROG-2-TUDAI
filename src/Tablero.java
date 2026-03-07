
import java.util.ArrayList;

public class Tablero {
    private ArrayList<ArrayList<Celda>> grilla;
    private int filas;
    private int columnas;

    public Tablero(int f, int c) {
        this.filas = f;
        this.columnas = c;
        this.grilla = new ArrayList<ArrayList<Celda>>();
        
        // Bucle for clasico con i para filas
        for (int i = 0; i < this.filas; i++) {
            ArrayList<Celda> filaNueva = new ArrayList<Celda>();
            // Bucle for clasico con j para columnas
            for (int j = 0; j < this.columnas; j++) {
                filaNueva.add(new Celda(new EstadoMuerta()));
            }
            this.grilla.add(filaNueva);
        }
    }

    public void setCelda(int f, int c, EstadoCelda estado) {
        if (f >= 0 && f < filas && c >= 0 && c < columnas) {
            // Reemplazamos la celda muerta por defecto por la cargada
            this.grilla.get(f).set(c, new Celda(estado));
        }
    }

    public int contarVecinosVivos(int fila, int columna) {
        int vivos = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i == fila && j == columna) { continue; }
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    if (this.grilla.get(i).get(j).estaViva()) {
                        vivos++;
                    }
                }
            }
        }
        return vivos;
    }

    public boolean evolucionar() {
        // Paso 1: Calcular siguiente estado basado en el actual
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int vecinos = contarVecinosVivos(i, j);
                this.grilla.get(i).get(j).calcularProximoEstado(vecinos);
            }
        }

        // Paso 2: Actualizar y verificar si hubo cambios
        boolean huboCambios = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (this.grilla.get(i).get(j).actualizarEstado()) {
                    huboCambios = true;
                }
            }
        }
        return huboCambios;
    }

    public String dibujar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(this.grilla.get(i).get(j).getRepresentacion()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}