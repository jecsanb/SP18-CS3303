package com.example.jecsan.animation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * an instance of WalkAnimation will draw a solid circle after taking rest for 1 sec
 */

public class WalkAnimation extends View {

    private float cx, cy,radius, sHeight, sWidth,angle;
    private Random r;
    Context c;

    public WalkAnimation(Context context, float w, float h){
        super(context);
        c = context;
        sWidth = w - 100;
        sHeight = h - 100;
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
        canvas.drawCircle(cx, cy,radius,paint);
        angle = ((angle + 1) % 360);
        updateCoordinates();
        Log.i("WalkAnimation","Angle:" + Double.toString(angle));
        try{
            Thread.sleep(10);

        }
        catch (InterruptedException e){
            Toast.makeText(c, "Fatal error during the sleep interval", Toast.LENGTH_SHORT).show();
        }
        // invalidate whole view and invoke the callback onDraw()
       invalidate();
    }

    private void updateCoordinates(){

        //coordinates within the region and
        //x = hradcos(theta)
        //y = vradsin(theta)

        float rads = (float)  Math.toRadians(angle);
        cx = sWidth/2 + (sWidth/2 - 50)*(float)Math.cos(rads);
        cy = (float)(sHeight/2.2 )+ (sHeight/2 - 100 )*(float)Math.sin(rads);

        Log.i("WalkAnimation","x:" + Float.toString(cx));
        Log.i("WalkAnimation","y:" + Float.toString(cy));


    }
}
