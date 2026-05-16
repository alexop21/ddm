package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;

public class TinderButton extends Button {
    float x0,y0;
    int colorR,colorG,colorB;
    public TinderButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        this.setText("Ola Mundão");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawY();
        float y = event.getRawY();
        Log.d("coordenadas","x:"+ Float.toString(x)+"y:"+Float.toString(y));

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x0=x;
                y0=y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx= (int)(x-x0);
                colorR= Math.min(255,Math.max(0,120-dx/5));
                colorG= Math.min(255,Math.max(0,120-dx/5));
                colorB= 120;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        this.setBackgroundColor(Color.rgb(colorR,colorG,colorB));

        return super.onTouchEvent(event);
    }
}
