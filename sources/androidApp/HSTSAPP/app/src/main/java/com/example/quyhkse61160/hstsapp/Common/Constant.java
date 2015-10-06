package com.example.quyhkse61160.hstsapp.Common;

import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by QUYHKSE61160 on 9/12/2015.
 */
public class Constant {
    //All constant of call api
    public static final String hostURL = "http://localhost:8080";
    public static final String loginMethod = "/login";
    public static final String checkNotifyMethod = "/notify";

    //All constant of shared preference
    public static final String PREF_NAME = "HSTSAPPPREF";
    public static final String PREF_USENAME_HADLOGIN = "HSTSAPPPREFLOGIN";
    public static String NUMBEROFSTEP_POSITION = "3";

    public static String accountId = "";
    public static String patientId = "";

    //
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String DEVICE_INFORMATION = "0000180a-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    public static final UUID main_service_UUID = UUID.fromString("0000ff01-0000-1000-8000-00805f9b34fb");
    public static final UUID movementData_current_UUID = UUID.fromString("0000f019-0000-1000-8000-00805f9b34fb");
    public static final UUID numberOfStep_UUID = UUID.fromString("00002a25-0000-1000-8000-00805f9b34fb");
    public static final UUID manufacturer_UUID = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");

    //KhuongMH
    public static String DATA_FROM_SERVER = "";

    public static List<ToDoTime> getItems(String field){
        List<ToDoTime> foods = new ArrayList<>();
        try{
            JSONObject obj = new JSONObject(Constant.DATA_FROM_SERVER);
            JSONArray array = obj.getJSONArray(field);
            for(int i = 0;i< array.length();i++){
                ToDoTime food = new ToDoTime();
                food.setTimeUse(array.getJSONObject(i).getString("timeUse"));
                JSONArray array1 = array.getJSONObject(i).getJSONArray("items");
                for (int j = 0;j<array1.length();j++){
                    ToDoItem item = new ToDoItem();
                    item.setName(array1.getJSONObject(j).getString("name"));
                    item.setQuantity(array1.getJSONObject(j).getString("quantity"));
                    food.getItems().add(item);
                }
                foods.add(food);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return foods;
    }

    //KhuongMH

}
