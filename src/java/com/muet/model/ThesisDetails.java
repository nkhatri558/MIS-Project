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
public class ThesisDetails {
    private Integer thesisDetailsId;
    private Thesis thesis;
    private String thesisExamDate;
    private Integer finalResults;
    private String remarks;
    private Student student;
    private String status;
    private String thesisTitle;

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getThesisDetailsId() {
        return thesisDetailsId;
    }

    public void setThesisDetailsId(Integer thesisDetailsId) {
        this.thesisDetailsId = thesisDetailsId;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public String getThesisExamDate() {
        return thesisExamDate;
    }

    public void setThesisExamDate(String thesisExamDate) {
        this.thesisExamDate = thesisExamDate;
    }

    public Integer getFinalResults() {
        return finalResults;
    }

    public void setFinalResults(Integer finalResults) {
        this.finalResults = finalResults;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
   
}
