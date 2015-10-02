package com.example.quyhkse61160.hstsapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.PracticeAdapter;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment {

    PracticeAdapter adapter;
    ListView listView;

    public Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_tab_3, container, false);
        ArrayList<HashMap<String,String>> sections = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> d = new HashMap<>();
        d.put("PracticeName", "Đi bộ");
        d.put("PracticeTime", "15 phút");
        sections.add(d);
        d = new HashMap<>();
        d.put("PracticeName", "Chạy bộ");
        d.put("PracticeTime", "20 phút");
        sections.add(d);
        d = new HashMap<>();
        d.put("PracticeName", "Đạp xe đạp");
        d.put("PracticeTime", "10 phút");
        sections.add(d);
        d = new HashMap<>();
        d.put("PracticeName", "Chơi đá bóng");
        d.put("PracticeTime", "15 phút");
        sections.add(d);
        listView = (ListView) v.findViewById(R.id.list_practice_treatment);
        adapter = new PracticeAdapter(getActivity(), sections);
        listView.setAdapter(adapter);
        return v;
    }


}
