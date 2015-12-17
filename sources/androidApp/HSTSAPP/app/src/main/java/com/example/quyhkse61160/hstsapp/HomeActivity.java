package com.example.quyhkse61160.hstsapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Adapter.NavDrawerListAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.ViewPagesAdapter;
import com.example.quyhkse61160.hstsapp.Classes.AlarmManagerBroadcastReceiver;
import com.example.quyhkse61160.hstsapp.Classes.NavDrawerItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.Fragment.NoticeNextTab;
import com.example.quyhkse61160.hstsapp.Fragment.NoticeTab;
import com.example.quyhkse61160.hstsapp.Fragment.Tab4;
import com.example.quyhkse61160.hstsapp.Service.BluetoothLeService;
import com.example.quyhkse61160.hstsapp.Service.BroadcastService;
import com.example.quyhkse61160.hstsapp.Service.GetWristbandDataService;
import com.example.quyhkse61160.hstsapp.Service.NetworkChangeReceiver;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener {

    private final static String TAG = HomeActivity.class.getSimpleName();
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    public static BluetoothLeService mBluetoothLeService;
    private String mDeviceName;
    private String mDeviceAddress;
    public static int appointmentId = 2;
    public static List<String> listNumberOfStep = new ArrayList<>();
    public static List<String> dateSaveStep = new ArrayList<>();
    public static BluetoothGattCharacteristic characteristicStep = null;
    public static BluetoothGattCharacteristic characteristicManufacturer = null;
    private Timer timer = new Timer();
    protected final IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    protected final NetworkChangeReceiver mConnectionDetector = new NetworkChangeReceiver();
    private Intent checkNotifyIntent;
    public static List<String> amountTime = new ArrayList<>();
    public static boolean hadStartAlarmService = false;
    ActionBar actionBar;
    ViewPagesAdapter adapter;
    ViewPager viewPager;
    public static String timeAlert;
    private AlarmManagerBroadcastReceiver alarm;
    public static boolean hadRegisterReceiver = false;
    public static boolean hadHadCharacteristic = false;
    private final Handler handlerThread = new Handler();

    public static boolean stopGetDataFromWristband = false;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean flag = false;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter dadapter;
    public static boolean hasNotify = false;
    public static boolean hasRunBroadcastService = false;

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        AlarmManagerBroadcastReceiver.flag = true;
    }

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                //Maybe being wrong
                mBluetoothLeService.connect(mDeviceAddress);
            }
        }
    };

    private BroadcastReceiver notifyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkNotify(intent);
        }
    };


    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        List<BluetoothGattService> listService = mBluetoothLeService.getSupportedGattServices();
        for (int i = 0; i < listService.size(); i++) {
            if (listService.get(i).getUuid().toString().equals(Constant.DEVICE_INFORMATION)) {
                BluetoothGattService gattService = listService.get(i);
                if (gattService == null) {
                    Log.d("-------", "NULL CMNR");
                } else {
                    Log.d("-------", "--" + gattService.getUuid().toString() + "--");
                    characteristicStep = gattService.getCharacteristic(Constant.numberOfStep_UUID);
                    characteristicManufacturer = gattService.getCharacteristic(Constant.manufacturer_UUID);
                    hadHadCharacteristic = true;
                    mBluetoothLeService.readCharacteristic(characteristicManufacturer);
                }
            }
        }

