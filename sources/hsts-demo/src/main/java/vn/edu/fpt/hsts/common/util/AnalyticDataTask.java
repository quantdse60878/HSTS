package vn.edu.fpt.hsts.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */


@Component
public class AnalyticDataTask {

    @Autowired
    MedicalRecordDataRepo medicalRecordDataRepo;

    @Scheduled(fixedRate = 1000*20)
    public void updatePatientData() {
        List<MedicalRecordData> listRecordData = medicalRecordDataRepo.findRecordDataNotUpdate();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        float distance = 0;
        int calories = 0;
        for(int i = 0; i < listRecordData.size(); i++) {
            MedicalRecordData recordData = listRecordData.get(i);
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("x", recordData.getAppointment().getHeight());
            vars.put("z", recordData.getNumberOfStep());
            try {
                distance = Float.parseFloat(engine.eval(IConsts.FORMULA_CALCULATE_DISTANCE, new SimpleBindings(vars)).toString());
                vars = new HashMap<String, Object>();
                vars.put("y", recordData.getAppointment().getWeight());
                vars.put("k", distance);
                double caloriesDB = Double.parseDouble(engine.eval(IConsts.FORMULA_CALCULATE_CALORIES, new SimpleBindings(vars)).toString());
                calories = (int) caloriesDB;
                if(distance > 0 && calories > 0) {
                    recordData.setDistance(distance);
                    recordData.setCalories(calories);
                    recordData.setType(IDbConsts.IMedicalRecordDataType.CALCULATED);
                }
                System.out.println("----------");
            } catch (ScriptException e) {
                e.printStackTrace();
            }

        }

        medicalRecordDataRepo.save(listRecordData);



    }

}
