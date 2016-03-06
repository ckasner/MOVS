package logica;

import es.uam.eps.multij.Movimiento;

/**
 * Created by e264564 on 4/03/16.
 */
public class MovimientoConecta4 extends Movimiento{
    private int columna;

    public MovimientoConecta4(int arg) {
        // TODO Auto-generated constructor stub
        columna = arg;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return Integer.toString(columna);
    }

    public boolean equals(Object o) {
        if(!(o instanceof MovimientoConecta4)){
            return false;
        }
        MovimientoConecta4 obj = (MovimientoConecta4) o;
        return obj.columna == this.columna;
    }
}
