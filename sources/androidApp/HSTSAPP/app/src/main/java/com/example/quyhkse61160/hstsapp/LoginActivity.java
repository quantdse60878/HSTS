package com.example.quyhkse61160.hstsapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.Service.NetworkChangeReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class LoginActivity extends ActionBarActivity {

    public static AssetManager am;
    public static boolean hadRegisterReceiver = false;
    protected final NetworkChangeReceiver mConnectionDetector = new NetworkChangeReceiver();
    protected final IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //KhuongMH
        Constant.DATA_FROM_SERVER = HSTSUtils.loadData(getAssets());
        //KhuongMH
        am = getAssets();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(Constant.PREF_USENAME_HADLOGIN, "").equals("")) {
            Intent myIntent = new Intent(LoginActivity.this, SelectDeviceActivity.class);
            LoginActivity.this.startActivity(myIntent);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));

        if(!hadRegisterReceiver) {
            hadRegisterReceiver = true;
            registerReceiver(mConnectionDetector, mIntentFilter);
        }


        setContentView(R.layout.activity_main);
        final EditText txtUsername = (EditText) findViewById(R.id.txt_login_username);
        final EditText txtPassword = (EditText) findViewById(R.id.txt_login_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsername.getText().length() > 0 && txtPassword.getText().length() > 0) {

                    LoginAsyncTask login = new LoginAsyncTask();
                    login.execute(txtUsername.getText().toString(), txtPassword.getText().toString());


                } else {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }

//                Comment o day
//                Intent intent = new Intent(LoginActivity.this, DeviceScanActivity.class);
//                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////                Intent intent = new Intent(LoginActivity.this, HomeActivity2.class);
//                startActivity(intent);
            }
        });


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateString = df.format(date).replaceAll("/", "");
        String FILENAME = "hakimquy" + ".txt";

        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/kimquy");
        dir.mkdirs();
        File file = new File(dir, FILENAME);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.println("----------------------");
            pw.flush();
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String stringURL = Constant.hostURL + Constant.loginMethod;
            Log.d("QUYYYY1111", "Login url: " + stringURL);
            Log.d("QUYYYY1111", "Login param: " + strings[0] + "-" + strings[1]);

            try {
                URL url = new URL(stringURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(100000);
                urlConnection.setConnectTimeout(30000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                Constant.username = strings[0];
                params.add(new BasicNameValuePair("username", strings[0]));
                params.add(new BasicNameValuePair("password", strings[1]));

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
                Log.d("QUYYY111", "Response: --" + response);
                try {
                    JSONObject patientObject = new JSONObject(response);
                    Constant.accountId = patientObject.getString("accountId");
                    Constant.PATIENT_NAME = patientObject.getString("fullname");
                    Constant.patientId = patientObject.getString("patientId");
                    Log.d("QUYYY111", "Benh nhan: " + "Account: " + Constant.accountId + " Patient: " + Constant.patientId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            if((Constant.accountId.equals("0") && Constant.PATIENT_NAME == null) || Constant.patientId.equals("0")){
                Toast.makeText(getApplicationContext(),R.string.login_error,Toast.LENGTH_LONG).show();
            } else {
                Intent continueIntent = new Intent(LoginActivity.this,DeviceScanActivity.class);
                startActivity(continueIntent);
            }
        }
    }







}
