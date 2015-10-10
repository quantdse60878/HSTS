package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.persistence.entity.Medicine;
import vn.edu.fpt.hsts.persistence.repo.MedicineRepo;

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
}
