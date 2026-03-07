

public class EstadoMuerta implements EstadoCelda {
    protected final String REPRESENTACION_MUERTA = ".";

    @Override
    public boolean estaViva() {
        return false;
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        if (vecinosVivos == 3) {
            return new EstadoViva();
        }
        return this;
    }

    @Override
    public String getRepresentacion() {
        return REPRESENTACION_MUERTA;
    }
}
