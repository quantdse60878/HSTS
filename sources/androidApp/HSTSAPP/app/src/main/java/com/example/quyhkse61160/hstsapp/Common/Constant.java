package com.example.quyhkse61160.hstsapp.Common;

import java.util.UUID;

/**
 * Created by QUYHKSE61160 on 9/12/2015.
 */
public class Constant {
    //All constant of call api
    public static final String hostURL = "http://localhost";
    public static final String loginMethod = "/login";

    //All constant of shared preference
    public static final String PREF_NAME = "HSTSAPPPREF";
    public static final String PREF_USENAME_HADLOGIN = "HSTSAPPPREFLOGIN";
    public static String NUMBEROFSTEP_POSITION = "3";



    //
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String DEVICE_INFORMATION = "0000180a-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static final UUID main_service_UUID = UUID.fromString("0000ff01-0000-1000-8000-00805f9b34fb");
    public static final UUID movementData_current_UUID = UUID.fromString("0000f019-0000-1000-8000-00805f9b34fb");
    public static final UUID numberOfStep_UUID = UUID.fromString("00002a25-0000-1000-8000-00805f9b34fb");
    public static final UUID manufacturer_UUID = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");

    //KhuongMH
    public static String ASSET_PATH = "";
    //KhuongMH

}
