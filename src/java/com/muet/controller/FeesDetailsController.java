/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.FeesDao;
import com.muet.dao.StudentDao;
import com.muet.dao.StudentFeesDao;
import com.muet.daoimpl.FeesDaoImpl;
import com.muet.daoimpl.StudentDaoImpl;
import com.muet.daoimpl.StudentFeesDaoImpl;
import com.muet.model.Fees;
import com.muet.model.Student;
import com.muet.model.StudentFees;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author khatr
 */
@MultipartConfig
public class FeesDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("--------------->" + action);
        if (null == action) {
            viewData(request, response);
        } else {
            switch (action) {
                case "":
                case "view":
                    viewData(request, response);
                    break;
                case "add":
                    addData(request, response);
                    break;
                case "getStudentFeesRecord":
                    getStudentFeesRecord(request, response);
                    break;
                case "update":
                    updateData(request, response);
                    break;
                case "delete":
                    deleteData(request, response);
                    break;
                case "addStudentFees":
                    addStudentFees(request, response);
                    break;
                case "updateFeesStatus":
                    updateFeesStatus(request, response);
                    break;
                case "updateStudentFees":
                    updateStudentFees(request, response);
                    break;
                default:
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void viewData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        List<StudentFees> studentFeeses = studentFeesDao.getStudentFee();
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(studentFeeses));
    }

    private void addData(HttpServletRequest request, HttpServletResponse response) {
        StudentFees studentFees = new StudentFees();
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentByRollNo(request.getParameter("studentRollNumber"));
        studentFees.setStudent(student);
        Fees fees = new Fees();
        fees.setFeesId(Integer.parseInt(request.getParameter("feesId")));
        studentFees.setFees(fees);
        studentFees.setDatePaid(request.getParameter("datePaid"));
        studentFees.setAmountPaid(Integer.parseInt(request.getParameter("amountPaid")));
        studentFees.setLateFees(Integer.parseInt(request.getParameter("lateFee")));
        studentFees.setChallanNumber(Integer.parseInt(request.getParameter("challanNumber")));
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        studentFeesDao.addStudentFees(studentFees);
    }

    private void getStudentFeesRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        StudentFees studentFees = studentFeesDao.getStudentFeesById(Integer.parseInt(request.getParameter("studentFeesId")));
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(studentFees));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) {
        StudentFees studentFees = new StudentFees();
        studentFees.setStudentFeesId(Integer.parseInt(request.getParameter("studentFeesId")));
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentByRollNo(request.getParameter("studentRollNumber"));
        studentFees.setStudent(student);
        Fees fees = new Fees();
        fees.setFeesId(Integer.parseInt(request.getParameter("feesId")));
        studentFees.setFees(fees);
        studentFees.setDatePaid(request.getParameter("datePaid"));
        studentFees.setAmountPaid(Integer.parseInt(request.getParameter("amountPaid")));
        studentFees.setLateFees(Integer.parseInt(request.getParameter("lateFee")));
        studentFees.setChallanNumber(Integer.parseInt(request.getParameter("challanNumber")));
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        studentFeesDao.updateStudentFees(studentFees);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("studentFeesId"));
        studentFeesDao.deleteStudentFees(id);
    }
    
    private void addStudentFees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("00010101001");
        StudentFees studentFees = new StudentFees();
        StudentDao studentDao = new StudentDaoImpl();
        System.out.println(request.getParameter("studentId"));
        Student student = studentDao.getStudentById(Integer.parseInt(request.getParameter("studentId")));
        studentFees.setStudent(student);
        System.out.println("Student ID: " + student.getStudentId());
        FeesDao feesDao = new FeesDaoImpl();
        Fees fees = feesDao.getFeesById(Integer.parseInt(request.getParameter("feesId")));
        studentFees.setFees(fees);
        System.out.println("Fees ID: " + fees.getFeesId());
        studentFees.setDatePaid(request.getParameter("datePaid"));
        studentFees.setAmountPaid(Integer.parseInt(request.getParameter("amountPaid")));
        studentFees.setLateFees(Integer.parseInt(request.getParameter("lateFees")));
        studentFees.setChallanNumber(Integer.parseInt(request.getParameter("challanNo")));
        System.out.println(studentFees.getStudent().getStudentId() +" " + studentFees.getDatePaid() + " " + studentFees.getChallanNumber());
        // file uploading
        Part part = request.getPart("challanImage");
        String fileName = part.getSubmittedFileName();
        String extension = part.getContentType();
        PrintWriter out = response.getWriter();
        out.println(fileName);
        System.out.println("----------> File Name: " + fileName);
        // Uploading profile image
        InputStream is = part.getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        String imageTitle = fees.getFeesTitle();
        if(fees.getFeesTitle().split(" ").length > 1) {
            String feesTitleSplit[] = fees.getFeesTitle().split(" ");
            imageTitle = feesTitleSplit[0];
            for(int i = 1; i < feesTitleSplit.length; i++) {
                imageTitle += "_" + feesTitleSplit[i];
            }
            
        }
