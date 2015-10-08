package com.example.quyhkse61160.hstsapp.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.HomeActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class SynchronizeDataReceiver extends BroadcastReceiver {
    public SynchronizeDataReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Day di thoi");
        wl.acquire();


        HomeActivity.listNumberOfStep.add(HomeActivity.numberOfStep);
        HomeActivity.dateSaveStep.add(new Date());

        if(Constant.haveInternet) {

            for(int i = 0; i < HomeActivity.listNumberOfStep.size(); i++) {
                sendMedicalData(HomeActivity.listNumberOfStep.get(i), HomeActivity.dateSaveStep.get(i));
            }

            HomeActivity.listNumberOfStep = new ArrayList<>();
            HomeActivity.dateSaveStep = new ArrayList<>();

        }

        wl.release();



    }

    private void sendMedicalData(String numberOfStep, Date collectDate) {

        String stringURL = Constant.hostURL + Constant.sendMedicalData;
        Log.d("QUYYYY1111", "Login url: " + stringURL);

        try {
            URL url = new URL(stringURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(100000);
            urlConnection.setConnectTimeout(30000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appointmentId", HomeActivity.appointmentId + ""));
            params.add(new BasicNameValuePair("numberOfStep", numberOfStep));
            params.add(new BasicNameValuePair("collectDate", collectDate.toString()));

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(HSTSUtils.getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            urlConnection.connect();

            InputStream inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            Log.d("QUYYY111", "--" + response);

            PrintWriter printWriter = new PrintWriter("treatment.json");
            printWriter.write(response);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SynchronizeDataReceiver.class);
        intent.putExtra("----0----", Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        PendingIntent pi1 = PendingIntent.getBroadcast(context, 1, intent, 0);
        //After 5 seconds

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 00);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi1);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24, pi);
    }
}
