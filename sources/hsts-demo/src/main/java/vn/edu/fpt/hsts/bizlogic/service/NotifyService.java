package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.WebNotifyModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.persistence.IDbConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;
import vn.edu.fpt.hsts.persistence.entity.Notify;
import vn.edu.fpt.hsts.persistence.entity.Patient;
import vn.edu.fpt.hsts.persistence.repo.MedicalRecordRepo;
import vn.edu.fpt.hsts.persistence.repo.NotifyRepo;
import vn.edu.fpt.hsts.persistence.repo.PatientRepo;
import vn.edu.fpt.hsts.web.session.UserSession;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
@Service
public class NotifyService extends AbstractService {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyService.class);

    @Autowired
    private NotifyRepo notifyRepo;

    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    /**
     * The {@link PatientRepo}.
     */
    @Autowired
    private PatientRepo patientRepo;

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


    public List<WebNotifyModel> findNotificationData() throws BizlogicException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {

            final UserSession userSession = getUserSession();
            if (null == userSession) {
               throw new BizlogicException("UserSession is null or empty");
            }

            // Find all notification with receiverId = current session user
            final List<Notify> notifications = notifyRepo.findByReceiverId(userSession.getId(), IDbConsts.INotifyStatus.UNCOMPLETED);
            final List<WebNotifyModel> dataList = new ArrayList<WebNotifyModel>();
            if (null != notifications && !notifications.isEmpty()) {
                for (Notify n: notifications) {
                    final byte type = n.getType();
                    final WebNotifyModel model = new WebNotifyModel();
                    String message = null, targetLink = null;
                    switch (type) {
                        case IDbConsts.INotifyType.NURSE_DOCTOR:

                            // Check the notify message is related with a patient id or not?
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.debug("message[{}]", n.getMessage());
                            }
                            final int patientId = Integer.parseInt(n.getMessage());
                            Patient patient = patientRepo.findOne(patientId);
                            if (null == patient) {
                                LOGGER.error("Patient with id[{}] is not found", patientId);
                                throw new BizlogicException("Patient with id[{}] is not found", null, patientId);
                            }

                            // Make data
                            message = String.format("New appointment with patient %s today", patient.getAccount().getFullName());
                            targetLink = "/createPrescription?patientID=" + patient.getId() + "&notificationId=" + n.getId();
                            break;

                        case IDbConsts.INotifyType.PATIENT_DOCTOR:

                            // Check the patient existence?
                            final Account sender = n.getSender();
                            if(LOGGER.isDebugEnabled()) {
                                LOGGER.debug("senderId[{}], senderAccount[{}]", sender.getId(), sender.getUsername());
                            }
                            // Find related patient
                            Patient senderPatient = patientRepo.findByAccountId(sender.getId());
                            if (null == senderPatient) {
                                LOGGER.error("Patient with accountId[{}] is not found", sender.getId());
                                throw new BizlogicException("Patient with accountId[{}] is not found", null, sender.getId());
                            }

                            // TODO map targetLink with a static variable for avoid changes
                            message = String.format("Patient %s sent a message: %s", sender.getFullName(), n.getMessage());
                            targetLink = "/createPrescription?patientID=" + senderPatient.getId() + "&notificationId=" + n.getId();
                            break;

                        default:
                            break;
                    }
                    model.setMessage(message);
                    model.setTargetLink(targetLink);
                    dataList.add(model);
                }
            }
            return dataList;
        } catch (BizlogicException be) {
            throw be;
        } catch (Exception e) {
            throw new BizlogicException("Error while find notification data");
        }
        finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void markAllNotificationAsReaded() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("Marking all notification to user[{}] as readed", getUserSession().getUsername());
            final List<Notify> notifications = notifyRepo.findByReceiverId(getUserSession().getId(), IDbConsts.INotifyStatus.UNCOMPLETED);
            if (null != notifications && !notifications.isEmpty()) {
                for (Notify n: notifications) {
                    n.setStatus(IDbConsts.INotifyStatus.COMPLETED);
                    notifyRepo.save(n);
                }
            }
            notifyRepo.flush();
            LOGGER.info("Finished");
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void markAsRead(final int notificationId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("notification[{}]", notificationId);
            Notify notify = notifyRepo.findOne(notificationId);
            notify.setStatus(IDbConsts.INotifyStatus.COMPLETED);
            notifyRepo.saveAndFlush(notify);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

}
