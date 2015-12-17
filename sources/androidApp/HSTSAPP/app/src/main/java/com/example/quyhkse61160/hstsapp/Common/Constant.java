package com.example.quyhkse61160.hstsapp.Common;

import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Constant {
    //All constant of call api
    public static String hostURL = "http://192.168.0.103:8080";
    public static final String loginMethod = "/loginMobile";
    public static final String checkNotifyMethod = "/notify";
    public static final String getTreatment = "/getTreatment";
    public static final String sendMedicalData = "/sendMedicalData";
    public static final String hadGetTreatment = "/hadGetNotify";
    public static final String sendNotifyToDoctor = "/sendNotifyToDoctor";
    public static final String changePassword = "/changePassword";
    public static final String sendUuidToAndroid = "/sendUuidToAndroid";

    //All constant of shared preference
    public static final String PREF_NAME = "HSTSAPPPREF";
    public static final String PREF_ACCOUNTID_HADLOGIN = "HSTSAPPPREFACCOUNT";
    public static final String PREF_PATIENTID_HADLOGIN = "HSTSAPPPREFPATIENT";
    public static final String PREF_HADSELECTDEVICE = "HSTSAPPPREFSELECTDEVICE";
    public static final String PREF_DATA = "HSTSAPPDATA";
    public static final String PREF_PATIENT_NAME = "HSTSAPPPREFPATIENTNAME";

    public static String accountId = "0";
    public static String patientId = "0";
    public static String username = "";
    public static String numberOfStep = "2000";
    public static int position = -1;
    public static String manufacturer = "";
    public static boolean haveInternet = false;

    //
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String DEVICE_INFORMATION = "0000180a-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static final UUID main_service_UUID = UUID.fromString("0000ff01-0000-1000-8000-00805f9b34fb");
    public static final UUID movementData_current_UUID = UUID.fromString("0000f019-0000-1000-8000-00805f9b34fb");
    public static UUID numberOfStep_UUID = UUID.fromString("00002a25-0000-1000-8000-00805f9b34fb");
    public static final UUID manufacturer_UUID = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");
    public static int numberOfStep_potition = 3;

    //KhuongMH
    public static String DATA_FROM_SERVER = "";
    public static String FOOD_FROM_JSON = "listFoodTreatment";
    public static String MEDICINE_FROM_JSON = "listMedicineTreatment";
    public static String PRACTICE_FROM_JSON = "listPracticeTreatment";
    public static String PATIENT_NAME = "Trần Đăng Quân";
    public static Date PATIENT_APPOINTMENT = new Date();
    public static List<String> TIMES = new ArrayList<>();
    public static List<Treatment> TREATMENTS = new ArrayList<>();
    public static int ALARM_TIME = 5;
    public static int ALARM_TIME_COUNT = 0;

//    public static List<ToDoTime> Foods = new ArrayList<>();
//    public static List<ToDoTime> Medicines = new ArrayList<>();
//    public static List<ToDoTime> Practice = new ArrayList<>();

    //KhuongMH

}
