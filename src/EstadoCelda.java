

public interface EstadoCelda {
    boolean estaViva();
    EstadoCelda proximoEstado(int vecinosVivos);
    String getRepresentacion();
}