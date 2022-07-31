/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.model;

/**
 *
 * @author 92310
 */
public class DashboardChart {
    
    private Integer seminarApproved;
    private Integer seminarRejected;
    private Integer feesPaidCount;
    private Integer feesUnPaidCount;
    private Integer registeredStudentsCount;
    private Integer unRegisteredStudentsCount;
    private Integer maleCount;
    private Integer femaleCount;

    public Integer getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(Integer maleCount) {
        this.maleCount = maleCount;
    }

    public Integer getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(Integer femaleCount) {
        this.femaleCount = femaleCount;
    }
    public Integer getSeminarApproved() {
        return seminarApproved;
    }

    public void setSeminarApproved(Integer seminarApproved) {
        this.seminarApproved = seminarApproved;
    }

    public Integer getSeminarRejected() {
        return seminarRejected;
    }

    public void setSeminarRejected(Integer seminarRejected) {
        this.seminarRejected = seminarRejected;
    }

    public Integer getFeesPaidCount() {
        return feesPaidCount;
    }

    public void setFeesPaidCount(Integer feesPaidCount) {
        this.feesPaidCount = feesPaidCount;
    }

    public Integer getFeesUnPaidCount() {
        return feesUnPaidCount;
    }

    public void setFeesUnPaidCount(Integer feesUnPaidCount) {
        this.feesUnPaidCount = feesUnPaidCount;
    }

    public Integer getRegisteredStudentsCount() {
        return registeredStudentsCount;
    }

    public void setRegisteredStudentsCount(Integer registeredStudentsCount) {
        this.registeredStudentsCount = registeredStudentsCount;
    }

    public Integer getUnRegisteredStudentsCount() {
        return unRegisteredStudentsCount;
    }

    public void setUnRegisteredStudentsCount(Integer unRegisteredStudentsCount) {
        this.unRegisteredStudentsCount = unRegisteredStudentsCount;
    }
}
