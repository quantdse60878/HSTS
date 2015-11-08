package com.example.quyhkse61160.hstsapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quyhkse61160.hstsapp.Adapter.FoodAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.MedicineAdapter;
import com.example.quyhkse61160.hstsapp.Adapter.PracticeAdapter;
import com.example.quyhkse61160.hstsapp.Classes.ToDoTime;
import com.example.quyhkse61160.hstsapp.Classes.Treatment;
import com.example.quyhkse61160.hstsapp.Common.Constant;
import com.example.quyhkse61160.hstsapp.HomeActivity;
import com.example.quyhkse61160.hstsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Man Huynh Khuong on 11/6/2015.
 */
public class NoticeNextTab extends Fragment {

    LinearLayout food, medicine, practice;
    LinearLayout llFood, llMedicine, llPractice, llNotice;
    TextView notice;
    FoodAdapter foodAdapter;
    MedicineAdapter medicineAdapter;
    PracticeAdapter practiceAdapter;
    ArrayList<HashMap<String, String>> sections;
    boolean flag = false;
    String time = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notice_tab, container, false);
        food = (LinearLayout) v.findViewById(R.id.list_food);
        medicine = (LinearLayout) v.findViewById(R.id.list_medicine);
        practice = (LinearLayout) v.findViewById(R.id.list_practice);
        llFood = (LinearLayout) v.findViewById(R.id.first);
        llMedicine = (LinearLayout) v.findViewById(R.id.second);
        llPractice = (LinearLayout) v.findViewById(R.id.third);
        llNotice = (LinearLayout) v.findViewById(R.id.fourth);
        notice = (TextView) v.findViewById(R.id.time_notice);

        //update food notify
        if(HomeActivity.timeAlert == null) {
            llNotice.setVisibility(View.GONE);
        } else {
            llNotice.setVisibility(View.VISIBLE);
            for(int i = 0; i < Constant.TIMES.size(); i++){
                if(HomeActivity.timeAlert.equals(Constant.TIMES.get(i))){
                    if(i == Constant.TIMES.size() - 1) continue;
                    this.time = Constant.TIMES.get(i + 1);
                }
            }
            notice.setText(this.time);
        }
        String temp = "";
        sections = new ArrayList<>();
        List<Treatment> treatments = Constant.TREATMENTS;
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListFoodTreatment()) {
                if (time.getNumberOfTime().contains(this.time)) {
                    HashMap<String, String> d = new HashMap<>();
                    temp = time.getName();
                    if (!time.getAdvice().isEmpty() && !time.getAdvice().equals("null")){
                        temp += "(" + time.getAdvice() + ")";
                    }
                    d.put("FoodName", temp);
//                    if (time.getAdvice().isEmpty() || time.getAdvice().equals("null"))
//                        d.put("Advice", "");
//                    else d.put("Advice", time.getAdvice());
                    d.put("QuantitativeOfFood", time.getQuantitative());
                    sections.add(d);
                }
            }
        }
        if (sections.isEmpty()) {
            llFood.setVisibility(View.GONE);
        } else {
            llFood.setVisibility(View.VISIBLE);
            foodAdapter = new FoodAdapter(getActivity(), sections);
            for (int i=0; i < foodAdapter.getCount();i++){
                View vi = foodAdapter.getView(i,null,food);
                food.addView(vi);
            }
        }

        //update medicine notify
        sections = new ArrayList<>();
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListMedicineTreatment()) {
                if (time.getNumberOfTime().contains(this.time)) {
                    HashMap<String, String> d = new HashMap<>();
                    temp = time.getName();
                    if (!time.getAdvice().isEmpty() && !time.getAdvice().equals("null")){
                        temp += "(" + time.getAdvice() + ")";
                    }
                    d.put("MedicineName", temp);
//                    if (time.getAdvice().isEmpty() || time.getAdvice().equals("null")) d.put("Advice", "");
//                    else d.put("Advice", time.getAdvice());
                    d.put("NumberOfMedicine", time.getQuantitative() + " " + time.getUnit());
                    sections.add(d);
                }
            }
        }
        if (sections.isEmpty()) {
            llMedicine.setVisibility(View.GONE);
        } else {
            llMedicine.setVisibility(View.VISIBLE);
            medicineAdapter = new MedicineAdapter(getActivity(), sections);
            for (int i=0; i < medicineAdapter.getCount();i++){
                View vi = medicineAdapter.getView(i,null,medicine);
                medicine.addView(vi);
            }
        }

        //update food notify
        sections = new ArrayList<>();
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListPracticeTreatment()) {
                if (time.getNumberOfTime().contains(this.time)) {
                    HashMap<String, String> d = new HashMap<>();
                    temp = time.getName();
                    if (!time.getAdvice().isEmpty() && !time.getAdvice().equals("null")){
                        temp += "(" + time.getAdvice() + ")";
                    }
                    d.put("PracticeName", temp);
//                    if (time.getAdvice().isEmpty() || time.getAdvice().equals("null")) d.put("Advice", "");
//                    else d.put("Advice", time.getAdvice());
                    d.put("PracticeTime", time.getQuantitative());
                    sections.add(d);
                }
            }
        }
        if (sections.isEmpty()) {
            llPractice.setVisibility(View.GONE);
        } else {
            llPractice.setVisibility(View.VISIBLE);
            practiceAdapter = new PracticeAdapter(getActivity(), sections);
            for (int i=0; i < practiceAdapter.getCount();i++){
                View vi = practiceAdapter.getView(i,null,practice);
                practice.addView(vi);
            }
        }
        HomeActivity.hasNotify = false;
        return v;
    }

}
