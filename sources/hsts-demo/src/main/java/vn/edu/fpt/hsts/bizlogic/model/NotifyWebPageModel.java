/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/14/2015.
 */
package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;
import java.util.List;

public class NotifyWebPageModel implements Serializable {

    private int size;

    private List<WebNotifyModel> data;

    public NotifyWebPageModel() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<WebNotifyModel> getData() {
        return data;
    }

    public void setData(List<WebNotifyModel> data) {
        this.data = data;
    }
}
