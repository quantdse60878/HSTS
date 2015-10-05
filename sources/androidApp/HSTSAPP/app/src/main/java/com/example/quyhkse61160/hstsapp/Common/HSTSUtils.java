package com.example.quyhkse61160.hstsapp.Common;

import android.content.res.AssetManager;

import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    public boolean loadData(){
        JsonParser parser = new JsonParser();
        try{
            Object obj = parser.parse(new FileReader(Constant.ASSET_PATH + "/treatment.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String treatmentName = (String) jsonObject.get("treatmentName");
            String nextAppointment = (String) jsonObject.get("nextAppointment");
            String fromDate = (String) jsonObject.get("fromDate");
            String toDate = (String) jsonObject.get("toDate");
            String advice = (String) jsonObject.get("advice");

        } catch (Exception e){
            return false;
        }
        return true;
    }
}
