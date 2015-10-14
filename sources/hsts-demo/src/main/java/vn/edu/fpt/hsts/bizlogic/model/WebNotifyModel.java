/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WebNotifyModel implements Serializable {

    private String message;

    private String targetLink;

    public WebNotifyModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }

    public static List<WebNotifyModel> testData() {
        final List<WebNotifyModel> listData = new ArrayList<WebNotifyModel>();
        for(int i = 0; i< 5; i++) {
            WebNotifyModel model = new WebNotifyModel();
            model.setMessage("Messsage" + (i+1) );
            model.setTargetLink("/updatePatient?patientID="+ (i+1));
            listData.add(model);
        }
        return listData;
    }
}
