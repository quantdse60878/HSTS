package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by QUYHKSE61160 on 10/27/15.
 */
@Entity
public class ParamMeasurement extends AbstractKeyEntity {

    private String measurementName;
    private String measurementType;
    private int measurementMinRange;
    private int measurementMaxRange;
    private byte type;
    private String uuid;
    private int positionHaveValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceId", nullable = false)
    private Device device;

    public ParamMeasurement() {
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPositionHaveValue() {
        return positionHaveValue;
    }

    public void setPositionHaveValue(int positionHaveValue) {
        this.positionHaveValue = positionHaveValue;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public int getMeasurementMinRange() {
        return measurementMinRange;
    }

    public void setMeasurementMinRange(int measurementMinRange) {
        this.measurementMinRange = measurementMinRange;
    }

    public int getMeasurementMaxRange() {
        return measurementMaxRange;
    }

    public void setMeasurementMaxRange(int measurementMaxRange) {
        this.measurementMaxRange = measurementMaxRange;
    }
}
