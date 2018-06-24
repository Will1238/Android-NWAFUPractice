package com.example.will.exp5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Will on 2018/6/24.
 */

public class TimerService extends Service {

    private Thread workThread;

    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this,"(1)调用onCreate()",Toast.LENGTH_LONG).show();
        workThread=new Thread(null,backgroundWork,"WorkThread");
    }

    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
        Toast.makeText(this, "(2)调用onStart()", Toast.LENGTH_SHORT).show();
        if(!workThread.isAlive()){
            workThread.start();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"(3)调用onDestroy()",Toast.LENGTH_LONG).show();
        workThread.interrupt();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Runnable backgroundWork=new Runnable() {
        @Override
        public void run() {
           long nowTime;
            try {
                while(!Thread.interrupted()){
                    nowTime=System.currentTimeMillis();
                    MainActivity.UpdateGUI(nowTime);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
