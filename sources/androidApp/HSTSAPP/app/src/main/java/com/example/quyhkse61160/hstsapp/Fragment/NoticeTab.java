package com.example.quyhkse61160.hstsapp.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

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

public class NoticeTab extends Fragment {

    LinearLayout food, medicine, practice;
    LinearLayout llFood, llMedicine, llPractice, llNotice;
    TextView notice;
    FoodAdapter foodAdapter;
    MedicineAdapter medicineAdapter;
    PracticeAdapter practiceAdapter;
    ArrayList<HashMap<String, String>> sections;
    ScrollView scroller;
    FrameLayout superFather;
    boolean flag = false;
    float x1,x2;
    float y1, y2;
    int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notice_tab, container, false);
        scroller = (ScrollView) v.findViewById(R.id.superFather);
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
            notice.setText(HomeActivity.timeAlert);
        }
        updateData(HomeActivity.timeAlert);
        for(int i=0; i<Constant.TIMES.size(); i++){
            if(HomeActivity.timeAlert == Constant.TIMES.get(i)){
                count = i;
                break;
            }
        }
        scroller.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent touchevent) {
                switch (touchevent.getAction())
                {
                    // when user first touches the screen we get x and y coordinate
                    case MotionEvent.ACTION_DOWN:
                    {
                        x1 = touchevent.getX();
                        y1 = touchevent.getY();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        x2 = touchevent.getX();
                        y2 = touchevent.getY();

                        //if left to right sweep event on screen
                        if (x1 < x2 && (x2 - x1) > 20)
                        {

                            if( !HomeActivity.timeAlert.isEmpty() && count - 1 >= 0){
                                count -= 1;
                                String time = Constant.TIMES.get(count);
                                updateData(time);
                                notice.setText(time);
                            }
                        }

                        // if right to left sweep event on screen
                        if (x1 > x2 && (x1 - x2) > 20)
                        {
                            if(!HomeActivity.timeAlert.isEmpty() && count + 1 <= Constant.TIMES.size() - 1){
                                count += 1;
                                String time = Constant.TIMES.get(count);
                                updateData(time);
                                notice.setText(time);
                            }
                        }
                    }
                }
                return false;
            }
        });
        HomeActivity.hasNotify = false;
        return v;
    }

    public void updateData(String timeAlert){
        String temp = "";
        sections = new ArrayList<>();
        List<Treatment> treatments = Constant.TREATMENTS;
        for (Treatment treatment : treatments) {
            for (ToDoTime time : treatment.getListFoodTreatment()) {
                if (time.getNumberOfTime().contains(timeAlert)) {
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
        food.removeAllViews();
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
                if (time.getNumberOfTime().contains(timeAlert)) {
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
        medicine.removeAllViews();
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
                if (time.getNumberOfTime().contains(timeAlert)) {
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
        practice.removeAllViews();
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
    }

}
