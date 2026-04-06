public class EstadoMuerta implements EstadoCelda {

    @Override
    public boolean estaViva() {
        return false;
    }

    @Override
    public EstadoCelda comprobarEstado(int vecinosVivos) {
        if (vecinosVivos == 3) {
            return new EstadoViva();
        }
        return this; // Permanece muerta
    }

    @Override
    public char getSimbolo() {
        return '.';
    }
}