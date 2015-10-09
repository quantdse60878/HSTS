package com.example.quyhkse61160.hstsapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    Button food, medicine, practice;
    TextView ill, toDate, fromDate, nextApp, adviceFood, adviceMedicine, advicePractice;

    public Tab4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_tab_4, container, false);
        ill = (TextView) v.findViewById(R.id.notice_illness_name);
        toDate = (TextView) v.findViewById(R.id.notice_to_date);
        fromDate = (TextView) v.findViewById(R.id.notice_from_date);
        nextApp = (TextView) v.findViewById(R.id.notice_next_appointment);
        adviceFood = (TextView) v.findViewById(R.id.notice_advice_food);
        adviceMedicine = (TextView) v.findViewById(R.id.notice_advice_medicine);
        advicePractice = (TextView) v.findViewById(R.id.notice_advice_practical);

        ill.setText(Constant.TREATMENTS.get(0).getIllnessName());
        toDate.setText(Constant.TREATMENTS.get(0).getToDate());
        fromDate.setText(Constant.TREATMENTS.get(0).getFromDate());
        nextApp.setText(Constant.TREATMENTS.get(0).getNextAppointment());
        adviceFood.setText(Constant.TREATMENTS.get(0).getAdviseFood());
        adviceMedicine.setText(Constant.TREATMENTS.get(0).getAdviseMedicine());
        advicePractice.setText(Constant.TREATMENTS.get(0).getAdvicePractice());


        food = (Button) v.findViewById(R.id.btn_foods);
        medicine = (Button) v.findViewById(R.id.btn_medicines);
        practice = (Button) v.findViewById(R.id.btn_pracrices);
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
