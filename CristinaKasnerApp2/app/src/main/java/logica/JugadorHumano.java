package logica;

import es.uam.eps.multij.Evento;
import es.uam.eps.multij.Jugador;
import es.uam.eps.multij.Tablero;

/**
 * Created by e264564 on 4/03/16.
 */
public class JugadorHumano implements Jugador{
    String nombre;

    public JugadorHumano(){
        this.nombre = "observador";
    }
    public JugadorHumano(String nombre) {
        this.nombre = nombre;
    }
    public void onCambioEnPartida(Evento evento) {
        // TODO Auto-generated method stub

        switch (evento.getTipo()) {

            case Evento.EVENTO_CAMBIO:

                break;
            case Evento.EVENTO_TURNO:
                break;

        }
    }

    public String getNombre() {
        // TODO Auto-generated method stub
        return this.nombre;
    }

    public boolean puedeJugar(Tablero tablero) {
        // TODO Auto-generated method stub
        return true;
    }
}
