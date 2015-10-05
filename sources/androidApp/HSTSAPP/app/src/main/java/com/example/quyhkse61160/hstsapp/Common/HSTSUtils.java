package com.example.quyhkse61160.hstsapp.Common;

import android.content.res.AssetManager;

import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public boolean loadData(String json){
        try{
            JSONObject obj = new JSONObject(json);
            String treatmentName = (String) obj.get("treatmentName");
            String nextAppointment = (String) obj.get("nextAppointment");
            String fromDate = (String) obj.get("fromDate");
            String toDate = (String) obj.get("toDate");
            String advice = (String) obj.get("advice");
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public String readAssets(AssetManager am){
        String str = "";
        BufferedReader in = null;
        try{
            StringBuilder buf=new StringBuilder();
            InputStream json = am.open("/treatment.json");
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            while ((str=in.readLine()) != null) {
                buf.append(str);
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
        return  str;
    }
}
