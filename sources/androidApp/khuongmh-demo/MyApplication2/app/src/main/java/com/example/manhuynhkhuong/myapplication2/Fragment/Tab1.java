package com.example.manhuynhkhuong.myapplication2.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.manhuynhkhuong.myapplication2.Adapter.InfoAdapter;
import com.example.manhuynhkhuong.myapplication2.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {


    public Tab1() {
        // Required empty public constructor
    }

    InfoAdapter adapter;
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        ArrayList<HashMap<String,String>> sections = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> d = new HashMap<String,String>();
        d.put("Image","");
        d.put("Title","Thuốc Uống");
        d.put("Info","");
        sections.add(d);
        d = new HashMap<String,String>();
        d.put("Image","");
        d.put("Title","Thức Ăn");
        d.put("Info","");
        sections.add(d);
        d = new HashMap<String,String>();
        d.put("Image","");
        d.put("Title","Luyện Tập");
        d.put("Info","");
        sections.add(d);
        listView = (ListView) v.findViewById(R.id.current_info);
        adapter = new InfoAdapter(getActivity(),sections);
        listView.setAdapter(adapter);
        return v;
    }


}
