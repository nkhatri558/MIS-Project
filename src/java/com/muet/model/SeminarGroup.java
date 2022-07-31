/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.model;

import java.util.Date;

/**
 *
 * @author 92310
 */
public class SeminarGroup {
    private Integer seminarGroupId;
    private Seminar seminar;
    private ThesisDetails thesis;
    private Date dateApplied;
    private Date dateConducted;
    private String remarks;
    private String comments;
    private String status ;
    private Student student;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSeminarGroupId() {
        return seminarGroupId;
    }

    public void setSeminarGroupId(Integer seminarGroupId) {
        this.seminarGroupId = seminarGroupId;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public Date getDateConducted() {
        return dateConducted;
    }

    public void setDateConducted(Date dateConducted) {
        this.dateConducted = dateConducted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ThesisDetails getThesis() {
        return thesis;
    }

    public void setThesis(ThesisDetails thesis) {
        this.thesis = thesis;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
}
