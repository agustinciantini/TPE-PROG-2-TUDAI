public class Celda {
    private EstadoCelda estadoActual;
    private EstadoCelda estadoSiguiente;

    public Celda(EstadoCelda estadoInicial) {
        this.estadoActual = estadoInicial;
    }

    public boolean estaViva() {
        return estadoActual.estaViva();
    }

    public void estadoSiguiente(int vecinosVivos) {
        this.estadoSiguiente = estadoActual.comprobarEstado(vecinosVivos);
    }

    public boolean actualizar() {
		boolean cambio = !estadoActual.equals(estadoSiguiente);
		estadoActual = estadoSiguiente;
		return cambio;
	}

    // Para dibujarla en la consola, le pedimos al estado su letra (X, L, E, O)
	public char getRepresentacion() {
		return estadoActual.getSimbolo();
	}
}