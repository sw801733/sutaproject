package com.example.ringspinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] values;
    TextView textView;
    MediaPlayer mediaPlayer;
    Button startButton;
    Button stopButton;
    String[] items = {"LG모닝콜", "emart", "알람벨"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView2);
        startButton = findViewById(R.id.PlaySong);
        stopButton = findViewById(R.id.StopSong);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(items[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("노래 선택: ");
            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView song = (TextView) findViewById(R.id.textView2);
                String s1 = song.getText().toString();

                int[] playlist = new int[3];
                playlist[0]=R.raw.goodmorning;
                playlist[1]=R.raw.emart;
                playlist[2]=R.raw.alarmbell;

                if (s1.equals("LG모닝콜")) {
                    if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.goodmorning);

                } else if (s1.equals("emart")) {
                    if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.emart);

                } else if (s1.equals("알람벨")) {
                    if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alarmbell);

                }
                mediaPlayer.start();
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    });
}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
