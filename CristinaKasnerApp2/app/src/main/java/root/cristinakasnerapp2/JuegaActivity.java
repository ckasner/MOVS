package root.cristinakasnerapp2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.uam.eps.multij.AccionMover;
import es.uam.eps.multij.Evento;
import es.uam.eps.multij.ExcepcionJuego;
import es.uam.eps.multij.Jugador;
import es.uam.eps.multij.JugadorAleatorio;
import es.uam.eps.multij.Partida;
import es.uam.eps.multij.Tablero;
import logica.MovimientoConecta4;
import logica.TableroConecta4;
import vista.OnPlayListener;
import vista.TableroConecta4View;

public class JuegaActivity extends AppCompatActivity implements OnPlayListener,Jugador{



        Partida game;
        TableroConecta4View tableroView;
        TableroConecta4 tablero;
        ArrayList<Jugador> jugadores;
        TextView infotext;










        //Partida game = new Partida();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_juega);
            infotext = (TextView)findViewById(R.id.infotext);
            Jugador jugadorAleatorio = new JugadorAleatorio("Máquina");

            jugadores = new ArrayList<Jugador>();
            jugadores.add(this);
            jugadores.add(jugadorAleatorio);
            tableroView = (TableroConecta4View)findViewById(R.id.board);
            tableroView.setOnPlayListener(this);
            tablero = new TableroConecta4();
            game = new Partida(tablero, jugadores);
            tableroView.setPartida(game);

            if(tablero.getEstado()== Tablero.EN_CURSO){
                game.comenzar(tablero, jugadores);

            }

            if(tablero.getEstado()==Tablero.TABLAS){
                infotext.setText(R.string.Tablas);
            }
            if(tablero.getEstado()==Tablero.FINALIZADA) {
                infotext.setText("Gana: "+ jugadores.get(tablero.getTurno()).getNombre());
            }

        }

        @Override
        public String getNombre() {
            return "Humano";
        }

        @Override
        public boolean puedeJugar(Tablero tablero) {
            return true;
        }

        @Override
        public void onPlay(int columna) {
            try {
                game.realizaAccion(new AccionMover(this, new MovimientoConecta4(columna)));
            } catch (ExcepcionJuego excepcionJuego) {
                Toast.makeText(tableroView.getContext(), R.string.Error, Toast.LENGTH_LONG).show();
                return;
            }

        }

        @Override
        public void onCambioEnPartida(Evento evento) {
            switch (evento.getTipo()) {

                case Evento.EVENTO_CAMBIO:
                    tableroView.invalidate();
                    break;
                case Evento.EVENTO_TURNO:

                    tableroView.invalidate();
                    break;

                default:
                    break;
            }

        }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tablero",tablero.tableroToString());
        return;
    }
    }