//        txtNumberOfStep.setText(numberOfStep);

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // Your logic here...
//
//                // When you need to modify a UI element, do so on the UI thread.
//                // 'getActivity()' is required as this is being ran from a Fragment.
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // This code will always run on the UI thread, therefore is safe to modify UI elements.
//                        readData();
//                    }
//                });
//            }
//        }, 10000, 10000);


    }

    public static void readData(){
        if (Constant.numberOfStep_potition != -1) {
            mBluetoothLeService.readCharacteristic(characteristicStep);
            Log.d("QUYYY1111111", "Manufacturer: " + Constant.manufacturer + "------" + "Number of step: " + Constant.numberOfStep);
        }
    }

    private void updateView(int index){
        int temp = index - mDrawerList.getFirstVisiblePosition();
        View v = mDrawerList.getChildAt(temp);

        if(v == null){
            return;
        }
        ImageView danger = (ImageView) v.findViewById(R.id.danger);
        danger.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Log.e("QUYYY11", "00HomeActivity - RegisterReceiver: " + hadRegisterReceiver);
        if(!hadRegisterReceiver) {
            hadRegisterReceiver = true;
            registerReceiver(mConnectionDetector, mIntentFilter);
        }
        SendBrandAsyncTask sendBrandAsyncTask = new SendBrandAsyncTask();
//        sendBrandAsyncTask.execute();
        Constant.TREATMENTS = HSTSUtils.getItems();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Context context = getApplicationContext();
            if (bundle.getBoolean("openDialogForMe")) {
                hasNotify = true;
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Bạn đến giờ ăn, uống thuốc, tập luyện")
                        .setPositiveButton("Ngưng Nhắc Nhở", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BroadcastService.alertMinute = 0;
                                Constant.ALARM_TIME_COUNT = 0;
                            }
                        })
                        .setNegativeButton("Làm Sau", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Constant.ALARM_TIME_COUNT++;
                                BroadcastService.alertMinute = 0;
                                for(int i=0; i< Constant.ALARM_TIME_COUNT;i++){
                                    BroadcastService.alertMinute += Constant.ALARM_TIME;
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
            if (bundle.getBoolean("nextAppointment")) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Hôm nay là ngày tái khám. Hãy cố gắng thu xếp thời gian để đến với chúng tôi. Hân hạnh đươc đón tiếp Quý Khách!");
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
                Constant.DATA_FROM_SERVER = HSTSUtils.loadData(getAssets());
                Constant.TREATMENTS = HSTSUtils.getItems();
            }if (bundle.getBoolean("notFinished")) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Hôm qua bạn chưa hoàn thành chế độ điều trị, hãy cố gắng thực hiện để việc điều trị được tốt hơn.");
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
            if (bundle.getBoolean("noConnection")) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Nhắc Nhở").setMessage("HSTS App không tìm thấy kết nối mạng để đồng bộ dữ liệu. Bạn có muốn mở mạng không ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
            if (bundle.getBoolean("overFinished")) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Hôm qua bạn hoàn thành vượt mức chế độ điều trị, hãy cố gắng điều độ để việc điều trị được tốt hơn.");
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
        }

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
//        navMenuIcons = getResources()
//                .obtainTypedArray(R.array.nav_drawer_items);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));

        // Notice
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

//        // Notice Next Time
//        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        dadapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(dadapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    updateView(position);
                    displayView(0);
                }
                if (position == 1) {
                    updateView(position);
                    displayView(1);
                }
//                if (position == 2) {
//                    updateView(position);
//                    displayView(2);
//                }
            }
        });

        // enabling action bar app icon and behaving it as toggle button
        actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                actionBar.setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                actionBar.setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            if(hasNotify) displayView(1);
            else displayView(0);
        }

        if(!hasRunBroadcastService){
            hasRunBroadcastService = true;
            checkNotifyIntent = new Intent(this, BroadcastService.class);
            startService(checkNotifyIntent);
        }
        //KhuongMH


        //Set Alarm
        amountTime = amountTime();


        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }

        Context context = getApplicationContext();
        Intent notifyIntent = new Intent(context, GetWristbandDataService.class);
        context.startService(notifyIntent);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        actionBar.setTitle(mTitle);
    }

    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Tab4();
                break;
            case 1:
                fragment = new NoticeTab();
                break;
