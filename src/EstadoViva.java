

public class EstadoViva implements EstadoCelda {

    public boolean estaViva(){
        return true;
    };

    @Override
    public EstadoCelda comprobarEstado(int vecinosVivos) {
        if (Math.random() < 0.25) {
            return new EstadoEnferma(); 
        }
        if (vecinosVivos < 2) {
            return new EstadoMuerta(); // Muere por soledad
        } else if (vecinosVivos == 2 || vecinosVivos == 3) {
            return this; // Permanece viva
        } else {
            return new EstadoMuerta(); // Muere por sobrepoblación
        }
    }
    @Override
    public char getSimbolo() {
        return 'X';
    }
}