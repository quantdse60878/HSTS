package com.example.quyhkse61160.hstsapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.Adapter.FoodAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.HomeActivity;
import com.example.quyhkse61160.hstsapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {

    FoodAdapter adapter;
    ListView listView;
    TextView time_eat;
    static ArrayList<HashMap<String, String>> sections;

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment_tab_1, container, false);

//        HashMap<String,String> d = new HashMap<>();
//        d.put("FoodName", "Rau");
//        d.put("QuantitativeOfFood", "200gr");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("FoodName", "Cá");
//        d.put("QuantitativeOfFood", "300gr");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("FoodName", "Cơm");
//        d.put("QuantitativeOfFood", "1 chén");
//        sections.add(d);

        //KhuongMH
        updateData();
        time_eat = (TextView) v.findViewById(R.id.time_eat);
        time_eat.setText(HomeActivity.timeAlert);
        //KhuongMH

        listView = (ListView) v.findViewById(R.id.list_food_treatment);
        adapter = new FoodAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }

    public static void updateData() {
        sections = new ArrayList<HashMap<String, String>>();
        List<Treatment> treatments = Constant.TREATMENTS;
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListFoodTreatment()) {
                if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
                    HashMap<String, String> d = new HashMap<>();
                    d.put("FoodName", time.getName());
                    if (time.getAdvice().isEmpty() || time.getAdvice().equals("null")) d.put("Advice", "");
                    else d.put("Advice", time.getAdvice());
                    d.put("QuantitativeOfFood", time.getQuantitative());
                    sections.add(d);

                }
            }
        }
    }


}
