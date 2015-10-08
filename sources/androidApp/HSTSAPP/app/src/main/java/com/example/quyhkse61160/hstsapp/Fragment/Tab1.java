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
import com.example.quyhkse61160.hstsapp.Common.Constant;
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

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment_tab_1, container, false);
        ArrayList<HashMap<String,String>> sections = new ArrayList<HashMap<String,String>>();
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
//        List<ToDoTime> foods = Constant.getItems(Constant.FOOD_FROM_JSON);
//        ToDoTime food = foods.get(0);
//        for (int i = 0; i<food.getItems().size();i++){
//            ToDoItem item = food.getItems().get(i);
//            HashMap<String,String> d = new HashMap<>();
//            d.put("FoodName", item.getName());
//            d.put("QuantitativeOfFood", item.getQuantity());
//            sections.add(d);
//        }
//        time_eat = (TextView) v.findViewById(R.id.time_eat);
//        time_eat.setText(food.getTimeUse());
        //KhuongMH

        listView = (ListView) v.findViewById(R.id.list_food_treatment);
        adapter = new FoodAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }


}
