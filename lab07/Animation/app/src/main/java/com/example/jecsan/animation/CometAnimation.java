package com.example.jecsan.animation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Author: Jecsan Blanco
 * Version: 03/28/18
 * This app draws a circle that travels the path of an ellipse  on the screen.
 */

public class CometAnimation extends View {

    private float ellipseX, ellipseY,radius, sHeight, sWidth,angle;
    Context context;

    public CometAnimation(Context context, float screenWidth, float screenHeight){
        super(context);
        this.context = context;
        sWidth = screenWidth - 100; // corrects to keep away from the edges.
        sHeight = screenHeight - 100;
        radius = 50;
        angle = 0;
        updateCoordinates();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);

        canvas.drawCircle(ellipseX, ellipseY,radius,paint);

        angle = ((angle + 1) % 360); updateCoordinates();

        Log.i("WalkAnimation","Angle:" + Double.toString(angle));
        try{
            //fps
            Thread.sleep(10);

        }
        catch (InterruptedException e){
            Toast.makeText(this.context, "Fatal error during the sleep interval", Toast.LENGTH_SHORT).show();
        }
        // invalidate whole view and invoke the callback onDraw()
       invalidate();
    }

    private void updateCoordinates(){

       /* gives the x and y  coordinates on a ellipse given an angle.o
        An ellipse can be defined as the locus of all points that satisfy the equations
        x = a cos t
        y = b sin t
        where:
        x,y are the coordinates of any point on the ellipse,
                a, b are the radius on the x and y axes respectively, ( * See radii notes below )
        t is the parameter, which ranges from 0 to 2π radians.
        */

        float rads = (float)  Math.toRadians(angle);
        float a = (sWidth/2 - 50);
        float b = (sHeight/2 -100);
        float shiftX = sWidth/2;
        float shiftY = sHeight/2;

        ellipseX = shiftX + a*(float)Math.cos(rads);
        ellipseY = shiftY + b*(float)Math.sin(rads);

        Log.i("WalkAnimation","x:" + Float.toString(ellipseX));
        Log.i("WalkAnimation","y:" + Float.toString(ellipseY));


    }
}
