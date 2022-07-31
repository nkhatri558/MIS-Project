/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.BatchDao;
import com.muet.dao.StudentThesisDao;
import com.muet.dao.ThesisDao;
import com.muet.dao.ThesisDocumentsDao;
import com.muet.daoimpl.BatchDaoImpl;
import com.muet.daoimpl.StudentThesisDaoImpl;
import com.muet.daoimpl.ThesisDaoImpl;
import com.muet.daoimpl.ThesisDocumentsDaoImpl;
import com.muet.model.Batch;
import com.muet.model.Student;
import com.muet.model.StudentThesis;
import com.muet.model.Thesis;
import com.muet.model.ThesisDocuments;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author 92310
 */
@MultipartConfig
@WebServlet(name = "StudentThesisController", urlPatterns = {"/StudentThesisController"})
public class StudentThesisController extends HttpServlet {

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
            throws ServletException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("viewBatches")) {
            getAllBatches(request, response);
        }
        else if (action.equals("add")) { 
            addStudentThesis(request, response);
        }
        else if(action.equals("view")){
            getAllThesis(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(StudentThesisController.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(StudentThesisController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void getAllBatches(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //To change body of generated methods, choose Tools | Templates.
        PrintWriter pw = response.getWriter();
        StudentThesisDao dao = new StudentThesisDaoImpl();
        List<Batch> list = dao.getAllBatches();
        Gson gson = new Gson();
        pw.write(gson.toJson(list));
    }

    private void addStudentThesis(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //To change body of generated methods, choose Tools | Templates.
        PrintWriter out = response.getWriter();
        StudentThesis studentThesis = new StudentThesis();
        Thesis thesis = new Thesis();
        Batch batch = new Batch();
        ThesisDocuments thesisDocument=new ThesisDocuments();
        ThesisDocumentsDao thesisDocumentsDao=new ThesisDocumentsDaoImpl();
        ThesisDao thesisDao=new ThesisDaoImpl();
        BatchDao batchDao=new BatchDaoImpl();
        Student student=new Student();
        StudentThesisDao studentThesisDao =new StudentThesisDaoImpl();
        studentThesis.setThesisTitle(request.getParameter("thesisTitle"));
        studentThesis.setAppliedDate(request.getParameter("examDate"));
        batch.setBatchName(request.getParameter("batchName"));  
        student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        Part imageUpload = request.getPart("uploadImage");
        InputStream image = imageUpload.getInputStream();
        byte[] imageData = new byte[image.available()];
        image.read(imageData);
        String fileName = imageUpload.getSubmittedFileName();
        String imagePath = "uploads" + File.separator + "carouselImages" + File.separator;
        String link = "uploads" + File.separator + "carouselImages" + File.separator + fileName;
        FileOutputStream fos1 = new FileOutputStream(request.getRealPath("/")+link);
        fos1.write(imageData);
        studentThesis.setStudent(student);
        thesis.setBatch(batch);
        studentThesis.setThesis(thesis);
        studentThesisDao.addStudentThesis(studentThesis);
        batch=batchDao.getBatchByBatchName(batch.getBatchName());
        thesis=thesisDao.getThesisRecordByBatchId(batch.getBatchId());
        thesisDocument.setThesisDocumentTitle(studentThesis.getThesisTitle());
        thesisDocument.setThesisDocumentId(studentThesis.getThesisDetailsId());
        thesisDocument.setThesisDocument(link);
        studentThesis=studentThesisDao.getStudentThesisByStudentId(Integer.parseInt(request.getParameter("studentId")));
        thesisDocument.setStudentThesis(studentThesis);
        thesisDocumentsDao.addThesisDocuments(thesisDocument);
        fos1.close();
    }

    private void getAllThesis(HttpServletRequest request, HttpServletResponse response) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            PrintWriter pw=response.getWriter();
            StudentThesisDao dao=new StudentThesisDaoImpl();
            List<StudentThesis> list=dao.getAllStudentThesis(Integer.parseInt(request.getParameter("id")));
            Gson gson=new Gson();
            pw.write(gson.toJson(list));
        } catch (IOException ex) {
            Logger.getLogger(StudentThesisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
