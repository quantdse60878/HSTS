package com.example.quyhkse61160.hstsapp.Classes;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;

import com.example.quyhkse61160.hstsapp.HomeActivity;

import java.util.Calendar;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    private static int id = 0;
    public static boolean flag = true;

    @Override
    public void onReceive(final Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Giờ ăn, uống, tập luyện đến rồi");
        wl.acquire();
        Bundle extras = intent.getExtras();
        if (extras != null && extras.getString("time") != null) {
            String time = extras.getString("time");
            HomeActivity.timeAlert = time;
            final int idOfAlarm = extras.getInt("id");
            Log.d("QUYYY111", "----------" + idOfAlarm + "------------");
            //Make dialog
            if(flag){
                flag = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Bạn đến giờ ăn, uống thuốc, tập luyện")
                        .setPositiveButton("Ngưng Nhắc Nhở", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                                PendingIntent sender = PendingIntent.getBroadcast(context, idOfAlarm, intent, 0);
                                alarmManager.cancel(sender);
                                Intent in = new Intent(context, HomeActivity.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(in);
                            }
                        }).setNegativeButton("Làm Sau", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }

    }
        wl.release();

    }

    public static void setAlarm(Context context, String time) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra("time", time);
        int id1 = AlarmManagerBroadcastReceiver.id++;
        intent.putExtra("id", id1);
        PendingIntent pi = PendingIntent.getBroadcast(context, id1, intent, 0);

        //After 5 seconds
        //am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 10, pi);

        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        int second = Integer.parseInt(time.split(":")[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Log.d("Khuong Dep Trai", calendar.getTime() + " - " + AlarmManagerBroadcastReceiver.id);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

//    public void cancelAlarm(Context context) {
//        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        for (int a = AlarmManagerBroadcastReceiver.id; a > 0; a--) {
//            PendingIntent sender = PendingIntent.getBroadcast(context, a, intent, 0);
//            alarmManager.cancel(sender);
//        }
//
//    }

}
