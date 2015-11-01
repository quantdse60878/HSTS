/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/1/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;

@Service
public class BarcodeService extends AbstractService {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BarcodeService.class);

    /**
     * The barcode prefix.
     */
    public static final String PATIENT_BARCODE_PREFIX = "404";

    /**
     *
     */
    private static final int PATIENT_BARCODE_PREFIX_LENGHT = 4;

    /**
     * static const.
     */
    private static final int TEN = 10;

    /**
     * static const.
     */
    private static final int THREE = 3;

    /**
     * Common method to gen check digit for a barcode.
     * @param uncheckBarcode String
     * @return String
     */
    public static String genCheckDigit(final String uncheckBarcode) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            String checkDigit  = "";
            int sum = 0;
            int len = uncheckBarcode.length();
            for (int i = 1; i <= len; i++) {
                int cal = 0;
                if (i % 2 != 0) {
                    cal = Integer.parseInt(uncheckBarcode.charAt(len - i) + "") * THREE;
                } else {
                    cal =  Integer.parseInt(uncheckBarcode.charAt(len - i) + "");
                }
                sum += cal;
            }
            if (sum % TEN > 0) {
                checkDigit += (TEN - sum % TEN);
            } else {
                checkDigit = "0";
            }
            return checkDigit;
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    /**
     *
     * <p>
     * convert string to string number.
     * </p>
     * @param number int
     * @param strLength int
     * @return {@link String} string
     */
    public static String convertNum(final int number, final int strLength) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            String result = "";
            String zero = "";
            int add = 0;
            result += number;
            add = strLength - result.length();
            for (int i = 0; i < add; i++) {
                zero += "0";
            }
            result = zero + result;
            return result;
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }

    /**
     * Generate a patient barcode.
     * @param patientId
     * @return
     */
    public String getPatientBarcode(final int patientId) {
        LOGGER.debug(IConsts.BEGIN_METHOD);
        try {
            final StringBuilder sbBarcode = new StringBuilder(PATIENT_BARCODE_PREFIX);
            sbBarcode.append(BarcodeService.convertNum(patientId, PATIENT_BARCODE_PREFIX_LENGHT));
            sbBarcode.append(genCheckDigit(sbBarcode.toString()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("patientId[{}], barcode[{}]", patientId, sbBarcode);
            }
            return sbBarcode.toString();
        } finally {
            LOGGER.debug(IConsts.END_METHOD);
        }
    }
}
