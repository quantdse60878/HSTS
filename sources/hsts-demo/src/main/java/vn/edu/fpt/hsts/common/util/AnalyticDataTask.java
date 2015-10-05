package vn.edu.fpt.hsts.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecordData;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordDataRepo;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by QUYHKSE61160 on 10/5/15.
 */


@Service
public class AnalyticDataTask extends TimerTask {

    @Autowired
    private MedicalRecordDataRepo medicalRecordDataRepo;

    @Override
    public void run() {
        final List<MedicalRecordData> listRecordData = medicalRecordDataRepo.findAll();
        for(int i = 0; i < listRecordData.size(); i++) {
            MedicalRecordData recordData = listRecordData.get(i);
//            adfasdfasdfasdf
        }
    }
}
