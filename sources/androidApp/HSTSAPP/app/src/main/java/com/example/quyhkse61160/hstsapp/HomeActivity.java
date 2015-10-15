package com.example.quyhkse61160.hstsapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quyhkse61160.hstsapp.Adapter.NavDrawerListAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.ViewPagesAdapter;
import com.example.quyhkse61160.hstsapp.Classes.AlarmManagerBroadcastReceiver;
import com.example.quyhkse61160.hstsapp.Classes.NavDrawerItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Common.HSTSUtils;
import com.example.quyhkse61160.hstsapp.Fragment.Tab1;
import com.example.quyhkse61160.hstsapp.Fragment.Tab2;
import com.example.quyhkse61160.hstsapp.Fragment.Tab3;
import com.example.quyhkse61160.hstsapp.Fragment.Tab4;
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
    public static boolean hadRegisterReceiver = false;


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean flag = false;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter dadapter;

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
        // Food
        boolean hasFood = true;
        boolean hasMedicine = true;
        boolean hasPractice = true;
//        for(Treatment treatment : Constant.TREATMENTS){
//            for (ToDoTime time : treatment.getListFoodTreatment()) {
//                if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
//                    hasFood = true;
//                }
//            }
//            for (ToDoTime time : treatment.getListMedicineTreatment()) {
//                if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
//                    hasMedicine = true;
//                }
//            }
//            for (ToDoTime time : treatment.getListPracticeTreatment()) {
//                if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
//                    hasPractice = true;
//                }
//            }
//        }

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), hasFood));
        // Medicines
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), hasMedicine));
        // Practice
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), hasPractice));


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
                    NavDrawerItem item = (NavDrawerItem) parent.getItemAtPosition(position);
                    item.setNotify(false);
                    updateView(position);
                    displayView(0);
                }
                if (position == 1) {
                    NavDrawerItem item = (NavDrawerItem) parent.getItemAtPosition(position);
                    item.setNotify(false);
                    updateView(position);
                    displayView(1);
                }
                if (position == 2) {
                    NavDrawerItem item = (NavDrawerItem) parent.getItemAtPosition(position);
                    item.setNotify(false);
                    updateView(position);
                    displayView(2);
                }
                if (position == 3) {
                    NavDrawerItem item = (NavDrawerItem) parent.getItemAtPosition(position);
                    item.setNotify(false);
                    updateView(position);
                    item.setNotify(false);
                    displayView(3);
                }
            }
        });

        // enabling action bar app icon and behaving it as toggle button
        actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
        if(hasFood || hasMedicine || hasPractice) actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_red);
        else {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                actionBar.setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
//                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                actionBar.setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
//                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }











        checkNotifyIntent = new Intent(this, BroadcastService.class);
        //KhuongMH
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final Context context = getApplicationContext();
            if (bundle.getBoolean("openDialogForMe")) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Bạn đến giờ ăn, uống thuốc, tập luyện")
                        .setPositiveButton("Ngưng Nhắc Nhở", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BroadcastService.alertMinute = 0;
                            }
                        })
                        .setNegativeButton("Làm Sau", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BroadcastService.alertMinute += 5;
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
            if (bundle.getBoolean("notFinished")) {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Nhắc Nhở").setMessage("Hôm qua bạn chưa hoàn thành chế độ điều trị, hãy cố gắng thực hiện để việc điều trị được tốt hơn.");
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


//        actionBar = getSupportActionBar();
//        viewPager = (ViewPager) findViewById(R.id.pager);
//        adapter = new ViewPagesAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3ea000")));
//        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#4ABC02")));
//
//        ActionBar.Tab atab1 = actionBar.newTab().setText("THỨC ĂN").setTabListener(this);
//        ActionBar.Tab atab2 = actionBar.newTab().setText("THUỐC").setTabListener(this);
//        ActionBar.Tab atab3 = actionBar.newTab().setText("LUYỆN TẬP").setTabListener(this);
//        ActionBar.Tab atab4 = actionBar.newTab().setText("THÔNG BÁO").setTabListener(this);
//        actionBar.addTab(atab1);
//        actionBar.addTab(atab2);
//        actionBar.addTab(atab3);
//        actionBar.addTab(atab4);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                actionBar.setSelectedNavigationItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


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

        if(!hadRegisterReceiver) {
            hadRegisterReceiver = true;
            registerReceiver(mConnectionDetector, mIntentFilter);
        }


//        Constant.TREATMENTS = Constant.getItems();

        //Set Alarm
//        amountTime = amountTime();
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
                fragment = new Tab1();
                break;
            case 2:
                fragment = new Tab2();
                break;
            case 3:
                fragment = new Tab3();
                break;
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
            case R.id.action_settings:
                return true;
            case R.id.action_logout:
                return true;
            case R.id.action_notify_doctor:{
                Intent intentNotify = new Intent(this, NotifyToDoctor.class);
                startActivity(intentNotify);
                break;
            }
            case R.id.action_setstep:{
                Intent intentStep = new Intent(this, SetNumberStepActivity.class);
                startActivity(intentStep);
                break;
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

}
