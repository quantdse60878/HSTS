package com.example.quyhkse61160.hstsapp.Service;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.HomeActivity;
import com.example.quyhkse61160.hstsapp.SelectDeviceActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 9/21/2015.
 */
public class BluetoothLeService extends Service {

    private final static String TAG = BluetoothLeService.class.getSimpleName();
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt;


    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";


    private final IBinder mBinder = new LocalBinder();

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            broadcastUpdate(ACTION_DATA_AVAILABLE);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            final byte[] data = characteristic.getValue();

            String stringdata = new String(data);
            Log.d("QUYYYYYYYYY", "--" + stringdata + "--");
            if((HomeActivity.characteristicManufacturer != null) && (HomeActivity.characteristicStep != null)) {
                if(characteristic.getUuid().toString().contains(Constant.numberOfStep_UUID.toString())) {
//                    String[] listData = stringdata.split(",");
//                    HomeActivity.numberOfStep = listData[HomeActivity.position];
//
//                    Log.d("Quyyyyy111", "NumberOfStep: " + stringdata + "--");

                    if (data != null && data.length > 0) {

                        String byteData = "";
                        for(int i = 0; i < data.length; i++) {
                            if(i < data.length-1)
                                byteData = byteData + data[i] + ",";
                            else
                                byteData = byteData + data[i];
                        }


                        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        Date date = new Date();
                        String dateString = df.format(date).replaceAll("/", "");
                        String FILENAME = dateString + ".txt";
                        File root = Environment.getExternalStorageDirectory();
                        File dir = new File(root.getAbsolutePath() + "/kimquy");
                        dir.mkdirs();
                        File file = new File(dir, FILENAME);
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(file);
                            fos.write(byteData.getBytes());
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("QUYYYYYYYYY", "--hehe--");
                    }


                } else if(characteristic.getUuid().toString().contains(Constant.manufacturer_UUID.toString())) {
                    if (data != null && data.length > 0) {
                        final StringBuilder stringBuilder = new StringBuilder(data.length);
                        for(byte byteChar : data)
                            stringBuilder.append(String.format("%02X ", byteChar));
                    }
                    String byteData = "";
                    for(int i = 0; i < data.length; i++) {
                        if(i < data.length-1)
                            byteData = byteData + data[i] + ",";
                        else
                            byteData = byteData + data[i];
                    }
                    Constant.manufacturer = byteData;
                    Log.e("-===-", "--" + Constant.manufacturer);
                }
            }
            broadcastUpdate(ACTION_DATA_AVAILABLE);

        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            mBluetoothGatt.discoverServices();
            if(newState == BluetoothProfile.STATE_CONNECTED) {
                broadcastUpdate(ACTION_GATT_CONNECTED);
            }
            if(newState == BluetoothProfile.STATE_DISCONNECTED) {
                broadcastUpdate(ACTION_GATT_DISCONNECTED);
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
        }

    };


    public boolean initialize() {
        // For API level 18 and above, get a reference to BluetoothAdapter through
        // BluetoothManager.
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }
        }
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }
        return true;
    }

    public boolean connect(final String address) {
        if (mBluetoothAdapter == null || address == null) {
            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        // Previously connected device.  Try to reconnect.
        if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress)
                && mBluetoothGatt != null) {
            Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
            if (mBluetoothGatt.connect()) {
                return true;
            } else {
                return false;
            }
        }
        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {
            Log.w(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        // We want to directly connect to the device, so we are setting the autoConnect
        // parameter to false.
        mBluetoothGatt = device.connectGatt(this, false, mGattCallback);

        Log.d(TAG, "Trying to create a new connection.");
        mBluetoothDeviceAddress = address;
        return true;
    }

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.readCharacteristic(characteristic);
    }

    public List<BluetoothGattService> getSupportedGattServices() {
        if (mBluetoothGatt == null) return null;
        return mBluetoothGatt.getServices();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class LocalBinder extends Binder {
        public BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }
    @Override
    public boolean onUnbind(Intent intent) {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        close();
        return super.onUnbind(intent);
    }
    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);


        List<BluetoothGattService> gattServices = mBluetoothGatt.getServices();

        sendBroadcast(intent);
    }
    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }

}
