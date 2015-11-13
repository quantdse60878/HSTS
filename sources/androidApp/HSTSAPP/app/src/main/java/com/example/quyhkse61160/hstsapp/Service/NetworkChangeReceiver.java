package com.example.quyhkse61160.hstsapp.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.quyhkse61160.hstsapp.Common.Constant;

public class NetworkChangeReceiver extends BroadcastReceiver {
    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (activeNetInfo != null) {
            boolean isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting();
            Intent notifyIntent = new Intent(context, BroadcastService.class);
            if (isConnected) {
                Log.i("QUYYYYYYY INTERNET", "connected " + isConnected);

                //A function with many code overhere
                Constant.haveInternet = true;
//                context.startService(notifyIntent);


            } else {
                Log.i("QUYYYYYY INTERNET", "not connected " + isConnected);
//                Constant.haveInternet = false;
//                context.stopService(notifyIntent);
            }
        }

    }
}
