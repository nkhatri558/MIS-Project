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
public class StudentAcademicInformationBoard {
    private Integer academicInformationBoardId;
    private Student student;
    private String passingYear;
    private String board;
    private Double marks;
    private String subject;
    private String academicType;
    private String marksheetImage;
    private String certificateImage;
    
    public StudentAcademicInformationBoard () {}

    public Integer getAcademicInformationBoardId() {
        return academicInformationBoardId;
    }

    public void setAcademicInformationBoardId(Integer academicInformationBoardId) {
        this.academicInformationBoardId = academicInformationBoardId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAcademicType() {
        return academicType;
    }

    public void setAcademicType(String academicType) {
        this.academicType = academicType;
    }
    
    public String getMarksheetImage() {
        return marksheetImage;
    }

    public void setMarksheetImage(String marksheetImage) {
        this.marksheetImage = marksheetImage;
    }

    public String getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(String certificateImage) {
        this.certificateImage = certificateImage;
    }
    
    
}
