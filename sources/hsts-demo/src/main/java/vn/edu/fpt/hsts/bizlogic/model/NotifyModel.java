package vn.edu.fpt.hsts.bizlogic.model;

import java.io.Serializable;

/**
 * Created by QUYHKSE61160 on 10/7/15.
 */
public class NotifyModel implements Serializable {

    private int senderId;
    private int receiverId;
    private int type;
    private Byte status;

    public NotifyModel() {
    }

    public NotifyModel(int senderId, int receiverId, int type, Byte status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.status = status;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
