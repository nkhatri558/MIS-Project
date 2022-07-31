/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.BatchDao;
import com.muet.dao.ThesisDao;
import com.muet.daoimpl.BatchDaoImpl;
import com.muet.daoimpl.ThesisDaoImpl;
import com.muet.model.Batch;
import com.muet.model.Thesis;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 92310
 */
@MultipartConfig
@WebServlet(name = "ThesisController", urlPatterns = {"/ThesisController"})
public class ThesisController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("addThesis")) {
            addThesis(request, response);
        } else if (action.equals("viewBatches")) {
            getAllBatches(request, response);
        } else if (action.equals("view")) {
            getAllThesisRecords(request, response);
        } else if (action.equals("getData")) {
            getDataById(request, response);
        } else if (action.equals("updateThesis")) {
            updateThesisData(request, response);
        }
        else if(action.equals("delete")){
            deleteThesis(request, response);
        }
    }

    private void addThesis(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        PrintWriter pw = response.getWriter();
        Thesis thesis = new Thesis();
        ThesisDao thesisDao = new ThesisDaoImpl();
        Batch batch = new Batch();
        thesis.setAnnouncementDate(request.getParameter("announcementDate"));
        thesis.setDueDate(request.getParameter("dueDate"));
        batch.setBatchName(request.getParameter("selectedBatch"));
        thesis.setBatch(batch);
        thesisDao.addThesis(thesis);

    }

    private void getAllBatches(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        PrintWriter pw = response.getWriter();
        BatchDao dao = new BatchDaoImpl();
        List<Batch> list = dao.getBatches();
        Gson gson = new Gson();
        pw.write(gson.toJson(list));
    }

    private void getAllThesisRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        ThesisDao thesisDao = new ThesisDaoImpl();
        List<Thesis> list = thesisDao.getAllThesis();
        Gson gson = new Gson();
        pw.write(gson.toJson(list));
    }

    private void getDataById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        PrintWriter pw = response.getWriter();
        ThesisDao thesisDao = new ThesisDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("thesisId"));
        List<Thesis> list = thesisDao.getThesisDataById(id);
        Gson gson = new Gson(); 
        pw.write(gson.toJson(list));
    }

    private void updateThesisData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        PrintWriter pw = response.getWriter();
        ThesisDao thesisDao = new ThesisDaoImpl();
        Thesis thesis = new Thesis();
        Batch batch = new Batch();
        thesis.setAnnouncementDate(request.getParameter("announcementDate"));
        thesis.setDueDate(request.getParameter("dueDate"));
        batch.setBatchName(request.getParameter("batchName"));
        batch.setBatchId(Integer.parseInt(request.getParameter("batchId")));
        thesis.setThesisId(Integer.parseInt(request.getParameter("thesisId")));
        thesis.setBatch(batch);
        thesisDao.updateThesis(thesis);
    }

    private void deleteThesis(HttpServletRequest request, HttpServletResponse response) {
        //To change body of generated methods, choose Tools | Templates.
        ThesisDao thesisDao=new ThesisDaoImpl();
        Integer id=Integer.parseInt(request.getParameter("thesisId"));
        thesisDao.delete(id);
    }
    

}
