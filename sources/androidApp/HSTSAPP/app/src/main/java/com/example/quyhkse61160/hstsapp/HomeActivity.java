package com.example.quyhkse61160.hstsapp;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Adapter.ViewPagesAdapter;
import com.example.quyhkse61160.hstsapp.Classes.AlarmManagerBroadcastReceiver;
import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.Fragment.Tab1;
import com.example.quyhkse61160.hstsapp.Service.AlarmService;
import com.example.quyhkse61160.hstsapp.Service.BluetoothLeService;
import com.example.quyhkse61160.hstsapp.Service.BroadcastService;
import com.example.quyhkse61160.hstsapp.Service.NetworkChangeReceiver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener {

    private final static String TAG = HomeActivity.class.getSimpleName();
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    private BluetoothLeService mBluetoothLeService;
    private String mDeviceName;
    private String mDeviceAddress;
    public static int appointmentId = 2;
    public static List<String> listNumberOfStep = new ArrayList<>();
    public static List<String> dateSaveStep = new ArrayList<>();
    public static String numberOfStep = "2000";
    public static int position = 0;
    public static String manufacturer = "Unknown";
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
                    Log.d("-------", "GGWP");
                    Log.d("-------", "--" + gattService.getUuid().toString() + "--");
                    characteristicStep = gattService.getCharacteristic(Constant.numberOfStep_UUID);
                    characteristicManufacturer = gattService.getCharacteristic(Constant.manufacturer_UUID);
//                    mBluetoothLeService.readCharacteristic(characteristicStep);
                    mBluetoothLeService.readCharacteristic(characteristicManufacturer);
                }
            }
        }

//        txtNumberOfStep.setText(numberOfStep);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Your logic here...

                // When you need to modify a UI element, do so on the UI thread.
                // 'getActivity()' is required as this is being ran from a Fragment.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // This code will always run on the UI thread, therefore is safe to modify UI elements.
                        mBluetoothLeService.readCharacteristic(characteristicStep);
//                        mBluetoothLeService.readCharacteristic(characteristicManufacturer);

                        Log.d("QUYYY1111111", "Manufacturer: " + manufacturer + "------" + "Number of step: " + numberOfStep);
                    }
                });
            }
        }, 10000, 10000);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);
        checkNotifyIntent = new Intent(this, BroadcastService.class);
        //KhuongMH
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Context context = getApplicationContext();
            if (bundle.getBoolean("openDialogForMe")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Bạn đến giờ ăn, uống thuốc, tập luyện")
                        .setPositiveButton("Ngưng Nhắc Nhở", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BroadcastService.alertMinute = 0;
                                BroadcastService.flag = true;
                            }
                        }).setNegativeButton("Làm Sau", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BroadcastService.alertMinute += 5;
                        BroadcastService.flag = true;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
            if(bundle.getBoolean("notFinished")){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Hôm qua bạn chưa hoàn thành chế độ điều trị, hãy cố gắng thực hiện để việc điều trị được tốt hơn.");
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
            if(bundle.getBoolean("overFinished")){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Hôm qua bạn hoàn thành vượt mức chế độ điều trị, hãy cố gắng điều độ để việc điều trị được tốt hơn.");
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
        }


        actionBar = getSupportActionBar();
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagesAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));

        ActionBar.Tab atab1 = actionBar.newTab().setText("THỨC ĂN").setTabListener(this);
        ActionBar.Tab atab2 = actionBar.newTab().setText("THUỐC").setTabListener(this);
        ActionBar.Tab atab3 = actionBar.newTab().setText("LUYỆN TẬP").setTabListener(this);
        ActionBar.Tab atab4 = actionBar.newTab().setText("THÔNG BÁO").setTabListener(this);
        actionBar.addTab(atab1);
        actionBar.addTab(atab2);
        actionBar.addTab(atab3);
        actionBar.addTab(atab4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        startService(checkNotifyIntent);
//        registerReceiver(notifyReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
//        position = Integer.parseInt(Constant.NUMBEROFSTEP_POSITION);
//        final Intent intent = getIntent();
//        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
//        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);
//
//        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
//        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
//        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
//        if (mBluetoothLeService != null) {
//            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
//            Log.d(TAG, "Connect request result=" + result);
//        }

        registerReceiver(mConnectionDetector, mIntentFilter);


        Constant.TREATMENTS = Constant.getItems();

        //Set Alarm
        amountTime = amountTime();
        if (!hadStartAlarmService) {
//            Intent alarmIntent = new Intent(this, AlarmService.class);
//            startService(alarmIntent);
//            hadStartAlarmService = true;
        }
//            for (ToDoTime item2 : Constant.Foods) {
//                if (item2.getTimeUse().equals(item)) {
//                    message += "Thức ăn : \n";
//                    for (ToDoItem item3 : item2.getItems()) {
//                        message += item3.getName() + " - " + item3.getQuantity() + "\n";
//
//                    }
//                }
//            }
//            for (ToDoTime item2 : Constant.Medicines) {
//                if (item2.getTimeUse().equals(item)) {
//                    message += "Thuốc : \n";
//                    for (ToDoItem item3 : item2.getItems()) {
//                        message += item3.getName() + " - " + item3.getQuantity() + "\n";
//                    }
//                }
//            }
//            for (ToDoTime item2 : Constant.Practice) {
//                if (item2.getTimeUse().equals(item)) {
//                    message += "Bài tập : \n";
//                    for (ToDoItem item3 : item2.getItems()) {
//                        message += item3.getName() + " - " + item3.getQuantity() + "\n";
//                    }
//                }
//            }
//            alarm.setAlarm(context, item, message);
//        }

//        startService(checkNotifyIntent);
//        position = Integer.parseInt(Constant.NUMBEROFSTEP_POSITION);
//        final Intent intent = getIntent();
//        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
//        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);
//
//        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
//        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
//        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
//        if (mBluetoothLeService != null) {
//            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
//            Log.d(TAG, "Connect request result=" + result);
//        }


    }

    public static List<String> amountTime() {
        List<Treatment> treatments = Constant.TREATMENTS;
        List<String> alarmTime = new ArrayList<>();
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListFoodTreatment()) {
                for(String t : time.getNumberOfTime()){
                    if (!alarmTime.contains(t)) alarmTime.add(t);
                }
            }
            for (ToDoTime time : treatment.getListMedicineTreatment()) {
                for(String t : time.getNumberOfTime()){
                    if (!alarmTime.contains(t)) alarmTime.add(t);
                }
            }
            for (ToDoTime time : treatment.getListPracticeTreatment()) {
                for(String t : time.getNumberOfTime()){
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
        if (id == R.id.action_logout) {
            return true;
        }
        if(id == R.id.action_notify_doctor) {
            Intent intentNotify = new Intent(this, NotifyToDoctor.class);
            startActivity(intentNotify);
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

}
