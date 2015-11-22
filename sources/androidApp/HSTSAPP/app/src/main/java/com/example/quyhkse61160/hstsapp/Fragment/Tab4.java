package com.example.quyhkse61160.hstsapp.Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.Adapter.InfoAdapter;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.HomeActivity;
import com.example.quyhkse61160.hstsapp.NoticeActivity;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab4 extends Fragment {

    LinearLayout food, medicine, practice;
    TextView name, ill, fromDate, nextApp;

    public Tab4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_tab_4, container, false);

        name = (TextView) v.findViewById(R.id.notice_patient_name);
        ill = (TextView) v.findViewById(R.id.notice_illness_name);
        fromDate = (TextView) v.findViewById(R.id.notice_from_date);
        nextApp = (TextView) v.findViewById(R.id.notice_next_appointment);
        name.setText(Constant.PATIENT_NAME);
        ill.setText(Constant.TREATMENTS.get(0).getIllnessName());

        if(!Constant.TREATMENTS.get(0).getFromDate().isEmpty()){
            String[] temp = Constant.TREATMENTS.get(0).getFromDate().split(" ")[0].split("-");
            fromDate.setText(temp[2] + "-" + temp[1] + "-" + temp[0]);
        } else {
            fromDate.setText("");
        }
        if(!Constant.TREATMENTS.get(0).getNextAppointment().isEmpty()){
            String[] temp = Constant.TREATMENTS.get(0).getNextAppointment().split(" ")[0].split("-");
            nextApp.setText(temp[2] + "-" + temp[1] + "-" + temp[0]);
        } else {
            nextApp.setText("");
        }

        food = (LinearLayout) v.findViewById(R.id.btn_foods);
        medicine = (LinearLayout) v.findViewById(R.id.btn_medicines);
        practice = (LinearLayout) v.findViewById(R.id.btn_pracrices);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                intent.putExtra("field","food");
                startActivity(intent);
            }
        });
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                intent.putExtra("field","medicine");
                startActivity(intent);
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                intent.putExtra("field","practice");
                startActivity(intent);
            }
        });
        return v;
    }


}
