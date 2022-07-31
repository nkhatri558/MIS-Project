/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muet.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.muet.dao.UserDao;
import com.muet.daoimpl.UserDaoImpl;
import com.muet.model.User;
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
public class UserController extends HttpServlet {

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
                case "getUserRecord":
                    getUserRecord(request, response);
                    break;
                case "update":
                    updateData(request, response);
                    break;
                case "delete":
                    deleteData(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                case "changePassword":
                    changePassword(request, response);
                    break;
                case "changeAdminPassword":
                    changeAdminPassword(request, response);
                    break;
                default:
                    break;
            }
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.login(email, password);
        if (user.getFullName() == null) {
            request.getSession().setAttribute("failed", 1);
            response.sendRedirect("login.jsp");
        } else if(user.getRole().equals("student")) {
            request.getSession().setAttribute("student", user.getStudent());
            System.out.println("registration_status : " + user.getStudent().getRegistrationStatus());
            if(user.getStudent().getRegistrationStatus().equals("registered")) {
                System.out.println("User Unregistered");
                response.sendRedirect("student_dashboard.jsp");
            }
            else {
                response.sendRedirect("student_registration.jsp");
            }
        }
        else if(user.getRole().equals("admin") || user.getRole().equals("user")) {
            request.getSession().setAttribute("user", user);
            //request.getRequestDispatcher("index.jsp").include(request, response);
            response.sendRedirect("index.jsp");
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
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUsers();
        PrintWriter pw = response.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(users));
    }

    private void addData(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setFullName(request.getParameter("fullName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        UserDao userDao = new UserDaoImpl();
        userDao.addUser(user);
    }

    private void getUserRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserById(Integer.parseInt(request.getParameter("userId")));
        Gson gson = new Gson();
        PrintWriter pw = response.getWriter();
        pw.write(gson.toJson(user));
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setFullName(request.getParameter("fullName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setUserId(Integer.parseInt(request.getParameter("userId")));
        UserDao userDao = new UserDaoImpl();
        userDao.updateUser(user);
    }

    private void deleteData(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = new UserDaoImpl();
        Integer id = Integer.parseInt(request.getParameter("userId"));
        userDao.deleteUser(id);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newPassword = request.getParameter("password");
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        UserDao userDao = new UserDaoImpl();
        Boolean update = userDao.changePassword(newPassword, studentId);
        if(update) {
            response.sendRedirect("student_dashboard.jsp");
        }
    }

    private void changeAdminPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String newPassword = request.getParameter("password");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        UserDao userDao = new UserDaoImpl();
        Boolean update = userDao.changeAdminPassword(newPassword, userId);
        if(update) {
            response.sendRedirect("student_dashboard.jsp");
        }
    }
    
}
