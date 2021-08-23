package com.example.qwqwqw;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.qwqwqw.AlarmReceiver;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button alarmB;
    int alarmHour, alarmMinute;
    Calendar alarmCalendar;
    @Override


    protected void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        alarmB=(Button)findViewById(R.id.AlarmButton);
        alarmB.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar,new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        alarmHour=hourOfDay;
                        alarmMinute=minute;
                        setAlarm();
                    }
                },alarmHour,alarmMinute,false);
                timePickerDialog.setTitle("알람시간은?");
                timePickerDialog.show();
            }
        });
    }



    void setAlarm() {
        alarmCalendar = Calendar.getInstance();
        alarmCalendar.setTimeInMillis(System.currentTimeMillis());
        alarmCalendar.set(Calendar.HOUR_OF_DAY, alarmHour);
        alarmCalendar.set(Calendar.MINUTE, alarmMinute);
        alarmCalendar.set(Calendar.SECOND, 0);

        if (alarmCalendar.before(Calendar.getInstance())) alarmCalendar.add(Calendar.DATE, 1);
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmIntent.setAction(AlarmReceiver.ACTION_RESTART_SERVICE);
        PendingIntent alarmCallPendingIntent
                = PendingIntent.getBroadcast
                (MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle
                    (alarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), alarmCallPendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            alarmManager.setExact
                    (AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), alarmCallPendingIntent);
    }
}







