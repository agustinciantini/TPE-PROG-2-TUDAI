
public class EstadoViva implements EstadoCelda {
    protected final String REPRESENTACION_VIVA = "O";
    protected final double PROBABILIDAD_ENFERMAR = 0.25;

    @Override
    public boolean estaViva() {
        return true;
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        // Regla de Reproduccion: Una celda muerta con exactamente 3 vecinos vivos 
        // se convierte en una celda "Viva" en la siguiente generacion.
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
