package com.example.quyhkse61160.hstsapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.PracticeAdapter;
import com.example.quyhkse61160.hstsapp.Classes.ToDoItem;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment {

    PracticeAdapter adapter;
    ListView listView;
    TextView time_practice;

    public Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_tab_3, container, false);
        ArrayList<HashMap<String,String>> sections = new ArrayList<HashMap<String,String>>();
//        HashMap<String,String> d = new HashMap<>();
//        d.put("PracticeName", "Đi bộ");
//        d.put("PracticeTime", "15 phút");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("PracticeName", "Chạy bộ");
//        d.put("PracticeTime", "20 phút");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("PracticeName", "Đạp xe đạp");
//        d.put("PracticeTime", "10 phút");
//        sections.add(d);
//        d = new HashMap<>();
//        d.put("PracticeName", "Chơi đá bóng");
//        d.put("PracticeTime", "15 phút");
//        sections.add(d);

        //KhuongMH
        List<ToDoTime> practices = Constant.getItems("practice");
        ToDoTime practice = practices.get(0);
        for (int i = 0; i<practice.getItems().size();i++){
            ToDoItem item = practice.getItems().get(i);
            HashMap<String,String> d = new HashMap<>();
            d.put("PracticeName", item.getName());
            d.put("PracticeTime", item.getQuantity());
            sections.add(d);
        }

        time_practice = (TextView) v.findViewById(R.id.time_practice);
        time_practice.setText(practice.getTimeUse());
        //KhuongMH

        listView = (ListView) v.findViewById(R.id.list_practice_treatment);
        adapter = new PracticeAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }


}
