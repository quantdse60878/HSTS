package vn.edu.fpt.hsts.bizlogic.model;

import vn.edu.fpt.hsts.persistence.entity.FoodTreatment;
import vn.edu.fpt.hsts.persistence.entity.MedicineTreatment;
import vn.edu.fpt.hsts.persistence.entity.PracticeTreatment;

import java.util.List;

/**
 * Created by Aking on 11/6/2015.
 */
public class HisInforDateModel {
    private List<HisMedicine> hms;
    private List<HisFood> hfs;
    private List<HisPractice> hps;

    public HisInforDateModel() {
    }

    public HisInforDateModel(List<HisMedicine> hms, List<HisFood> hfs, List<HisPractice> hps) {
        this.hms = hms;
        this.hfs = hfs;
        this.hps = hps;
    }

    public List<HisMedicine> getHms() {
        return hms;
    }

    public void setHms(List<HisMedicine> hms) {
        this.hms = hms;
    }

    public List<HisFood> getHfs() {
        return hfs;
    }

    public void setHfs(List<HisFood> hfs) {
        this.hfs = hfs;
    }

    public List<HisPractice> getHps() {
        return hps;
    }

    public void setHps(List<HisPractice> hps) {
        this.hps = hps;
    }
}
