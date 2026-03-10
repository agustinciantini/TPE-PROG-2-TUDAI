
public class EstadoLatente implements EstadoCelda {
    protected final String REPRESENTACION_LATENTE = "X";

    @Override
    public boolean estaViva() {
        return false; // Se considera muerta
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        // Regla especial de extension: Una celda Latente con exactamente 
        // 1 vecino vivo se convierte en una celda "Viva".
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