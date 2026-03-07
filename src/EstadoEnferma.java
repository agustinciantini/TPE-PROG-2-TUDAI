

public class EstadoEnferma implements EstadoCelda {
    protected final String REPRESENTACION_ENFERMA = "E";

    @Override
    public boolean estaViva() {
        return true;
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        return new EstadoMuerta(); // Muere sí o sí en la siguiente gen
    }

    @Override
    public String getRepresentacion() {
        return REPRESENTACION_ENFERMA;
    }
}