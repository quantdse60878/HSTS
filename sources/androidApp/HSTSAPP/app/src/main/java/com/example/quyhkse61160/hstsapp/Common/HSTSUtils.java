package com.example.quyhkse61160.hstsapp.Common;

import android.content.res.AssetManager;

import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by QUYHKSE61160 on 9/29/2015.
 */
public class HSTSUtils {


    public static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public static String loadData(AssetManager am){
        String str;
        BufferedReader in = null;
        StringBuilder buf = null;
        try{
            buf = new StringBuilder();
            InputStream json = am.open("treatment.json");
            if(json != null) {
                in = new BufferedReader(new InputStreamReader(json));
                while ((str=in.readLine()) != null) {
                    buf.append(str);
                }
            }
        } catch(Exception e){
            return "";
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  buf.toString();
    }

    public static List<Treatment> getItems() {
        List<Treatment> foods = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(Constant.DATA_FROM_SERVER);
            for (int i = 0; i < jsonArray.length(); i++) {
                Treatment treatment = new Treatment();
                treatment.setIllnessName(jsonArray.getJSONObject(i).getString("illnessName"));
                treatment.setNextAppointment(jsonArray.getJSONObject(i).getString("nextAppointment"));
                if(jsonArray.getJSONObject(i).getString("nextAppointment").isEmpty()){
                    Constant.PATIENT_APPOINTMENT = null;
                } else {
                    Constant.PATIENT_APPOINTMENT = new SimpleDateFormat("yyyy-MM-dd").parse(jsonArray.getJSONObject(i).getString("nextAppointment").split(" ")[0]);
                }
                treatment.setFromDate(jsonArray.getJSONObject(i).getString("fromDate"));
                treatment.setToDate(jsonArray.getJSONObject(i).getString("toDate"));
                treatment.setCaloriesBurnEveryday(jsonArray.getJSONObject(i).getString("caloriesBurnEveryday"));
                treatment.setListFoodTreatment(getInfoTreatment(jsonArray.getJSONObject(i).getJSONArray(Constant.FOOD_FROM_JSON)
                        , Constant.FOOD_FROM_JSON));
                treatment.setListMedicineTreatment(getInfoTreatment(jsonArray.getJSONObject(i).getJSONArray(Constant.MEDICINE_FROM_JSON)
                        , Constant.MEDICINE_FROM_JSON));
                treatment.setListPracticeTreatment(getInfoTreatment(jsonArray.getJSONObject(i).getJSONArray(Constant.PRACTICE_FROM_JSON)
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
                if(field.equals(Constant.MEDICINE_FROM_JSON)){
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

    public static String encryptMD5(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
