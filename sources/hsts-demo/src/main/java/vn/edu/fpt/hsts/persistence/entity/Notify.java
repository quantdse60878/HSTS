/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/6/2015.
 */
package vn.edu.fpt.hsts.persistence.entity;

import vn.edu.fpt.hsts.common.jpa.AbstractKeyEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Notify extends AbstractKeyEntity{

    /**
     *
     */
    @ManyToOne()
    @JoinColumn(name = "senderId", nullable = false)
    private Account sender;

    /**
     *
     */
    @ManyToOne()
    @JoinColumn(name = "receiverId", nullable = false)
    private Account receiver;

    /**
     *
     */
    private byte type;

    /**
     *
     */
    private byte status;

    public Notify() {
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(final Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(final Account receiver) {
        this.receiver = receiver;
    }

    public byte getType() {
        return type;
    }

    public void setType(final byte type) {
        this.type = type;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(final byte status) {
        this.status = status;
    }
}
