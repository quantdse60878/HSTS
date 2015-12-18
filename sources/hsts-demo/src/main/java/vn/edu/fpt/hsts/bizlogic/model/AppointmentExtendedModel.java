/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 12/18/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.Appointment;

public class AppointmentExtendedModel extends AbstractKeyModel<Appointment> {
    @Override
    protected Class<Appointment> getEntityClass() {
        return Appointment.class;
    }

    /**
     *
     */
    private String meetingDate;

    /**
     *
     */
    private byte status;


    /**
     *
     */
    private PreventionCheckModel preventionCheck;

    /**
     *
     */
    private PracticeResultModel practiceResult;

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public AppointmentExtendedModel() {
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public PreventionCheckModel getPreventionCheck() {
        return preventionCheck;
    }

    public void setPreventionCheck(PreventionCheckModel preventionCheck) {
        this.preventionCheck = preventionCheck;
    }

    public PracticeResultModel getPracticeResult() {
        return practiceResult;
    }

    public void setPracticeResult(PracticeResultModel practiceResult) {
        this.practiceResult = practiceResult;
    }

    @Override
    public void fromEntity(Appointment entity) {
        super.fromEntity(entity);
        meetingDate = DateUtils.formatDate(entity.getMeetingDate(), DateUtils.DATE_PATTERN_3);
        status = entity.getStatus();
        preventionCheck = new PreventionCheckModel();
        preventionCheck.fromEntity(entity.getPreventionCheck());
    }
}
