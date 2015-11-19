package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.bizlogic.model.MedicineModel;
import vn.edu.fpt.hsts.bizlogic.model.MedicinePageModel;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.common.expception.BizlogicException;
import vn.edu.fpt.hsts.common.util.StringUtils;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.repo.MedicineRepo;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aking on 10/10/2015.
 */
@Service
public class MedicineService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineService.class);

    @Autowired
    private MedicineRepo medicineRepo;

    public List<Medicine> getAllMedicine(){
        return medicineRepo.findAll();
    }

    public List<String> findAllMedicine() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final List<String> medicineNames = medicineRepo.findAllByName();
            if (null != medicineNames) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Got {} records", medicineNames.size());
                }
            }
            return medicineNames;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public MedicinePageModel findMedicine(final String name, final int page, final int pageSize) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            LOGGER.info("name[{}], page[{}], pageSize[{}]", name, page, pageSize);
            Page<Medicine> pageEntities;
            final PageRequest pageRequest = new PageRequest(page, pageSize);
            if (StringUtils.isNotEmpty(name)) {
                String formatName = "%" + name + "%";
                pageEntities = medicineRepo.findByNameLike(formatName, pageRequest);
            } else {
                pageEntities = medicineRepo.findAll(pageRequest);
            }
            final MedicinePageModel pageModel = new MedicinePageModel(pageEntities);
            return pageModel;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public MedicineModel medicineDetail(final int medicineId) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Medicine medicine = medicineRepo.findOne(medicineId);
            final MedicineModel model = new MedicineModel();
            model.fromEntity(medicine);
            return model;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void createMedicine(final String name, final String unit) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Medicine medicine = new Medicine();
            medicine.setName(name);
            medicine.setUnit(unit);
            medicineRepo.saveAndFlush(medicine);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    @Transactional(rollbackOn = BizlogicException.class)
    public void updateMedicine(final int id, final String name, final String unit) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            final Medicine medicine = medicineRepo.findOne(id);
            medicine.setName(name);
            medicine.setUnit(unit);
            medicineRepo.saveAndFlush(medicine);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
