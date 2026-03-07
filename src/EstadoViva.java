

public class EstadoViva implements EstadoCelda {
    protected final String REPRESENTACION_VIVA = "O";
    protected final double PROBABILIDAD_ENFERMAR = 0.25;

    @Override
    public boolean estaViva() {
        return true;
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        // Lógica de enfermedad (extensión)
        if (Math.random() < PROBABILIDAD_ENFERMAR) {
            return new EstadoEnferma();
        }
        
        if (vecinosVivos < 2 || vecinosVivos > 3) {
            return new EstadoMuerta();
        }
        return this;
    }

    @Override
    public String getRepresentacion() {
        return REPRESENTACION_VIVA;
    }
}
