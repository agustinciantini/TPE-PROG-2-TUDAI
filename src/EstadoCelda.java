public interface EstadoCelda {
    public boolean estaViva();

    public EstadoCelda comprobarEstado(int vecinosVivos);

    char getSimbolo();
}
