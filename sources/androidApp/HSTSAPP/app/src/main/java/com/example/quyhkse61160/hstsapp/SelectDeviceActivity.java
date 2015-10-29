package com.example.quyhkse61160.hstsapp;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.Service.BluetoothLeService;
import com.example.quyhkse61160.hstsapp.Service.NetworkChangeReceiver;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SelectDeviceActivity extends AppCompatActivity {

    private final static String TAG = SelectDeviceActivity.class.getSimpleName();
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    private BluetoothLeService mBluetoothLeService;
    private String mDeviceName;
    private String mDeviceAddress;
    public static String numberOfStep = "0";
    public static int position = 0;
    public static String manufacturer = "Unknown";
    public static BluetoothGattCharacteristic characteristicStep = null;
    public static BluetoothGattCharacteristic characteristicManufacturer = null;
    public static TextView txtDeviceName;
    public static TextView txtNumberOfStep;
    public static TextView txtManufacturer;
    private Timer timer = new Timer();
    protected final IntentFilter mIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    protected final NetworkChangeReceiver mConnectionDetector = new NetworkChangeReceiver();

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);

        position = Constant.position;
        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        txtDeviceName = (TextView) findViewById(R.id.device_name);
        txtNumberOfStep = (TextView) findViewById(R.id.numberOfStep);
        txtManufacturer = (TextView) findViewById(R.id.manufacturerName);
        txtDeviceName.setText(mDeviceName);

        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }

        registerReceiver(mConnectionDetector, mIntentFilter);


    }


    @Override
    protected void onDestroy() {

        timer.cancel();
        timer.purge();
        super.onDestroy();
        unbindService(mServiceConnection);
        unregisterReceiver(mGattUpdateReceiver);
        unregisterReceiver(mConnectionDetector);
        mBluetoothLeService = null;
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

        return super.onOptionsItemSelected(item);
    }

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        List<BluetoothGattService> listService = mBluetoothLeService.getSupportedGattServices();
        for(int i = 0; i < listService.size(); i++) {
            if(listService.get(i).getUuid().toString().equals(Constant.DEVICE_INFORMATION)) {
                BluetoothGattService gattService = listService.get(i);
                if(gattService == null) {
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

        txtNumberOfStep.setText(numberOfStep);

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
                        txtNumberOfStep.setText(numberOfStep);
                        txtManufacturer.setText(manufacturer);
                    }
                });
            }
        }, 10000, 10000);


    }
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }


}
