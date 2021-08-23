package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SubActivity extends AppCompatActivity {

    Button btn;
    TimePicker starttime;
    TimePicker endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn=findViewById(R.id.save_btn);
        starttime=findViewById(R.id.start);
        endtime=findViewById(R.id.end);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Start_hour=starttime.getHour();
                int Start_minute=starttime.getMinute();

                int End_hour=endtime.getHour();
                int End_minute=endtime.getMinute();

                String a = Start_hour+":"+Start_minute;
                String b= End_hour+":"+End_minute;

                Intent intent = new Intent();
                intent.putExtra("start",a); //입력값 담아주기
                intent.putExtra("end",b); //입력값 담아주기
                //intent.putExtra("endh",endtime.getHour()); //입력값 담아주기
                //intent.putExtra("endm",endtime.getMinute()); //입력값 담아주기
                setResult(RESULT_OK,intent); // 결과값 설정정

                finish(); //현재액티비티 종료

            }
        });


    }




}
