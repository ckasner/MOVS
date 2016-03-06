package logica;

import java.util.ArrayList;

import es.uam.eps.multij.ExcepcionJuego;
import es.uam.eps.multij.Movimiento;
import es.uam.eps.multij.Tablero;

/**
 * Created by e264564 on 4/03/16.
 */
public class TableroConecta4 extends Tablero {
    public int[][] tablero;
    public int VACIO = 0;
    public int JHUMANO = 1;
    public int JALEAT = 2;
    public int FILAS = 6;
    public int COLS = 7;



    public TableroConecta4() {
        this.tablero = new int[FILAS][COLS];
        for(int x = 0; x<FILAS ; x++){
            for(int y = 0 ; y<COLS ; y++){
                tablero[x][y] = VACIO;
            }
        }

        this.estado = EN_CURSO;


    }

    public void mueve(Movimiento m) throws ExcepcionJuego {
        int  x;
        int col = Integer.parseInt(m.toString());
        if(this.esValido(m)){
            for(x = 0; x<FILAS; x++){
                if(tablero[x][col]==0){
                    tablero[x][col] = this.turno + 1;
                    break;
                }
            }

            this.ultimoMovimiento = m;
            if((this.compruebaGanar(x, col))== true){
                this.estado= FINALIZADA;
                return;
            }


            this.cambiaTurno();
            if(this.movimientosValidos().equals(null)){
                this.estado = TABLAS;
                return;
            }
            return;
        }else{
            ExcepcionJuego e = new ExcepcionJuego("El movimiento " + m.toString() + "no es válido\n");
            throw e;
        }



    }

    public boolean esValido(Movimiento m)throws NumberFormatException{
        try {
            int col = Integer.parseInt(m.toString());
            if(col< 0 || col>COLS-1){
                return false;
            }
            if (this.tablero[FILAS-1][col]!= 0){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
            // TODO: handle exception
        }


    }

    public ArrayList<Movimiento> movimientosValidos() {
        ArrayList<Movimiento> movsValidos= new ArrayList<>();

        for(int y = 0; y< COLS; y++){
            if(tablero[FILAS-1][y]== 0){
                movsValidos.add(new MovimientoConecta4(y));
            }
        }

        return movsValidos;
    }

    public String tableroToString() {
        String stablero = new String();
        stablero = "Tablero\n";
        for(int x=FILAS-1;x>=0;x-- ){
            for(int y=0;y<7;y++){
                stablero = stablero.concat(Integer.toString(this.tablero[x][y]) +" ");
            }
            stablero = stablero.concat("\n");
        }

        return stablero;
    }

    public boolean compruebaGanar(int fila, int columna){
        if(this.numJugadas<FILAS){
            return false;
        }
        int contador = 1, auxcol;
        for(int y = columna-1; y>=0; y--){
            if(y<0 || y>FILAS){
                break;
            }
            if(tablero[fila][y] != this.turno +1){
                break;
            }
            contador++;
            if(contador == 4|| contador >4){
                return true;
            }
        }
        for (int y = columna+1; y<7; y++){
            if(y<0 || y>6){
                break;
            }
            if(tablero[fila][y] != this.turno +1){
                break;
            }
            contador++;
            if(contador == 4|| contador >4){
                return true;
            }
        }
        contador = 1;
        for(int y=fila-1; y>=0;y--){
            auxcol = columna-fila+y;
            if(auxcol<0 || auxcol>6||y<0 ||y>5){
                break;
            }
            if(tablero[y][auxcol]!= this.turno + 1){
                break;
            }
            contador++;
            if(contador == 4||contador >4 ){

                return true;
            }
        }
        for(int y=fila+1; y<7;y++){
            auxcol = columna-fila+y;
            if(auxcol<0 || auxcol>6 ||y<0 ||y>5){
                break;
            }
            if(tablero[y][auxcol]!= this.turno + 1){
                break;
            }
            contador++;
            if(contador == 4||contador >4 ){

                return true;
            }
        }
        contador = 1;
        for(int y = fila-1; y>=0; y--){
            if(y<0 || y>5){
                break;
            }
            if(tablero[y][columna] != this.turno +1){
                break;
            }
            contador++;
            if(contador == 4|| contador >4){
                return true;
            }
        }
        for (int y = fila+1; y<7; y++){
            if(y<0 || y>5){
                break;
            }
            if(tablero[y][columna] != this.turno +1){
                break;
            }
            contador++;
            if(contador == 4|| contador >4){
                return true;
            }
        }
        contador = 1;
        for(int y=fila-1; y>=0;y--){
            auxcol = columna+fila-y;
            if(auxcol<0 || auxcol>6 || y<0 || y>5){
                break;
            }
            if(tablero[y][auxcol]!= this.turno + 1){
                break;
            }
            contador++;
            if(contador == 4||contador >4 ){

                return true;
            }
        }
        for(int y=fila+1; y<7;y++){
            auxcol = columna+fila-y;
            if(auxcol<0 || auxcol>6|| y<0 || y>5){
                break;
            }
            if(tablero[y][auxcol]!= this.turno + 1){
                break;
            }
            contador++;
            if(contador == 4||contador >4 ){

                return true;
            }
        }

        return false;

    }
    public void stringToTablero(String cadena) throws ExcepcionJuego {
        // TODO Auto-generated method stub

    }

    public String toString() {
        String tab="";
        for(int i=FILAS-1;i>=0;i--){
            tab=tab+"|";
            for(int j=0; j<COLS;j++){
                tab=tab+tablero[i][j]+"|";
            }
            tab=tab+"\n";
        }
        return tab;

    }

    public boolean isFinished(){
        return this.estado != EN_CURSO;
    }

    public void setEstado(int est){
        this.estado = est;
    }
}
