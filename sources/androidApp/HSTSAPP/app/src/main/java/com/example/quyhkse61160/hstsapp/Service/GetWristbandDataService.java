package com.example.quyhkse61160.hstsapp.Service;

import android.app.Service;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.HomeActivity;

public class GetWristbandDataService extends Service {

    Intent intent;
    public static final String BROADCAST_ACTION = "com.example.quyhkse61160.hstsapp.trackingevent";
    private final Handler handlerThreadSetup = new Handler();

    public GetWristbandDataService() {
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        handlerThreadSetup.removeCallbacks(getWristbandData);
        handlerThreadSetup.postDelayed(getWristbandData,01);
    }

    private Runnable getWristbandData = new Runnable() {
        @Override
        public void run() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("KhuongMHHHHHH", "Tao Dang Chạy GetWristbandData");
                    if(HomeActivity.hadHadCharacteristic) {
                        HomeActivity.readData();
                    }
                }
            }).start();
            handlerThreadSetup.postDelayed(this,30000);
        }
    };

    @Override
    public void onDestroy() {
        handlerThreadSetup.removeCallbacks(getWristbandData);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
