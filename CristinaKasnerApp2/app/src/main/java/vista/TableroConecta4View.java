package vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import es.uam.eps.multij.Movimiento;
import es.uam.eps.multij.Partida;
import logica.MovimientoConecta4;
import logica.TableroConecta4;
import root.cristinakasnerapp2.R;

/**
 * Created by e264564 on 4/03/16.
 */
public class TableroConecta4View extends View {

    private Partida game;



    private float heightOfTile;
    private float widthOfTile;

    private int widthOfBoard;
    private int heightOfBoard;



    private OnPlayListener listener  = null;

    private Paint circlePaint, backgroundPaint;


    public TableroConecta4View(Context context, AttributeSet attributes) {
        super(context, attributes);
        setFocusable(true);
        setFocusableInTouchMode(true);


        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStrokeWidth(2);

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = widthSize;
        int height = heightSize;

        if (widthSize < heightSize)
            height = widthSize;
        else
            width = heightSize;

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    protected void onSizeChanged (int width, int height, int oldWidth, int oldHeight)
    {
        TableroConecta4 tab4 = (TableroConecta4) game.getTablero();
        if (width < height)
            height = width;
        else
            width = height;

        widthOfBoard = width;
        heightOfBoard = height;

        widthOfTile = width / tab4.COLS;
        heightOfTile = height / tab4.FILAS;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    private void drawTiles (Canvas canvas, Paint paint) {

        TableroConecta4 tab = (TableroConecta4) game.getTablero();
        float centerX, centerY, radio;
        if (widthOfBoard < heightOfBoard)
            radio = widthOfBoard / 20;
        else
            radio = heightOfBoard / 20;
        for (int i = 0; i < tab.FILAS; i++) {
            centerY = heightOfTile * (1 + 2 * (tab.FILAS - i - 1)) / 2f;
            for (int j = 0; j < tab.COLS; j++) {
                // paint.setColor(getResources().getColor(R.color.color_error));
                if (tab.tablero[i][j] == tab.VACIO) {
                    paint.setColor(getResources().getColor(R.color.colorVacio));
                } else if (tab.tablero[i][j] == tab.JHUMANO) {
                    paint.setColor(getResources().getColor(R.color.colorJHumano));
                } else if (tab.tablero[i][j] == tab.JALEAT) {
                    paint.setColor(getResources().getColor(R.color.colorJAleat));
                }
                centerX = widthOfTile * (1 + 2 * j) / 2f;
                canvas.drawCircle(centerX, centerY, radio, paint);
            }
        }
    }


    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);

        canvas.drawRect(0, 0, widthOfBoard, heightOfBoard, backgroundPaint);
        drawTiles(canvas, circlePaint);
    }

    private int yToIndex (float y){
        TableroConecta4 tab = (TableroConecta4) game.getTablero();
        return y < tab.FILAS*heightOfTile ? (int)(y/heightOfTile) : -1;
    }

    private int xToIndex (float x){
        TableroConecta4 tab = (TableroConecta4) game.getTablero();
        return x < tab.FILAS*widthOfTile ? (int)(x/widthOfTile) : -1;
    }

    public boolean onTouchEvent(MotionEvent event) {
        //Toast.makeText(this.getContext(), "Prueba Ontouchevent", Toast.LENGTH_LONG).show();


        TableroConecta4 tab4= (TableroConecta4)game.getTablero();
        if(tab4.isFinished())
            return false;

        int xIndex ;
        int yIndex ;

        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                xIndex = xToIndex(x);
                yIndex = yToIndex(y);

                if (xIndex < 0 || yIndex < 0)
                    return false;

                if (tab4.isFinished()) {
                    Toast.makeText(this.getContext(), R.string.gameOverTitle, Toast.LENGTH_LONG).show();
                    return false;
                }

                Movimiento movimiento = new MovimientoConecta4(yIndex);
                if (!tab4.esValido(movimiento)) {
                    Toast.makeText(this.getContext(), R.string.noValido, Toast.LENGTH_SHORT).show();
                    return false;

                }

                else {

                    listener.onPlay(yIndex);

                    return super.onTouchEvent(event);

                }



        }
        return super.onTouchEvent(event);
    }

    public void setOnPlayListener(OnPlayListener listener) {
        this.listener = listener;
    }
    public void setPartida(Partida part){
        this.game = part;
    }
}