//            case 2:
//                fragment = new NoticeNextTab();
//                break;
//            case 3:
//                fragment = new Tab3();
//                break;
//            case 4:
//                fragment = new NoticeTab();
//                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            flag = false;
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            // error in creating fragment
            Log.d("MainActivity", "Error in creating fragment");
        }
    }

    public static List<String> amountTime() {
        List<Treatment> treatments = Constant.TREATMENTS;
        List<String> alarmTime = new ArrayList<>();
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListFoodTreatment()) {
                for (String t : time.getNumberOfTime()) {
                    if (!alarmTime.contains(t)) alarmTime.add(t);
                }
            }
            for (ToDoTime time : treatment.getListMedicineTreatment()) {
                for (String t : time.getNumberOfTime()) {
                    if (!alarmTime.contains(t)) alarmTime.add(t);
                }
            }
            for (ToDoTime time : treatment.getListPracticeTreatment()) {
                for (String t : time.getNumberOfTime()) {
                    if (!alarmTime.contains(t)) alarmTime.add(t);
                }
            }
        }
        return alarmTime;
    }

    //KhuongMH
    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        if(!stopGetDataFromWristband) {
            menu.findItem(R.id.action_stop).setTitle("STOP");
        } else {
            menu.findItem(R.id.action_stop).setTitle("START");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:{
                if(!flag){
                    mDrawerLayout.openDrawer(mDrawerList);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    flag = true;
                }
                else {
                    mDrawerLayout.closeDrawer(mDrawerList);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    flag = false;
                }
                break;
            }
            case R.id.action_change_password:{
                Intent intentChangePassword = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentChangePassword);
                break;
            }
            case R.id.action_logout:{
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
                if(!sharedPreferences.getString(Constant.PREF_HADSELECTDEVICE, "").equals("")){
                    sharedPreferences.edit().remove(Constant.PREF_HADSELECTDEVICE).commit();
                }
                if(!sharedPreferences.getString(Constant.PREF_ACCOUNTID_HADLOGIN, "").equals("")){
                    sharedPreferences.edit().remove(Constant.PREF_ACCOUNTID_HADLOGIN).commit();
                }
                Intent logout = new Intent(getApplication(),LoginActivity.class);
                startActivity(logout);
                break;
            }
            case R.id.action_config_alarm:{
                Intent intentConfigAlarm = new Intent(this, AlarmSettingActivity.class);
                startActivity(intentConfigAlarm);
                break;
            }
            case R.id.action_stop:{
                if(!stopGetDataFromWristband) {
                    stopGetDataFromWristband = true;
                    item.setTitle("START");
                } else {
                    stopGetDataFromWristband = false;
                    item.setChecked(true);
                    item.setTitle("STOP");
                }
            }



        }


        return super.onOptionsItemSelected(item);
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    private void checkNotify(Intent intent) {
        String counter = intent.getStringExtra("counter");
        String time = intent.getStringExtra("time");
        Log.d("QUYYYY1111", "Check Notify: " + counter + "--" + time);
    }


    private class SendBrandAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            while (Constant.manufacturer.equals("")) {
                try {
                    Log.e("Quy", "FUCK");
                    Thread.sleep(1000);
                    Log.e("Quy", "You");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sendManufacturer();
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }


    private void sendManufacturer() {
        String stringURL = Constant.hostURL + Constant.sendUuidToAndroid;
        Log.d("---Brand Name---", Constant.manufacturer + "_________" + stringURL);

        try {
            URL url = new URL(stringURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(100000);
            urlConnection.setConnectTimeout(30000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("brandName", Constant.manufacturer));

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
            Log.e("Quy11111", "--" + response);
            try {
                JSONArray listUuid = new JSONArray(response);
                String result = listUuid.getString(0);
                String[] stringResponse = result.split(",");
                Constant.numberOfStep_UUID = UUID.fromString(stringResponse[0]);
                Constant.numberOfStep_potition = Integer.parseInt(stringResponse[1]);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
