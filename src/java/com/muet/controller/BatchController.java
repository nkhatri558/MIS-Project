/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.BatchDao;
import com.muet.daoimpl.BatchDaoImpl;
import com.muet.model.Batch;
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
public class BatchController extends HttpServlet {

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
                case "getBatchRecord":
                    getBatchRecord(request, response);
                    break;
                case "update":
                    updateData(request, response);
                    break;
                case "delete":
                    deleteData(request, response);
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
        BatchDao batchDao = new BatchDaoImpl();
        PrintWriter pw = response.getWriter();
        List<Batch> batches = batchDao.getBatches();
        Gson gson = new Gson();
        pw.write(gson.toJson(batches));
    }

    private void addData(HttpServletRequest request, HttpServletResponse response) {
        Batch batch = new Batch();
        batch.setBatchName(request.getParameter("batchName"));
        BatchDao batchDao = new BatchDaoImpl();
        batchDao.addBatch(batch);
    }

    private void getBatchRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("batchId"));
        BatchDao batchDao = new BatchDaoImpl();
        Batch batch = batchDao.getBatchById(id);
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(batch));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) {
        Batch batch = new Batch();
        batch.setBatchName(request.getParameter("batchName"));
        batch.setBatchId(Integer.parseInt(request.getParameter("batchId")));
        BatchDao batchDao = new BatchDaoImpl();
        batchDao.updateBatch(batch);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("batchId"));
        BatchDao batchDao = new BatchDaoImpl();
        batchDao.deleteBatch(id);
    }

}
