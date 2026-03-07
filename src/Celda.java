

public class Celda {
    private EstadoCelda estadoActual;
    private EstadoCelda estadoSiguiente;

    public Celda(EstadoCelda estadoInicial) {
        this.estadoActual = estadoInicial;
    }

    public boolean estaViva() {
        return this.estadoActual.estaViva();
    }

    public void calcularProximoEstado(int vecinosVivos) {
        this.estadoSiguiente = this.estadoActual.proximoEstado(vecinosVivos);
    }

    public boolean actualizarEstado() {
        boolean cambio = !this.estadoActual.getClass().equals(this.estadoSiguiente.getClass());
        this.estadoActual = this.estadoSiguiente;
        return cambio;
    }

    public String getRepresentacion() {
        return this.estadoActual.getRepresentacion();
    }
}