package com.example.tool_identification.ToolIdentification;

import android.content.Context;
import android.graphics.RectF;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SizeF;
import android.util.TypedValue;

public class DetectSize {

    RectF detectedBounds, referenceBounds;
    Double final_size,fs1;
    CameraCharacteristics info;
    Context context;
    SizeF sensorSize;
    float[] focalLength;

    public DetectSize(Context context, RectF referenceBounds,RectF detectedBounds){

        this.detectedBounds = detectedBounds;
        this.referenceBounds = referenceBounds;
        this.context = context;
        compare();
    }


    public void compare(){

        CameraManager manager =
                (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        try{
            String[] cameraId = manager.getCameraIdList();
            info = manager.getCameraCharacteristics(cameraId[0]);
            sensorSize = info.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
            focalLength = info.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);

        }catch(CameraAccessException e){
            e.printStackTrace();
        }

        float R_WidthMm = (referenceBounds.width()/1080)*sensorSize.getWidth();


//        change distance in millimeters for correct calculations
//      distance = 304.8  // 1 foot
        double distance = 152.4; // 1/2 foot
        
        final_size= (distance/focalLength[0])* R_WidthMm;
        Log.d("Test","Final Size: "+ Math.round(final_size));

        fs1 = 0.0;
        if(final_size <=18){
            fs1 = 8.0;
        }else if(final_size >= 19 && final_size <=20){
            fs1 = 8.0;
        }else if(final_size >=21 && final_size <= 23){
            fs1 = 12.0;
        }else if(final_size >=24 && final_size <=29){
            fs1 = 14.0;
        }else if(final_size >= 30 && final_size <= 33){
            fs1 = 17.0;
        }else if(final_size >=34){
            fs1 = 19.0;
        }else{
            fs1 = 0.0;
        }

    }

    
    public void setReferenceBounds(RectF b){
        referenceBounds = b;
    }
    public void setDetectedBounds(RectF b){
        detectedBounds = b;
    }

    public void setInfo(CameraCharacteristics i){
        info = i;
    }

    public Double getObjectSize(){
        return fs1;
    }

    public void setContext(Context c){
        context = c;
    }
    
}


