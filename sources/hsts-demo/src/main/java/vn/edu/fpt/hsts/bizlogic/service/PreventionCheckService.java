package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Appointment;
import vn.edu.fpt.hsts.persistence.entity.PreventionCheck;
import vn.edu.fpt.hsts.persistence.repo.PreventionCheckRepo;

import java.util.List;

/**
 * Created by Aking on 10/22/2015.
 */
@Service
public class PreventionCheckService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreventionCheckService.class);

    @Autowired
    private PreventionCheckRepo preventionCheckRepo;

    public PreventionCheck findLastPreventionCheckFromAppointment(final Appointment appointment){
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("appointment[{}]", appointment);
            List<PreventionCheck> preventionChecks = preventionCheckRepo.findPreventionCheckByAppointment(appointment);
            if (null != preventionChecks && !preventionChecks.isEmpty()){
                return preventionChecks.get(0);
            }
            return null;
        }finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
