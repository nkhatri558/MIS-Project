/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.StudentDao;
import com.muet.daoimpl.StudentDaoImpl;
import com.muet.daoimpl.ThesisDetailsDaoImpl;
import com.muet.model.Student;
import com.muet.model.ThesisDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.muet.dao.ThesisDetailsDao;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author khatr
 */
@MultipartConfig
public class ThesisDetailsController extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("pragma", "no-cache");
        response.setHeader("Expires", "0");
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
                case "getThesisRecord":
                    getThesisRecord(request, response);
                    break;
                case "update":
                    updateData(request, response);
                    break;
                case "delete":
                    deleteData(request, response);
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
        PrintWriter pw = response.getWriter();
        ThesisDetailsDao thesisDao = new ThesisDetailsDaoImpl();
        List<ThesisDetails> thesis = thesisDao.getThesisDetails();
        System.out.println(thesis.size());
        Gson gson = new Gson();
        pw.write(gson.toJson(thesis));

    }

    private void addData(HttpServletRequest request, HttpServletResponse response) {
        ThesisDetails thesis = new ThesisDetails();
        thesis.setThesisTitle(request.getParameter("thesisTitle"));
        thesis.setThesisExamDate(request.getParameter("thesisExamDate"));
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentByRollNo(request.getParameter("studentRollNumber"));
        thesis.setStudent(student);
        thesis.setFinalResults(Integer.parseInt(request.getParameter("finalResults")));
        thesis.setRemarks(request.getParameter("remarks"));
        ThesisDetailsDao thesisDao = new ThesisDetailsDaoImpl();
        thesisDao.addThesis(thesis);
    }

    private void getThesisRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("thesisId"));
        ThesisDetailsDao thesisDao = new ThesisDetailsDaoImpl();
        ThesisDetails thesis = thesisDao.getThesisById(id);
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(thesis));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) {

        ThesisDetails thesisDetails = new ThesisDetails();
        thesisDetails.setThesisDetailsId(Integer.parseInt(request.getParameter("thesisId")));
        thesisDetails.setThesisTitle(request.getParameter("thesisTitle"));
        thesisDetails.setThesisExamDate(request.getParameter("thesisExamDate"));
        Student student = new Student();
        student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        thesisDetails.setStudent(student);
        thesisDetails.setFinalResults(Integer.parseInt(request.getParameter("finalResults")));
        thesisDetails.setRemarks(request.getParameter("remarks"));
        thesisDetails.setStatus(request.getParameter("status"));
        ThesisDetailsDao thesisDao = new ThesisDetailsDaoImpl();
        thesisDao.updateThesis(thesisDetails);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {

        Integer id = Integer.parseInt(request.getParameter("thesisId"));
        ThesisDetailsDao thesisDao = new ThesisDetailsDaoImpl();
        thesisDao.DeleteThesis(id);
    }

}
