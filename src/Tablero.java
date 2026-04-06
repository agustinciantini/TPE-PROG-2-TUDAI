public class Tablero {
    Celda[][] celdas;
    private int filas;
    private int columnas;
    private int imprimir;


    public Tablero(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
        this.imprimir = imprimir;
		this.celdas = new Celda[filas][columnas];
	}
    public void addCelda(int fila, int columna, Celda celda) {
        if(fila < celdas.length && columna < celdas[0].length) {

        celdas[fila][columna] = celda;
        }
    }

    public void setCelda(int f, int c,Celda celda) {
		this.celdas[f][c]= celda;
	}

    
public boolean avanzarGeneracion() {
		
		boolean huboCambios = false;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				int vecinosVivos = contarVecinosVivos(i, j);
				celdas[i][j].estadoSiguiente(vecinosVivos);
			}
		}

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				boolean cambio = celdas[i][j].actualizar();
				if (cambio) {
					huboCambios = true;
				}
			}
		}
		return huboCambios;
	}

    public int contarVecinosVivos(int f, int c) {
            int contador = 0;
            // Bucle anidado que recorre el cuadrado de 3x3 alrededor de la celda
            for (int i = f - 1; i <= f + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    // Validamos límites del tablero y que no sea la celda central
                    if (i >= 0 && i < this.filas && j >= 0 && j < this.columnas && !(i == f && j == c)) {
                        if (this.celdas[i][j].estaViva()) {
                            contador++;
                        }
                    }
                }
            }
            return contador;
        }

    public void imprimir() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(this.celdas[i][j].getRepresentacion() + " ");
			}
			System.out.println(); // Salto de línea por fila
		}
	}

}