package com.example.lock;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LockScreenActivity extends Activity {
    TextView Time;
    TextView Date;
    UsedAsync asyncTask;
    ProgressHandler handler;

    SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
    String time;

    SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        Time = (TextView) findViewById(R.id.time_view);
        Date = (TextView) findViewById(R.id.day_view);

        handler = new ProgressHandler();

        runTime();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public void runTime(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try{
                        time = stf.format(new Date(System.currentTimeMillis()));
                        date = sdf.format(new Date(System.currentTimeMillis()));

                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    }catch (InterruptedException ex) {}
                }
            }
        });
        thread.start();

        asyncTask = new UsedAsync();
        asyncTask.execute();
    }

    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Time.setText(time);
            Date.setText(date);
        }
    }

    class UsedAsync extends AsyncTask<Integer, Integer, Integer> {
        Calendar cal;
        String timeGre;
        String dayGre;

        @Override
        protected Integer doInBackground(Integer... params) {
            while (isCancelled() == false){
                cal = new GregorianCalendar();
                timeGre = String.format("%d:%d", cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE));
                dayGre = String.format("%d월 %d일", cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                publishProgress();
                try{
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {}
            }
            return null;
        }
    }
    public void onClickCall(View v){
        Intent goCall = new Intent(Intent.ACTION_DIAL);
        startActivity(goCall);
    }

    public void onClickMessage(View v){
        Uri uri = Uri.parse("smsto:");
        Intent goMessage = new Intent(Intent.ACTION_SENDTO, uri);
        goMessage.putExtra("sms_body", "");
        startActivity(goMessage);
    }
}