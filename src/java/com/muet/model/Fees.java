/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.model;

/**
 *
 * @author khatr
 */
public class Fees {
    private Integer feesId;
    private String feesTitle;
    private Integer amount;
    private String announcementDate;
    private String dueDate;
    
    public Fees() {}

    public Integer getFeesId() {
        return feesId;
    }

    public void setFeesId(Integer feesId) {
        this.feesId = feesId;
    }

    public String getFeesTitle() {
        return feesTitle;
    }

    public void setFeesTitle(String feesTitle) {
        this.feesTitle = feesTitle;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(String announcementDate) {
        this.announcementDate = announcementDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    
}
