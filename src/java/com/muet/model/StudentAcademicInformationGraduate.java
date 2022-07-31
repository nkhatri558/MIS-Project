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
public class StudentAcademicInformationGraduate {
    private Integer academicInformationGraduateId;
    private Student student;
    private String passingYear;
    private String university;
    private Double cgpa;
    private String degreeProgram;
    private String graduateType;
    private String marksheetImage;
    private String certificateImage;
    
    public StudentAcademicInformationGraduate() {}

    public Integer getAcademicInformationGraduateId() {
        return academicInformationGraduateId;
    }

    public void setAcademicInformationGraduateId(Integer academicInformationGraduateId) {
        this.academicInformationGraduateId = academicInformationGraduateId;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    public String getGraduateType() {
        return graduateType;
    }

    public void setGraduateType(String graduateType) {
        this.graduateType = graduateType;
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
