package com.example.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE=777;

    CheckBox checkBox;

    TextView tv1;
    TextView tv2;
    TextView tv3;

    String shared = "file";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.myAppName);
        setSupportActionBar(toolbar);

        Button btn=findViewById(R.id.btn_edit);
        tv1=findViewById(R.id.tv_start);
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        String value = sharedPreferences.getString("time","");
        tv1.setText(value);

        btn.setOnClickListener(new View.OnClickListener() { //버튼함수
            @Override
            public void onClick(View v) { //버튼이 눌렸을 때

                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });




        checkBox=findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"체크박스 on",Toast.LENGTH_LONG).show();
                    //checkBox.setText("체크박스 on");
                }
                else{
                    Toast.makeText(getApplicationContext(),"체크박스 off",Toast.LENGTH_LONG).show();
                    //checkBox.setText("체크박스 off");
                }
            }
        });






        //tv1=findViewById(R.id.tv_start);
        //tv3=findViewById(R.id.tv_end);
        //tv3.setText(StopTime);
        //tv2=findViewById(R.id.tv_wave);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = tv1.getText().toString();
        editor.putString("time",value) ;
        editor.commit(); //저장
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            int i=1;
            //값을 넘겨줌

            Toast.makeText(getApplicationContext(), "수신성공", Toast.LENGTH_SHORT).show();
        }


        else {

            int i=0;
            //값을 넘겨줌
            Toast.makeText(getApplicationContext(), "수신실패", Toast.LENGTH_SHORT).show();
        }

        if(requestCode==REQUEST_CODE){
            String a=data.getStringExtra("start");
            String b=data.getStringExtra("end");
            tv1.setText(a + " ~ " + b);
            //tv2.setText("~");
            //tv3.setText(b);



        }
    }

   

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {




        if (item.getItemId()==R.id.it_option){
            Toast.makeText(getApplicationContext(), "옵션이 클릭됨", Toast.LENGTH_SHORT).show();

            Intent option_intent = new Intent(getApplicationContext(), OptionActivity.class);
            startActivity(option_intent);
            return true;
        }

        else{
            return super.onOptionsItemSelected(item);
        }


        /*switch (item.getItemId())
        {


            case R.id.it_option :
                Toast.makeText(getApplicationContext(), "옵션이 클릭됨", Toast.LENGTH_SHORT).show();

                Intent option_intent = new Intent(getApplicationContext(), OptionActivity.class);
                startActivity(option_intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/


    }


}
