/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.muet.dao.BatchDao;
import com.muet.dao.SeminarDao;
import com.muet.dao.SeminarGroupDao;
import com.muet.dao.StudentDao;
import com.muet.daoimpl.BatchDaoImpl;
import com.muet.daoimpl.SeminarDaoImpl;
import com.muet.daoimpl.SeminarGroupDaoImpl;
import com.muet.daoimpl.StudentDaoImpl;
import com.muet.model.Batch;
import com.muet.model.Seminar;
import com.muet.model.SeminarGroup;
import com.muet.model.Student;
import com.muet.model.ThesisDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 92310
 */
public class SeminarGroupController extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        String action = request.getParameter("action");
        if (null == action) {
            viewData(request, response);
        } else {
            switch (action) {
                case "":
                case "view":
                    viewData(request, response);
                    break;
                case "getSeminarGroupRecord":
                    getSeminarGroupRecord(request, response);
                    break;
                case "update":
                    updateData(request, response);
                    break;
                case "delete":
                    deleteData(request, response);
                    break;
                case "showStudentSeminarRecords":
                    showStudentSeminarRecords(request, response);
                    break;
                case "getStudentSeminarRecords":
                    getStudentSeminarRecords(request, response);
                    break;
                case "applySeminar":
                    applySeminar(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             processRequest(request, response);
         } catch (ParseException ex) {
             Logger.getLogger(SeminarGroupController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             processRequest(request, response);
         } catch (ParseException ex) {
             Logger.getLogger(SeminarGroupController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void viewData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("View is working");
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        PrintWriter pw = response.getWriter();
        List<SeminarGroup> seminarGroup = seminarGroupDao.getSeminarGroups();
        Gson gson = new Gson();
        pw.write(gson.toJson(seminarGroup));

    }

    

    private void getSeminarGroupRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("seminarGroupId"));   
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        PrintWriter pw = response.getWriter();
        SeminarGroup seminarGroup=seminarGroupDao.getSeminarGroupById(id);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        pw.write(gson.toJson(seminarGroup));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        SeminarGroup seminarGroup = new SeminarGroup();
        seminarGroup.setSeminarGroupId(Integer.parseInt(request.getParameter("seminarGroupId")));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        seminarGroup.setDateConducted(sf.parse(request.getParameter("dateConducted")));
        seminarGroup.setDateApplied(sf.parse(request.getParameter("dateApplied")));
        seminarGroup.setRemarks(request.getParameter("remarks"));
        seminarGroup.setComments(request.getParameter("comments"));
        seminarGroup.setStatus(request.getParameter("status"));
        Integer id =Integer.parseInt(request.getParameter("seminarId"));
        //Integer thesisId=Integer.parseInt(request.getParameter("thesisId"));
        SeminarDao seminarDao=new SeminarDaoImpl();
        Seminar seminar=seminarDao.getSeminarById(id);
//        seminar.setSeminarId(id);
//        seminar.setSeminarTitle(request.getParameter("seminarTitle"));
        seminarGroup.setSeminar(seminar);
        ThesisDetails thesis =new ThesisDetails();
        //ThesisDao thesisDao =new ThesisDaoImpl();
        //thesis.setThesisId(thesisId);
        //thesis.setThesisTitle(request.getParameter("thesisTitle"));
        //seminarGroup.setThesis(thesis);
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        seminarGroupDao.updateSeminarGroup(seminarGroup);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("seminarGroupId"));
        seminarGroupDao.deleteSeminarGroup(id);
    }

    private void getSeminarRecord(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showStudentSeminarRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        List<SeminarGroup> seminarGroups = seminarGroupDao.getSeminarGroupsByStudentId(studentId);
        PrintWriter pw = response.getWriter();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        pw.write(gson.toJson(seminarGroups));
    }

    private void getStudentSeminarRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        StudentDao studentDao = new StudentDaoImpl();
        BatchDao batchDao = new BatchDaoImpl();
        Batch batch = batchDao.getBatchByBatchName(studentDao.getStudentById(studentId).getBatch());
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        List<Seminar> seminarGroups = seminarGroupDao.getStudentSeminarRecords(studentId, batch.getBatchId());
        PrintWriter pw = response.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(seminarGroups));
    }

    private void applySeminar(HttpServletRequest request, HttpServletResponse response) {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentById(studentId);
        Integer seminarId = Integer.parseInt(request.getParameter("seminarId"));
        SeminarDao seminarDao = new SeminarDaoImpl();
        Seminar seminar = seminarDao.getSeminarById(seminarId);
        SeminarGroup seminarGroup = new SeminarGroup();
        seminarGroup.setStudent(student);
        seminarGroup.setSeminar(seminar);
        SeminarGroupDao seminarGroupDao = new SeminarGroupDaoImpl();
        seminarGroupDao.addSeminarGroup(seminarGroup);
    }


}
