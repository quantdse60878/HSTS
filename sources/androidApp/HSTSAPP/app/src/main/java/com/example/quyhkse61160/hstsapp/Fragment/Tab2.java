package com.example.quyhkse61160.hstsapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.HomeActivity;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 9/28/2015.
 */
public class Tab2 extends Fragment {

    MedicineAdapter adapter;
    ListView listView;
    TextView time_use;
    static ArrayList<HashMap<String, String>> sections;

    public Tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment_tab_2, container, false);

//        HashMap<String,String> d = new HashMap<>();
//        d.put("MedicineName", "Paracitamol 500mg");
//        d.put("NumberOfMedicine", "1");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("MedicineName", "Tiffy");
//        d.put("NumberOfMedicine", "2");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("MedicineName", "Panadol");
//        d.put("NumberOfMedicine", "1");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("MedicineName", "Simvastatin");
//        d.put("NumberOfMedicine", "1");
//        sections.add(d);

        //KhuongMH
//        List<ToDoTime> medicines = Constant.getItems(Constant.MEDICINE_FROM_JSON);
//        ToDoTime medicine = medicines.get(0);
//        for (int i = 0; i<medicine.getItems().size();i++){
//            ToDoItem item = medicine.getItems().get(i);
//            HashMap<String,String> d = new HashMap<>();
//            d.put("MedicineName", item.getName());
//            d.put("NumberOfMedicine", item.getQuantity());
//            sections.add(d);
//        }
//
        time_use = (TextView) v.findViewById(R.id.time_use);
        time_use.setText(HomeActivity.timeAlert);
        updateData();
        //KhuongMH

        listView = (ListView) v.findViewById(R.id.list_medicine_treatment);
        adapter = new MedicineAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }

    public static void updateData() {
        sections = new ArrayList<HashMap<String, String>>();
        List<Treatment> treatments = Constant.TREATMENTS;
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListMedicineTreatment()) {
                if (time.getNumberOfTime().contains(HomeActivity.timeAlert)) {
                    HashMap<String, String> d = new HashMap<>();
                    d.put("MedicineName", time.getName());
                    if (time.getAdvice().isEmpty() || time.getAdvice().equals("null")) d.put("Advice", "");
                    else d.put("Advice", time.getAdvice());
                    d.put("NumberOfMedicine", time.getQuantitative() + " " + time.getUnit());
                    sections.add(d);
                }
            }
        }
    }

}
