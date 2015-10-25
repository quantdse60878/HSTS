package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;

import java.lang.reflect.Field;
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

}
