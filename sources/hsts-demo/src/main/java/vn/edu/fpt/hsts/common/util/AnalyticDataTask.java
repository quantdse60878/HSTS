package vn.edu.fpt.hsts.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.*;
import vn.edu.fpt.hsts.persistence.repo.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */


@Component
public class AnalyticDataTask {


    public static boolean isFirst = true;
    //    X: Height
//    Y: Weight
//    Z: NumberofStep
//    K: Distance
    public static String FORMULA_CALCULATE_DISTANCE = "z * x * 0.414 / 100000";
    public static String FORMULA_CALCULATE_CALORIES = "y / 0.4536 * 0.53 * 1.609 * z * x * 0.414 / 100000";
    public static List<String> variable = new ArrayList<String>() {{
        add("z");
        add("x");
        add("y");
    }};
    public static List<String> valueVariable = new ArrayList<String>() {{
        add("2,NumberOfStep");
        add("1,Height");
        add("1,Weight");
    }};


    @Autowired
    MedicalRecordDataRepo medicalRecordDataRepo;
    @Autowired
    TreatmentRepo treatmentRepo;
    @Autowired
    PracticeTreatmentRepo practiceTreatmentRepo;
    @Autowired
    NotifyRepo notifyRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    PreventionCheckRepo preventionCheckRepo;
    @Autowired
    ParamMeasurementRepo paramMeasurementRepo;
    @Autowired
    PropertyRecordRepo propertyRecordRepo;

    @Scheduled(fixedRate = 1000 * 20)
    public void updatePatientData() {

        if(isFirst) {
            File file = new File(AnalyticDataTask.class.getClassLoader().getResource("formula.txt").getFile());
            BufferedReader br = null;

            try {

                String sCurrentLine;

                br = new BufferedReader(new FileReader(file));

                AnalyticDataTask.FORMULA_CALCULATE_DISTANCE = br.readLine();
                AnalyticDataTask.FORMULA_CALCULATE_CALORIES = br.readLine();
//                AnalyticDataTask.variable = new ArrayList<>();
//                AnalyticDataTask.valueVariable = new ArrayList<>();
                String tmp = "";
                AnalyticDataTask.valueVariable = new ArrayList<String>();
                AnalyticDataTask.variable = new ArrayList<String>();
                while ((tmp = br.readLine()) != null) {
                    String[] listData = tmp.split(",");
                    AnalyticDataTask.variable.add(listData[0]);
                    AnalyticDataTask.valueVariable.add(listData[1] + "," + listData[2]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            isFirst = false;
        }

        List<MedicalRecordData> listRecordData = medicalRecordDataRepo.findRecordDataNotUpdate();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        float distance = 0;
        int calories = 0;
        for (int i = 0; i < listRecordData.size(); i++) {
            MedicalRecordData recordData = listRecordData.get(i);
            PreventionCheck preventionCheck = preventionCheckRepo.findPreventionCheckByAppointmentId(recordData.getAppointment().getId());

            String formularDistance = FORMULA_CALCULATE_DISTANCE;
            String formularCalories = FORMULA_CALCULATE_CALORIES;
            for (int j = 0; j < variable.size(); j++) {
                String[] tableAndProperties = valueVariable.get(j).split(",");
                Method method;
                try {
                    if (tableAndProperties[0].equals("2")) {

                        ParamMeasurement paramMeasurement = paramMeasurementRepo.findParamMeasurementByMeasurementName(tableAndProperties[1]);
                        PropertyRecord propertyRecord = propertyRecordRepo.findPropertyRecordByMrdAndpm(recordData.getId(), paramMeasurement.getId());
//                        method = recordData.getClass().getMethod("get" + tableAndProperties[1]);
                        formularDistance = formularDistance.replace(variable.get(j), "" + propertyRecord.getParamMeasurementValue());
                        formularCalories = formularCalories.replace(variable.get(j), "" + propertyRecord.getParamMeasurementValue());
                    } else if (tableAndProperties[0].equals("1")) {
                        method = preventionCheck.getClass().getMethod("get" + tableAndProperties[1]);
                        formularDistance = formularDistance.replace(variable.get(j), "" + method.invoke(preventionCheck));
                        formularCalories = formularCalories.replace(variable.get(j), "" + method.invoke(preventionCheck));
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            try {
                distance = Float.parseFloat(engine.eval(formularDistance).toString());
                calories = (int) Double.parseDouble(engine.eval(formularCalories).toString());
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            System.out.println("1");
            if (distance > 0 && calories > 0) {

                ParamMeasurement paramDistance = paramMeasurementRepo.findParamMeasurementByMeasurementName("Distance");
                ParamMeasurement paramCalories = paramMeasurementRepo.findParamMeasurementByMeasurementName("Calories");
                PropertyRecord recordDistance = new PropertyRecord();
                PropertyRecord recordCalories = new PropertyRecord();

                recordDistance.setParamMeasurementValue(distance + "");
                recordDistance.setMedicalRecordData(recordData);
                recordDistance.setParamMeasurement(paramDistance);
                recordCalories.setParamMeasurementValue(calories + "");
                recordCalories.setMedicalRecordData(recordData);
                recordCalories.setParamMeasurement(paramCalories);
                propertyRecordRepo.save(recordDistance);
                propertyRecordRepo.save(recordCalories);
                recordData.setType(IDbConsts.IMedicalRecordDataType.CALCULATED);
            }

//            Map<String, Object> vars = new HashMap<String, Object>();


//            vars.put("x", preventionCheck.getHeight());
//            vars.put("z", recordData.getNumberOfStep());
//            try {
//                distance = Float.parseFloat(engine.eval(FORMULA_CALCULATE_DISTANCE, new SimpleBindings(vars)).toString());
//                vars = new HashMap<String, Object>();
//                vars.put("y", preventionCheck.getWeight());
//                vars.put("k", distance);
//                calories = (int) Double.parseDouble(engine.eval(FORMULA_CALCULATE_CALORIES, new SimpleBindings(vars)).toString());
//                if (distance > 0 && calories > 0) {
//                    recordData.setDistance(distance);
//                    recordData.setCalories(calories);
//                    recordData.setType(IDbConsts.IMedicalRecordDataType.CALCULATED);
//                }
//                System.out.println("----------");
//            } catch (ScriptException e) {
//                e.printStackTrace();
//            }

            Appointment appointment = recordData.getAppointment();
            Treatment treatment = treatmentRepo.findTreatmentByAppointmentId(appointment.getId());
            int ratioComplete = (calories * 100) / treatment.getCaloriesBurnEveryday();
            recordData.setRatioCompletePractice(ratioComplete);
            if (ratioComplete < 85) {
                MedicalRecord medicalRecord = appointment.getMedicalRecord();
                Account sender = medicalRecord.getDoctor().getAccount();
                Account receiverId = medicalRecord.getPatient().getAccount();
                Notify notify = new Notify();
                notify.setSender(sender);
                notify.setReceiver(receiverId);
                notify.setType((byte) 5);
                notify.setStatus((byte) 1);
                notifyRepo.save(notify);
            } else if (ratioComplete > 130) {
                MedicalRecord medicalRecord = appointment.getMedicalRecord();
                Account sender = medicalRecord.getDoctor().getAccount();
                Account receiverId = medicalRecord.getPatient().getAccount();
                Notify notify = new Notify();
                notify.setSender(sender);
                notify.setReceiver(receiverId);
                notify.setType((byte) 6);
                notify.setStatus((byte) 1);
                notifyRepo.save(notify);
            }
        }

        medicalRecordDataRepo.save(listRecordData);


    }

}
