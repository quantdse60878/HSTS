package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Notify;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.NotifyRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Service
public class NotifyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyService.class);
    @Autowired
    private NotifyRepo notifyRepo;
    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    public List<Notify> getNotifyByReceiverId(final int receiverId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        List<Notify> notifyReceiver = new ArrayList<Notify>();
        final List<Notify> allNotify = notifyRepo.findAll();
        for(int i = 0; i < allNotify.size(); i++) {
            Notify notifyItem = allNotify.get(i);
            LOGGER.info("-----------------ReceiverId: " + receiverId + "--Receiver: " + notifyItem.getReceiver().getId() + "--Status: " + notifyItem.getStatus());
            if((notifyItem.getReceiver().getId() == receiverId) && (notifyItem.getStatus() == IDbConsts.INotifyStatus.UNCOMPLETED)) {
                notifyReceiver.add(notifyItem);
            }
        }
        LOGGER.info(IConsts.END_METHOD);
        return notifyReceiver;
    }

    public boolean hadGetTreatment(final int notifyId) {
        final Notify notify = notifyRepo.findOne(notifyId);
        notify.setStatus((byte) IDbConsts.INotifyStatus.COMPLETED);
        notifyRepo.save(notify);
        return true;
    }

    public boolean sendNotifyToDoctor(final int patientId, final String message) {

        List<MedicalRecord> listMedicalReport = medicalRecordRepo.findMedicalRecordByPatientId(patientId);
        for(int i = 0; i < listMedicalReport.size(); i++) {
            MedicalRecord  medicalRecord = listMedicalReport.get(i);
            Account senderId = medicalRecord.getPatient().getAccount();
            Account receiverId = medicalRecord.getDoctor().getAccount();
            Notify notify = new Notify();
            notify.setSender(senderId);
            notify.setReceiver(receiverId);
            notify.setMessage(message);
            notify.setStatus((byte) 1);
            notify.setType((byte) 1);
            notifyRepo.save(notify);
        }


        System.out.println("!!!!");
        return true;
    }



}