//        String path = "uploads" + File.separator + "challanImages" + File.separator + student.getRollNumber() + "_"+imageTitle+"." + fileName.substring(fileName.lastIndexOf(".")+1);
        String path = "uploads" + File.separator + "challanImages" + File.separator + student.getRollNumber().trim() + "_"+imageTitle+"." + fileName.substring(fileName.lastIndexOf(".")+1);
        FileOutputStream fos = new FileOutputStream(request.getRealPath("/") + path);
        fos.write(data);
        fos.close();
        studentFees.setChallanImage(path);
        studentFees.setStatus("submitted");
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        studentFeesDao.addNewStudentFeesRecord(studentFees);
    }

    private void updateFeesStatus(HttpServletRequest request, HttpServletResponse response) {
        Integer studentFeesId = Integer.parseInt(request.getParameter("studentFeesId"));
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        StudentFees studentFees = studentFeesDao.getStudentFeesById(studentFeesId);
        studentFees.setStatus(request.getParameter("status"));
        studentFeesDao.updateStudentFees(studentFees);
    }

    private void updateStudentFees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("00010101001");
        StudentFees studentFees = new StudentFees();
        studentFees.setStudentFeesId(Integer.parseInt(request.getParameter("studentFeesId")));
        StudentDao studentDao = new StudentDaoImpl();
        System.out.println(request.getParameter("studentId"));
        Student student = studentDao.getStudentById(Integer.parseInt(request.getParameter("studentId")));
        studentFees.setStudent(student);
        System.out.println("Student ID: " + student.getStudentId());
        FeesDao feesDao = new FeesDaoImpl();
        Fees fees = feesDao.getFeesById(Integer.parseInt(request.getParameter("feesId")));
        studentFees.setFees(fees);
        System.out.println("Fees ID: " + fees.getFeesId());
        studentFees.setDatePaid(request.getParameter("datePaid"));
        studentFees.setAmountPaid(Integer.parseInt(request.getParameter("amountPaid")));
        studentFees.setLateFees(Integer.parseInt(request.getParameter("lateFees")));
        studentFees.setChallanNumber(Integer.parseInt(request.getParameter("challanNo")));
        System.out.println(studentFees.getStudent().getStudentId() +" " + studentFees.getDatePaid() + " " + studentFees.getChallanNumber());
        // file uploading
        Part part = request.getPart("challanImage");
        String fileName = part.getSubmittedFileName();
        PrintWriter out = response.getWriter();
        out.println(fileName);
        System.out.println("----------> File Name: " + fileName);
        // Uploading profile image
        InputStream is = part.getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        String imageTitle = fees.getFeesTitle();
        if(fees.getFeesTitle().split(" ").length > 1) {
            String feesTitleSplit[] = fees.getFeesTitle().split(" ");
            imageTitle = feesTitleSplit[0];
            for(int i = 1; i < feesTitleSplit.length; i++) {
                imageTitle += "_" + feesTitleSplit[i];
            }
            
        }
        String path = "uploads" + File.separator + "challanImages" + File.separator + student.getRollNumber().trim() + "_"+imageTitle+"." + fileName.substring(fileName.lastIndexOf(".")+1);
        FileOutputStream fos = new FileOutputStream(request.getRealPath("/") + path);
        fos.write(data);
        fos.close();
        studentFees.setChallanImage(path);
        studentFees.setStatus("submitted");
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        studentFeesDao.updateStudentFees(studentFees);
    }
   

}
