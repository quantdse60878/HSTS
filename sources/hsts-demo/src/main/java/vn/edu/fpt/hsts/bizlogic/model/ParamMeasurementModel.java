package vn.edu.fpt.hsts.bizlogic.model;


import vn.edu.fpt.hsts.persistence.entity.ParamMeasurement;

/**
 * Created by Man Huynh Khuong on 10/29/2015.
 */
public class ParamMeasurementModel extends AbstractKeyModel<ParamMeasurement> {
    @Override
    protected Class<ParamMeasurement> getEntityClass() {
        return ParamMeasurement.class;
    }

    private String measurementName;
    private String measurementType;
    private String uuid;
    private int positionHaveValue;

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

    @Override
    public void fromEntity(ParamMeasurement entity) {
        super.fromEntity(entity);
        setMeasurementName(entity.getMeasurementName());
        setMeasurementType(entity.getMeasurementType());
        setPositionHaveValue(entity.getPositionHaveValue());
        setUuid(entity.getUuid());
    }
}
