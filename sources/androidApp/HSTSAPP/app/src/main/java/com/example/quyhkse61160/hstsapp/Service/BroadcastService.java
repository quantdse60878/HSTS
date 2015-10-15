package com.example.quyhkse61160.hstsapp.Service;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.HomeActivity;
import com.example.quyhkse61160.hstsapp.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
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
    public static int alertMinute = 0;

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
                    Calendar c1 = Calendar.getInstance();
                    c1.set(Calendar.HOUR_OF_DAY, 22);
                    c1.set(Calendar.MINUTE, 00);
//                    if(c.getTime().equals(c1.getTime())) {
                    if (true) {


                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String dateString = df.format(date).replaceAll("/", "");
                        String FILENAME = dateString + ".txt";

                        Log.e("QUY", "------------------2222222222222-------------");
                        File root = Environment.getExternalStorageDirectory();
                        File dir = new File(root.getAbsolutePath() + "/kimquy");
                        dir.mkdirs();
                        File file = new File(dir, FILENAME);
                        if (file.exists()) {
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                                StringBuffer stringBuffer = new StringBuffer();
                                String line = null;

                                while ((line = bufferedReader.readLine()) != null) {

                                    stringBuffer.append(line);
                                }

                                System.out.println(stringBuffer);
                                String fileData = new String(stringBuffer);

                                try {
                                    JSONObject jsonObject = new JSONObject(fileData);

                                    JSONArray listArray = jsonObject.getJSONArray("Value");
                                    byte[] listByteData = new byte[listArray.length()];
                                    for (int i = 0; i < listArray.length(); i++) {
                                        listByteData[i] = Byte.parseByte(listArray.get(i).toString());
                                    }
                                    String listData = new String(listByteData);
                                    String numberOfStep = listData.split(",")[3];
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                    sendMedicalData(numberOfStep, sdf.format(date));
                                    Log.d("QUY", "QUY");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                    }

                    Log.d("QUY", "------------------111111111111111-------------");


//                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                        Date date = new Date();
//                        String dateString = df.format(date).replaceAll("/", "");
//                        try {
//                            InputStream is = LoginActivity.am.open(dateString + ".txt");
//                            int size = is.available();
//                            byte[] buffer = new byte[size];
//                            is.read(buffer);
//                            String fileData = new String(buffer);
//
//                            Log.d("QUyyy11", "--" + fileData + "--");
//                            try {
//                                JSONObject jsonObject = new JSONObject(fileData);
//
//                                JSONArray listArray = jsonObject.getJSONArray("Value");
//                                byte[] listByteData = new byte[listArray.length()];
//                                for(int i = 0; i < listArray.length(); i++) {
//                                    listByteData[i] = Byte.parseByte(listArray.get(i).toString());
//                                }
//                                String listData = new String(listByteData);
//                                String numberOfStep = listData.split(",")[3];
//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                                sendMedicalData(numberOfStep, sdf.format(date));
//                                Log.d("QUY", "QUY");
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                    //Su dung private folder
//                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                        Date date = new Date();
//                        String dateString = df.format(date).replaceAll("/", "");
//                        try {
//                            FileInputStream is = openFileInput(dateString + ".txt");
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                            String stringData = reader.readLine();
//                            is.close();
//                            String numberOfStep = stringData.split(",")[3];
//
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                            sendMedicalData(numberOfStep, sdf.format(date));
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Su dung private folder
                }

                //Khuong ve nha code cho nay bo alarm thay bang kiem tra thoi gian trong list time. Neu trung thi hien nhu binh thuong
                for(
                String time
                :HomeActivity.amountTime)

                {
                    Calendar c2 = Calendar.getInstance();
                    c2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
                    c2.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
                    if (c2.getTime().getHours() == c.getTime().getHours()
                            && c2.getTime().getMinutes() + alertMinute == c.getTime().getMinutes()) {
                        final Context context = getApplicationContext();
                        if (BroadcastService.flag) {
                            BroadcastService.flag = false;
                            Intent in = new Intent(context, HomeActivity.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.putExtra("openDialogForMe", Boolean.TRUE);
                            if (c2.getTime().getHours() < 10)
                                HomeActivity.timeAlert = "0" + c2.getTime().getHours() + ":00";
                            else HomeActivity.timeAlert = c2.getTime().getHours() + ":00";
                            context.startActivity(in);
                        }

                    }
                }

            }
        }

        ).

        start();

        handlerThread.postDelayed(this,60000);

    }
};


