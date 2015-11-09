package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.common.util.DateUtils;
import vn.edu.fpt.hsts.persistence.entity.Appointment;

/**
 * Created by Aking on 11/9/2015.
 */
public class AppointmentModel extends AbstractKeyModel<Appointment> {
    @Override
    protected Class<Appointment> getEntityClass() {
        return Appointment.class;
    }


    private String meetingDate;

    public AppointmentModel() {
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public void fromEntity(Appointment entity) {
        super.fromEntity(entity);
        meetingDate = DateUtils.formatDate(entity.getMeetingDate(), DateUtils.DATE_PATTERN_3);
    }
}
