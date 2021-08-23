package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Button;

public class SwitchTest extends Activity{
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch sw = (Switch)findViewById(R.id.VibSwitch);
        Button b1 = (Button)findViewById(R.id.VibOn);
        Button b2 = (Button)findViewById(R.id.VibOff);
        final Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        //스위치의 체크 이벤트를 위한 리스너 등록

        sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
                if(isChecked){
                    b1.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            vibrator.vibrate(new long[]{1000,3000},0);
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            vibrator.cancel();
                        }

                    });

                }
                else{
                    vibrator.cancel();
                }


            }

        });

    }
}
