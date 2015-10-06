package com.example.quyhkse61160.hstsapp.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class BroadcastService extends Service {

    private static final String TAG = "BroadcastService Notify";
    public static final String BROADCAST_ACTION = "com.example.quyhkse61160.hstsapp.trackingevent";
    private final Handler handlerThread = new Handler();
    Intent intent;
    int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handlerThread.removeCallbacks(sendUpdateToUI);
        handlerThread.postDelayed(sendUpdateToUI, 1000);
    }

    private Runnable sendUpdateToUI = new Runnable() {
        @Override
        public void run() {
            checkNotify();
            handlerThread.postDelayed(this, 10000);
        }
    };

    private void checkNotify() {
        Log.d(TAG, "entered check notify");
        intent.putExtra("time", new Date().toLocaleString());
        intent.putExtra("counter", String.valueOf(++counter));




        sendBroadcast(intent);

    }



    public BroadcastService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onDestroy() {
        handlerThread.removeCallbacks(sendUpdateToUI);
        super.onDestroy();
    }
}
