package com.example.quyhkse61160.hstsapp.Service;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class BroadcastService extends Service {

    private static final String TAG = "BroadcastService Notify";
    public static final String BROADCAST_ACTION = "com.example.quyhkse61160.hstsapp.trackingevent";
    public static boolean flag = true;
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
        handlerThread.postDelayed(sendUpdateToUI, 100);

    }

    private Runnable sendUpdateToUI = new Runnable() {
        @Override
        public void run() {

            new Thread(new Runnable() {
                @Override
                public void run() {

//                    checkNotify();
                    Calendar c = Calendar.getInstance();
                    long cl = c.getTimeInMillis();
                    Calendar c1 = Calendar.getInstance();
                    c1.set(Calendar.HOUR_OF_DAY, 14);
                    c1.set(Calendar.MINUTE, 47);
                    Log.d("QUYYY111", "-----" + c.getTime() + "--" + c1.getTime());
                    Log.d("QUYYY111", "-----------------------------------------------------------");
                    if(c.getTime().equals(c1.getTime())) {
                        HomeActivity.listNumberOfStep.add(HomeActivity.numberOfStep);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        HomeActivity.dateSaveStep.add(sdf.format(new Date()));
                        for (int i = 0; i < HomeActivity.listNumberOfStep.size(); i++) {
                            sendMedicalData(HomeActivity.listNumberOfStep.get(i), HomeActivity.dateSaveStep.get(i));
                        }
                    }

                    //Khuong ve nha code cho nay bo alarm thay bang kiem tra thoi gian trong list time. Neu trung thi hien nhu binh thuong
                    for(String time : HomeActivity.amountTime){
                        Calendar c2 = Calendar.getInstance();
                        c2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
                        c2.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));

                        Log.d("Hihi",c2.getTimeInMillis() + " " + c.getTimeInMillis());
                        if (c2.getTime().getHours() == c.getTime().getHours() && c2.getTime().getMinutes() == c.getTime().getMinutes()) {
                            Log.d("KhuongMH","HHHHHHHHHHHHHHHH");
                            final Context context = getApplicationContext();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                            builder.setTitle("Nhắc Nhở").setMessage("Bạn đến giờ ăn, uống thuốc, tập luyện")
//                                    .setPositiveButton("Ngưng Nhắc Nhở", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            Intent in = new Intent(context, HomeActivity.class);
//                                            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                            context.startActivity(in);
//                                        }
//                                    }).setNegativeButton("Làm Sau", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                }
//                            });
//                            AlertDialog dialog = builder.create();
//                            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//                            dialog.show();
                            if(BroadcastService.flag){
                                BroadcastService.flag = false;
                                Intent in = new Intent(context, HomeActivity.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.putExtra("openDialogForMe", Boolean.TRUE);
                                context.startActivity(in);
                            }

                        }
                    }

                }
            }).start();

            handlerThread.postDelayed(this, 60000);

        }
    };


    private void sendMedicalData(String numberOfStep, String collectDate) {

        String stringURL = Constant.hostURL + Constant.sendMedicalData;
        Log.d("QUYYYY1111", "Login url: " + stringURL + "------" + "App: " + HomeActivity.appointmentId + "--Step: " + numberOfStep + "--Date: " + collectDate);

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

            HomeActivity.listNumberOfStep = new ArrayList<>();
            HomeActivity.dateSaveStep = new ArrayList<>();


        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Khuonggggggg","Cannot connectttttttttttt");
        }
    }

    private void checkNotify() {
        Log.d(TAG, "entered check notify");
        intent.putExtra("time", new Date().toLocaleString());
        intent.putExtra("counter", String.valueOf(++counter));

        String stringURL = Constant.hostURL + Constant.checkNotifyMethod;
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
            params.add(new BasicNameValuePair("receiverId", Constant.accountId));

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

            try {
                JSONArray array = new JSONArray(response);
                for(int i = 0; i < array.length(); i++) {
                    JSONObject notifyItem = array.getJSONObject(i);
                    int notifyType = Integer.parseInt(notifyItem.getString("type"));
                    int notifyId = Integer.parseInt(notifyItem.getString("notifyId"));
                    if(notifyType == 2) {
                        getNewTreatment(notifyId);
                    } else if (notifyType == 5) {
                        //Mo ung dung hien thong bao la benh nhan hom truoc chua hoan thanh duoc bai tap cua minh
                    } else if (notifyType == 6) {
                        //Mo ung dung hien thong bao la benh nhan hom truoc da hoan thanh qua muc can thiet va can giam cuong do luyen tap de bao ve suc khoe
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    public void getNewTreatment(int notifyId) {

        String stringURL = Constant.hostURL + Constant.getTreatment;
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
            params.add(new BasicNameValuePair("patientId", Constant.patientId));

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
//            Constant.DATA_FROM_SERVER = response;
//            Constant.TREATMENTS = Constant.getItems();

            HomeActivity.amountTime = HomeActivity.amountTime();
            hadGetTreatment(notifyId);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hadGetTreatment(int notifyId) {

        String stringURL = Constant.hostURL + Constant.hadGetTreatment;
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
            params.add(new BasicNameValuePair("notifyId", notifyId + ""));

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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
