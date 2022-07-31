/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.muet.dao.UploadImagesDao;
import com.muet.daoimpl.UploadImagesDaoImpl;
import com.muet.model.UploadImages;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "UploadImagesController", urlPatterns = {"/UploadImagesController"})
public class UploadImagesController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadImagesController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(UploadImagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("addImageInfo")) {
            try {
                saveImages(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UploadImagesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("view")) {
            getAllImages(request, response);
        } else if (action.equals("delete")) {
            deleteData(request, response);
        }

    }

    private void saveImages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/html");
        PrintWriter out = response.getWriter();
        Part imageUpload = request.getPart("uploadImage");
        InputStream image = imageUpload.getInputStream();
        byte[] imageData = new byte[image.available()];
        image.read(imageData);
        String fileName = imageUpload.getSubmittedFileName();
        String extension = imageUpload.getContentType();
        System.out.println(extension);
        if (extension.equals("image/png") || extension.equals("image/jpg") || extension.equals("image/jpeg") || extension.equals("image/gif")) {
            String imagePath = "uploads" + File.separator + "carouselImages" + File.separator;
            String link = "uploads" + File.separator + "carouselImages" + File.separator + fileName;
            FileOutputStream fos1 = new FileOutputStream(request.getRealPath("/") + link);
            fos1.write(imageData);
            fos1.close();
            UploadImages images = new UploadImages();
            images.setLink(link);
            images.setPath(imagePath);
            UploadImagesDao dao = new UploadImagesDaoImpl();
            dao.addImages(images);
        } else {
            System.out.println("Else is working ");
            out.println("<script type='text/javascript'>");
            out.println("alert('User or password incorrect');");
            out.println("</script>");
        }

    }

    private void getAllImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        List<UploadImages> list = new ArrayList<>();
        UploadImagesDao dao = new UploadImagesDaoImpl();
        list = dao.getAllImages();
        Gson gson = new Gson();
        pw.write(gson.toJson(list));

    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        //To change body of generated methods, choose Tools | Templates.
        Integer id = Integer.parseInt(request.getParameter("imageId"));
        UploadImages images = new UploadImages();
        images.setId(id);
        UploadImagesDao dao = new UploadImagesDaoImpl();
        dao.deleteImages(images);
    }

}
