package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.util.AnalyticDataTask;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/25/15.
 */
@Service
public class FormulaService {


    private static final Logger LOGGER = LoggerFactory.getLogger(FormulaService.class);

    public List<String> getListFieldOfPreventionCheck() {
        List<String> result = new ArrayList<String>();
        for(Field f : PreventionCheck.class.getDeclaredFields()) {
            result.add(f.getName());
        }
        return result;
    }
    public List<String> getListFieldOfMedicalRecordData() {
        List<String> result = new ArrayList<String>();
        for(Field f : MedicalRecordData.class.getDeclaredFields()) {
            result.add(f.getName());
        }
        return result;
    }
    public void saveNewFormula() {
        File file = new File(FormulaService.class.getClassLoader().getResource("formula.txt").getFile());
        String content = AnalyticDataTask.FORMULA_CALCULATE_DISTANCE + "\n" +
                        AnalyticDataTask.FORMULA_CALCULATE_CALORIES + "\n";
        for(int i = 0; i < AnalyticDataTask.variable.size(); i++) {
            content = content + AnalyticDataTask.variable.get(i) + "," +
                                AnalyticDataTask.valueVariable.get(i) + "\n";
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        System.out.println("Done");
    }

}
