/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.FeesDao;
import com.muet.dao.StudentFeesDao;
import com.muet.daoimpl.FeesDaoImpl;
import com.muet.daoimpl.StudentFeesDaoImpl;
import com.muet.model.Fees;
import com.muet.model.StudentFees;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khatr
 */
public class FeesController extends HttpServlet {

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
                case "getFeesRecord":
                    getFeesRecord(request, response);
                    break;
                case "update":
                    updateData(request, response);
                    break;
                case "delete":
                    deleteData(request, response);
                    break;
                case "showStudentFeeRecords":
                    showStudentFeeRecords(request, response);
                    break;
                case "getFeesListByStudentId":
                    getFeesListByStudentId(request, response);
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
        FeesDao feesDao = new FeesDaoImpl();
        List<Fees> feeses = feesDao.getFees();
        PrintWriter pw = response.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(feeses));
    }

    private void addData(HttpServletRequest request, HttpServletResponse response) {
        Fees fees = new Fees();
        fees.setFeesTitle(request.getParameter("feesTitle"));
        fees.setAmount(Integer.parseInt(request.getParameter("amount")));
        fees.setAnnouncementDate(request.getParameter("announcementDate"));
        fees.setDueDate(request.getParameter("dueDate"));
        FeesDao feesDao = new FeesDaoImpl();
        feesDao.addFees(fees);
    }

    private void getFeesRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("feesId"));
        FeesDao feesDao = new FeesDaoImpl();
        Fees fees = feesDao.getFeesById(id);
        PrintWriter pw = response.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(fees));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) {
        Fees fees = new Fees();
        fees.setFeesTitle(request.getParameter("feesTitle"));
        fees.setAmount(Integer.parseInt(request.getParameter("amount")));
        fees.setAnnouncementDate(request.getParameter("announcementDate"));
        fees.setDueDate(request.getParameter("dueDate"));
        fees.setFeesId(Integer.parseInt(request.getParameter("feesId")));
        FeesDao feesDao = new FeesDaoImpl();
        feesDao.updateFees(fees);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("feesId"));
        FeesDao feesDao = new FeesDaoImpl();
        feesDao.deleteFees(id);
    }

    private void showStudentFeeRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("application/json");
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        StudentFeesDao studentFeesDao = new StudentFeesDaoImpl();
        List<StudentFees> list = studentFeesDao.getFeesByStudentId(studentId);
        PrintWriter pw = response.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(list));
    }

    private void getFeesListByStudentId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        FeesDao feesDao = new FeesDaoImpl();
        List<Fees> feesList = feesDao.getFeesListByStudentId(studentId);
        PrintWriter pw = response.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(feesList));
    }

}
