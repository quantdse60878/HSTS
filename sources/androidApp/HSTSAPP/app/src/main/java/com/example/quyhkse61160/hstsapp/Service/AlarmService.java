package com.example.quyhkse61160.hstsapp.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.quyhkse61160.hstsapp.Classes.AlarmManagerBroadcastReceiver;
import com.example.quyhkse61160.hstsapp.HomeActivity;

public class AlarmService extends Service {

    private final Handler handlerThread = new Handler();
    public static final String BROADCAST_ACTION = "com.example.quyhkse61160.hstsapp.alarm";
    Intent intent;
    public AlarmService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handlerThread.removeCallbacks(setAlarm);

        //Sau 10s khi ch?y service.
        handlerThread.postDelayed(setAlarm, 20000);
    }


    private Runnable setAlarm = new Runnable() {
        @Override
        public void run() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    setUpAlarm();
                }
            }).start();

            //Ch?y l?i sau 24h
            handlerThread.postDelayed(this, 1000*60*60*24);
        }
    };

    private void setUpAlarm() {
        Context context = getApplicationContext();
        Log.d("QUYYY111", "00000000000000000000000");
        AlarmManagerBroadcastReceiver alarmManagerBroadcastReceiver = new AlarmManagerBroadcastReceiver();
        for(String item : HomeActivity.amountTime) {
            alarmManagerBroadcastReceiver.setAlarm(context, item);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
