package vn.edu.fpt.hsts.persistence.entity;

import org.dom4j.tree.AbstractEntity;
import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by QUYHKSE61160 on 10/27/15.
 */
@Entity
public class PropertyRecord extends AbstractKeyEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicalRecordDataId", nullable = false)
    private MedicalRecordData medicalRecordData;

    @ManyToOne
    @JoinColumn(name = "paramMeasurementId", nullable = false)
    private ParamMeasurement paramMeasurement;

    private String paramMeasurementValue;

    public PropertyRecord() {
    }

    public MedicalRecordData getMedicalRecordData() {
        return medicalRecordData;
    }

    public void setMedicalRecordData(MedicalRecordData medicalRecordData) {
        this.medicalRecordData = medicalRecordData;
    }

    public ParamMeasurement getParamMeasurement() {
        return paramMeasurement;
    }

    public void setParamMeasurement(ParamMeasurement paramMeasurement) {
        this.paramMeasurement = paramMeasurement;
    }

    public String getParamMeasurementValue() {
        return paramMeasurementValue;
    }

    public void setParamMeasurementValue(String paramMeasurementValue) {
        this.paramMeasurementValue = paramMeasurementValue;
    }
}
