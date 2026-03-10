public interface Condicion {
    boolean cumple(Celda c);
}

class CondicionViva implements Condicion {
    @Override
    public boolean cumple(Celda c) {
        return c.estaViva();
    }
}