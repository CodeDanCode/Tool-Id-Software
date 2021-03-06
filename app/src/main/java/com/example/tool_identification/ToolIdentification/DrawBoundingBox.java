package com.example.tool_identification.ToolIdentification;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

public class DrawBoundingBox{

    private SurfaceHolder surfaceHolder;
    private RectF bounds;

    public DrawBoundingBox(SurfaceHolder surfaceHolder, RectF bounds){
        this.bounds = bounds;
        this.surfaceHolder = surfaceHolder;

    }

    public void DrawPersistentBox(){
        try{
            Canvas canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(5);
            canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }catch (Throwable e){
            Log.d("Test","error in drawing reference box");
            e.printStackTrace();
        }
    }

    public void DrawBBoxforStream() {
        if(!bounds.isEmpty()){
            try {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLUE);
                paint.setStrokeWidth(5);
                canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,paint);
                surfaceHolder.unlockCanvasAndPost(canvas);
                Log.d("Test","Stream Height: "+ bounds.height());
                Log.d("Test","Stream Width: "+ bounds.width());

            }catch (Throwable e){
                Log.d("Test","Error in drawingBBforStream");
            }
        }

    }

    // needs to be scaled to image size.
    public void drawBBoxforPic(){
        try {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(0,PorterDuff.Mode.CLEAR);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5);
            canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,paint);

            Log.d("Test","Pic Height: " + bounds.height());
            Log.d("Test","Pic Width: "+ bounds.width());

            surfaceHolder.unlockCanvasAndPost(canvas);

        }catch (Throwable e){
            Log.d("Test","Error in drawingBBforPic");
        }
    }

}