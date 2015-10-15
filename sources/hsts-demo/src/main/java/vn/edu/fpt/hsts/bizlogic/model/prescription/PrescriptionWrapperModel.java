/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/15/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model.prescription;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import vn.edu.fpt.hsts.common.IConsts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrescriptionWrapperModel {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionWrapperModel.class);

    /**
     * The template resource.
     */
    private static final String TEMPLATE = "jasper/prescription.jasper";

    /**
     * Logo.
     */
    private static final String LOGO = "static/image/FPT_Logo.png";

    /**
     *
     */
    private String patientName;

    /**
     *
     */
    private String birthday;

    /**
     *
     */
    private String diagnose;

    /**
     *
     */
    private String advice;

    /**
     *
     */
    private String appointmentDate;

    /**
     *
     */
    private String address;

    /**
     *
     */
    private String doctorName;

    /**
     *
     */
    private MedicineListWraper tableData;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public MedicineListWraper getTableData() {
        return tableData;
    }

    public void setTableData(MedicineListWraper tableData) {
        this.tableData = tableData;
    }

    private Map<String, Object> getParameters() throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // LOGO
        InputStream is = resolver.getResource(LOGO).getInputStream();
        try {
            BufferedImage logo = ImageIO.read(is);
            result.put("logo", logo);
        } catch (IOException ex) {
            result.put("logo", null);
            LOGGER.error("Logo not found");
        }

        result.put("patientName", this.patientName);
        result.put("birthday", birthday);
        result.put("diagnose", this.diagnose);
        result.put("advice", advice);
        result.put("birthday", birthday);
        result.put("appointmentDate", appointmentDate);
        result.put("address", address);
        result.put("doctorName", doctorName);
        return result;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void toPdf(OutputStream out) throws JRException, IOException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            // Prepare template file path
            InputStream is = resolver.getResource(TEMPLATE).getInputStream();

            List<MedicineListWraper> data = new ArrayList<MedicineListWraper>();
            if(null != tableData) {
                data.add(this.tableData);
            } else {
                data.add(new MedicineListWraper());
            }

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(data);

            // Report filled object
            JasperPrint report = JasperFillManager.fillReport(is, getParameters(), dataSource);

            /**
             * TODO
             * Export to *.docx = OK
             * Export to *.pdf = ??? encoding
             */
            JRExporter exporter = new JRDocxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, report);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
