public class EstadoEnferma implements EstadoCelda {
    @Override
    public boolean estaViva() {
        return true;
    }

    @Override
    public EstadoCelda comprobarEstado(int vecinosVivos) {

        return new EstadoMuerta();
    }

    @Override
    public char getSimbolo() {
        return 'E';
    }
}