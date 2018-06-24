package com.example.will.exp5;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Handler handler=new Handler();
    private static TextView textView=null;
    private static long recordTime;
    private static long nowTime;

    public static void UpdateGUI(long refreshTime){
        recordTime=refreshTime-nowTime;
        handler.post(RefreshTextView);
    }

    private static Runnable RefreshTextView=new Runnable() {
        @Override
        public void run() {
            String time="";
            long hour=0,min=0,sec=0;

            recordTime/=1000;   //毫秒转化为秒
            sec=recordTime%60;
            recordTime-=sec;
            recordTime/=60;
            min=recordTime%60;
            hour=recordTime/60;
            time+=((int)hour/10)+ "" + hour%10 +":"+ ((int)min/10) + min%10 +":"+ ((int)sec/10) + sec%10;

            textView.setText(time);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.AppNumber);
        final Intent serviceIntent=new Intent(this, TimerService.class);

        Button btn = (Button)findViewById(R.id.btnClear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("00:00:00");
            }
        });

        btn =(Button)findViewById(R.id.btnStart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowTime=System.currentTimeMillis();
                startService(serviceIntent);
            }
        });

        btn =(Button)findViewById(R.id.btnStop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });
    }
}
