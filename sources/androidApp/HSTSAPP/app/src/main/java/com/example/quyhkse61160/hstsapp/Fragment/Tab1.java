package com.example.quyhkse61160.hstsapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quyhkse61160.hstsapp.Adapter.FoodAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {

    FoodAdapter adapter;
    ListView listView;

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment_tab_1, container, false);
        ArrayList<HashMap<String,String>> sections = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> d = new HashMap<>();
        d.put("FoodName", "Rau");
        d.put("QuantitativeOfFood", "Nhiều");
        sections.add(d);
        d = new HashMap<>();
        d.put("FoodName", "Cá");
        d.put("QuantitativeOfFood", "Nhiều");
        sections.add(d);
        d = new HashMap<>();
        d.put("FoodName", "Cơm");
        d.put("QuantitativeOfFood", "Ít");
        sections.add(d);
        listView = (ListView) v.findViewById(R.id.list_food_treatment);

        adapter = new FoodAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }


}
