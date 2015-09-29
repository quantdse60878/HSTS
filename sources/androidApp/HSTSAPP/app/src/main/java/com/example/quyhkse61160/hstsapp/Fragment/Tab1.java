package com.example.quyhkse61160.hstsapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quyhkse61160.hstsapp.Adapter.InfoAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 9/28/2015.
 */
public class Tab1 extends Fragment {

    MedicineAdapter adapter;
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
        d.put("MedicineName", "Paracitamol 500mg");
        d.put("NumberOfMedicine", "1");
        sections.add(d);
        d = new HashMap<>();
        d.put("MedicineName", "Tiffy");
        d.put("NumberOfMedicine", "2");
        sections.add(d);
        d = new HashMap<>();
        d.put("MedicineName", "Panadol");
        d.put("NumberOfMedicine", "1");
        sections.add(d);
        d = new HashMap<>();
        d.put("MedicineName", "Simvastatin");
        d.put("NumberOfMedicine", "1");
        sections.add(d);
        listView = (ListView) v.findViewById(R.id.list_medicine_treatment);
        adapter = new MedicineAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }

}
