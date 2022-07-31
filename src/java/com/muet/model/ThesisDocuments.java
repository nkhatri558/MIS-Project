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
public class ThesisDocuments {
    private Integer thesisDocumentId;
    private String thesisDocumentTitle;
    private ThesisDetails thesis;
    private StudentThesis studentThesis;
    private String thesisDocument;

    public String getThesisDocument() {
        return thesisDocument;
    }

    public void setThesisDocument(String thesisDocument) {
        this.thesisDocument = thesisDocument;
    }

    public StudentThesis getStudentThesis() {
        return studentThesis;
    }

    public void setStudentThesis(StudentThesis studentThesis) {
        this.studentThesis = studentThesis;
    }
    
    public ThesisDocuments() {}

    public Integer getThesisDocumentId() {
        return thesisDocumentId;
    }

    public void setThesisDocumentId(Integer thesisDocumentId) {
        this.thesisDocumentId = thesisDocumentId;
    }

    public String getThesisDocumentTitle() {
        return thesisDocumentTitle;
    }

    public void setThesisDocumentTitle(String thesisDocumentTitle) {
        this.thesisDocumentTitle = thesisDocumentTitle;
    }

    public ThesisDetails getThesis() {
        return thesis;
    }

    public void setThesis(ThesisDetails thesis) {
        this.thesis = thesis;
    }
    
    
    
}
