package com.example.quyhkse61160.hstsapp.Common;

import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Constant {
    //All constant of call api
    public static String hostURL = "http://192.168.20.170:8080";
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
    public static String PATIENT_NAME = "";


    public static List<Treatment> getItems() {
        List<Treatment> foods = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(Constant.DATA_FROM_SERVER);
            for (int i = 0; i < jsonArray.length(); i++) {
                Treatment treatment = new Treatment();
                treatment.setIllnessName(jsonArray.getJSONObject(i).getString("illnessName"));
                treatment.setNextAppointment(jsonArray.getJSONObject(i).getString("nextAppointment"));
                treatment.setFromDate(jsonArray.getJSONObject(i).getString("fromDate"));
                treatment.setToDate(jsonArray.getJSONObject(i).getString("toDate"));
                treatment.setCaloriesBurnEveryday(jsonArray.getJSONObject(i).getString("caloriesBurnEveryday"));
                treatment.setListFoodTreatment(Constant.getInfoTreatment(jsonArray.getJSONObject(i).getJSONArray(Constant.FOOD_FROM_JSON)
                        , Constant.FOOD_FROM_JSON));
                treatment.setListMedicineTreatment(Constant.getInfoTreatment(jsonArray.getJSONObject(i).getJSONArray(Constant.MEDICINE_FROM_JSON)
                        , Constant.MEDICINE_FROM_JSON));
                treatment.setListPracticeTreatment(Constant.getInfoTreatment(jsonArray.getJSONObject(i).getJSONArray(Constant.PRACTICE_FROM_JSON)
                        , Constant.PRACTICE_FROM_JSON));
                foods.add(treatment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return foods;
    }

    public static List<ToDoTime> getInfoTreatment(JSONArray source, String field) {
        List<ToDoTime> times = new ArrayList<>();
        try {
            for(int i = 0; i < source.length();i++){
                ToDoTime time = new ToDoTime();
                time.setName(source.getJSONObject(i).getString("name"));
                time.setQuantitative(source.getJSONObject(i).getString("quantitative"));
                int tempTime = source.getJSONObject(i).getInt("numberOfTime");
                List<String> numberOfTime = new ArrayList<>();
                numberOfTime.add("07:00");
                if(tempTime == 2){
                    numberOfTime.add("18:00");
                }
                if(tempTime == 3){
                    numberOfTime.add("12:00");
                    numberOfTime.add("18:00");
                }
                if(tempTime == 4){
                    numberOfTime.add("12:00");
                    numberOfTime.add("18:00");
                    numberOfTime.add("21:00");
                }
                if(tempTime == 5){
                    numberOfTime.add("09:00");
                    numberOfTime.add("12:00");
                    numberOfTime.add("15:00");
                    numberOfTime.add("18:00");
                }
                if(tempTime == 6){
                    numberOfTime.add("09:00");
                    numberOfTime.add("12:00");
                    numberOfTime.add("15:00");
                    numberOfTime.add("18:00");
                    numberOfTime.add("21:00");
                }
                if(field.equals(MEDICINE_FROM_JSON)){
                    time.setUnit(source.getJSONObject(i).getString("unit"));
                }
                time.setNumberOfTime(numberOfTime);
                time.setAdvice(source.getJSONObject(i).getString("advice"));
                times.add(time);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return times;
    }

    public static List<Treatment> TREATMENTS = new ArrayList<>();

//    public static List<ToDoTime> Foods = new ArrayList<>();
//    public static List<ToDoTime> Medicines = new ArrayList<>();
//    public static List<ToDoTime> Practice = new ArrayList<>();

    //KhuongMH

}
