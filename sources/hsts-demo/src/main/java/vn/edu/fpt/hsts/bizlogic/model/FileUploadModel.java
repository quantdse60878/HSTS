/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/5/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

public class FileUploadModel implements Serializable {

    /**
     *
     */
    private boolean result = false;
    /**
     *
     */
    private String fileName;

    /**
     *
     */
    private String filePath;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(final boolean result) {
        this.result = result;
    }

    public FileUploadModel(final boolean result) {
        this.result = result;
    }

    public FileUploadModel() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }
}
