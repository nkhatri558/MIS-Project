/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.FacultyDao;
import com.muet.daoimpl.FacultyDaoImpl;
import com.muet.model.Faculty;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khatr
 */
public class FacultyController extends HttpServlet {

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
                case "getFacultyRecord":
                    getFacultyRecord(request, response);
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
        FacultyDao facultyDao = new FacultyDaoImpl();
        PrintWriter pw = response.getWriter();
        List<Faculty> faculties = facultyDao.getFaculties();
        Gson gson = new Gson();
        pw.write(gson.toJson(faculties));
    }

    private void addData(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty = new Faculty();
        faculty.setFacultyName(request.getParameter("facultyName"));
        FacultyDao facultyDao = new FacultyDaoImpl();
        facultyDao.addFaculty(faculty);
    }

    private void getFacultyRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("facultyId"));   
        FacultyDao facultyDao = new FacultyDaoImpl();
        PrintWriter pw = response.getWriter();
        Faculty faculty =facultyDao.getFacultyById(id);
        System.out.println(faculty.getFacultyName());
        Gson gson = new Gson();
        pw.write(gson.toJson(faculty));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(Integer.parseInt(request.getParameter("facultyId")));
        faculty.setFacultyName(request.getParameter("facultyName"));
        FacultyDao facultyDao = new FacultyDaoImpl();
        facultyDao.updateFaculty(faculty);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        FacultyDao facultyDao = new FacultyDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("facultyId"));
        facultyDao.deleteFaculty(id);
    }

}
