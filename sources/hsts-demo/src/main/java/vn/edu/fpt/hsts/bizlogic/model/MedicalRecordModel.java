/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/17/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.MedicalRecord;

public class MedicalRecordModel extends AbstractKeyModel<MedicalRecord> {

    @Override
    protected Class<MedicalRecord> getEntityClass() {
        return MedicalRecord.class;
    }

    /**
     * The doctor.
     */
    private DoctorModel doctor;

    /**
     *
     */
    private IllnessModel illness;

    /**
     *
     */
    private String startTime;

    /**
     *
     */
    private String endTime;

    /**
     * The symptoms.
     */
    private String symptoms;

    /**
     * The status.
     */
    private byte status;

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public IllnessModel getIllness() {
        return illness;
    }

    public void setIllness(IllnessModel illness) {
        this.illness = illness;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public void fromEntity(MedicalRecord entity) {
        super.fromEntity(entity);
        doctor = new DoctorModel();
        doctor.fromEntity(entity.getDoctor());
        illness = new IllnessModel();
        setShortModel(entity.getIllness(), illness);
        status = entity.getStatus();
        startTime = DateUtils.formatDate(entity.getStartTime(), DateUtils.DATE_PATTERN_3);
        if (null != entity.getEndTime()) {
            endTime = DateUtils.formatDate(entity.getEndTime(), DateUtils.DATE_PATTERN_3);
        }
        symptoms = entity.getSymptoms();
    }
}