private void sendMedicalData(String numberOfStep,String collectDate){

        String stringURL=Constant.hostURL+Constant.sendMedicalData;

        try{
        URL url=new URL(stringURL);
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setReadTimeout(100000);
        urlConnection.setConnectTimeout(30000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        List<NameValuePair>params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appointmentId",HomeActivity.appointmentId+""));
        params.add(new BasicNameValuePair("numberOfStep",numberOfStep));
        params.add(new BasicNameValuePair("collectDate",collectDate.toString()));

        OutputStream os=urlConnection.getOutputStream();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(HSTSUtils.getQuery(params));
        writer.flush();
        writer.close();
        os.close();

        urlConnection.connect();

        InputStream inStream=urlConnection.getInputStream();
        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
        String temp,response="";
        while((temp=bReader.readLine())!=null){
        response+=temp;
        }
        HomeActivity.listNumberOfStep=new ArrayList<>();
        HomeActivity.dateSaveStep=new ArrayList<>();


        }catch(IOException e){
        e.printStackTrace();
        }
        }

private void checkNotify(){
        Log.d(TAG,"entered check notify");
        intent.putExtra("time",new Date().toLocaleString());
        intent.putExtra("counter",String.valueOf(++counter));

        String stringURL=Constant.hostURL+Constant.checkNotifyMethod;
        Log.d("QUYYYY1111","Login url: "+stringURL);
        Calendar c=Calendar.getInstance();
        try{
        URL url=new URL(stringURL);
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setReadTimeout(100000);
        urlConnection.setConnectTimeout(30000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        List<NameValuePair>params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("receiverId",Constant.accountId));

        OutputStream os=urlConnection.getOutputStream();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(HSTSUtils.getQuery(params));
        writer.flush();
        writer.close();
        os.close();

        urlConnection.connect();

        InputStream inStream=urlConnection.getInputStream();
        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
        String temp,response="";
        while((temp=bReader.readLine())!=null){
        response+=temp;
        }
        try{
        JSONArray array=new JSONArray(response);
final Context context=getApplicationContext();
        for(int i=0;i<array.length();i++){
        JSONObject notifyItem=array.getJSONObject(i);
        int notifyType=Integer.parseInt(notifyItem.getString("type"));
        int notifyId=Integer.parseInt(notifyItem.getString("notifyId"));
        if(notifyType==2){
        getNewTreatment(notifyId);
        }else if(notifyType==5){
        //Mo ung dung hien thong bao la benh nhan hom truoc chua hoan thanh duoc bai tap cua minh, benh nhan can co gang hon de hoan thanh bai tap
        if(c.getTime().getHours()==7&&c.getTime().getMinutes()==0){
        Intent in=new Intent(context,HomeActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("notFinished",Boolean.TRUE);
        context.startActivity(in);
        hadGetNotify(notifyId);
        }
        }else if(notifyType==6){
        //Mo ung dung hien thong bao la benh nhan hom truoc da hoan thanh qua muc can thiet, benh nhan can giam cuong do luyen tap de bao ve suc khoe
        if(c.getTime().getHours()==7&&c.getTime().getMinutes()==0){
        Intent in=new Intent(context,HomeActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("overFinished",Boolean.TRUE);
        context.startActivity(in);
        hadGetNotify(notifyId);
        }
        }
        }


        }catch(JSONException e){
        e.printStackTrace();
        }


        }catch(MalformedURLException e){
        e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }


        sendBroadcast(intent);

        }

public BroadcastService(){
        }

@Override
public IBinder onBind(Intent intent){
        // TODO: Return the communication channel to the service.
        return null;
        }

@Override
public void onDestroy(){
        handlerThread.removeCallbacks(sendUpdateToUI);
        super.onDestroy();
        }
public void getNewTreatment(int notifyId){

        String stringURL=Constant.hostURL+Constant.getTreatment;
        try{
        URL url=new URL(stringURL);
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setReadTimeout(100000);
        urlConnection.setConnectTimeout(30000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        List<NameValuePair>params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("patientId",Constant.patientId));

        OutputStream os=urlConnection.getOutputStream();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(HSTSUtils.getQuery(params));
        writer.flush();
        writer.close();
        os.close();

        urlConnection.connect();

        InputStream inStream=urlConnection.getInputStream();
        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
        String temp,response="";
        while((temp=bReader.readLine())!=null){
        response+=temp;
        }
        try{
        JSONArray treatmentArray=new JSONArray(response);
        if(treatmentArray.length()>0){
        JSONObject treatmentObject=treatmentArray.getJSONObject(0);
        HomeActivity.appointmentId=Integer.parseInt(treatmentObject.getString("appointmentId"));
        }
        }catch(JSONException e){
        e.printStackTrace();
        }
        Constant.DATA_FROM_SERVER=response;
        Constant.TREATMENTS=Constant.getItems();

        HomeActivity.amountTime=HomeActivity.amountTime();
        hadGetNotify(notifyId);

        }catch(MalformedURLException e){
        e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }
        }

public void hadGetNotify(int notifyId){

        String stringURL=Constant.hostURL+Constant.hadGetTreatment;
        try{
        URL url=new URL(stringURL);
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        urlConnection.setReadTimeout(100000);
        urlConnection.setConnectTimeout(30000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        List<NameValuePair>params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("notifyId",notifyId+""));

        OutputStream os=urlConnection.getOutputStream();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(HSTSUtils.getQuery(params));
        writer.flush();
        writer.close();
        os.close();

        urlConnection.connect();

        InputStream inStream=urlConnection.getInputStream();
        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
        String temp,response="";
        while((temp=bReader.readLine())!=null){
        response+=temp;
        }
        }catch(MalformedURLException e){
        e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }

        }

        }
