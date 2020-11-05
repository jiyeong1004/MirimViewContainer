package com.example.mirimviewcontainer;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    ViewFlipper flipper;
    float downX, upX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);
        flipper = findViewById(R.id.flipper);
        Button btnPrev = findViewById(R.id.btn_prev);
        Button btnNext = findViewById(R.id.btn_next);
        btnPrev.setOnClickListener(btnListener);
        btnNext.setOnClickListener(btnListener);
        flipper.setOnTouchListener(touchListener);
    }

    //★★★ XML ViewFlipper 요소에 clickable = true로 반드시 설정한다.
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent e) {
            if(e.getAction() == MotionEvent.ACTION_DOWN){  //손가락으로 눌렀을 때
                downX = e.getX();
            }else if(e.getAction() == MotionEvent.ACTION_UP){  //손가락을 때었을 때
                upX = e.getX();
                if(downX > upX){
                    flipper.showNext();
                }else if(downX < upX){
                    flipper.showPrevious();
                }
            }
            return false;
        }
    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_prev:
                    flipper.setFlipInterval(1000);
                    flipper.startFlipping();
                    break;
                case R.id.btn_next:
                    flipper.stopFlipping();
                    break;
            }
        }
    };
}