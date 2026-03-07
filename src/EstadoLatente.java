

public class EstadoLatente implements EstadoCelda {
    protected final String REPRESENTACION_LATENTE = "X";

    @Override
    public boolean estaViva() {
        return false; // Se considera muerta
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        if (vecinosVivos == 1) {
            return new EstadoViva();
        }
        return this;
    }

    @Override
    public String getRepresentacion() {
        return REPRESENTACION_LATENTE;
    }
}