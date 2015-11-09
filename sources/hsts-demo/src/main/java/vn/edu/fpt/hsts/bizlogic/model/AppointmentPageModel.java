package vn.edu.fpt.hsts.bizlogic.model;

import org.springframework.data.domain.Page;
import vn.edu.fpt.hsts.persistence.entity.Appointment;

/**
 * Created by Aking on 11/9/2015.
 */
public class AppointmentPageModel extends AbstractPageModel<Appointment, AppointmentModel> {
    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param pageEntities {@link Page}
     */
    public AppointmentPageModel(Page<Appointment> pageEntities) {
        super(pageEntities);
    }

    @Override
    protected Class<AppointmentModel> getModelClass() {
        return AppointmentModel.class;
    }
}
