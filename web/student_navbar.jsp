<%-- 
    Document   : student_navbar
    Created on : Aug 20, 2020, 8:30:20 PM
    Author     : khatr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Main Sidebar Container -->
<style>
    button:hover{
        color: black;
    }
</style>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="student_dashboard.jsp" class="brand-link">
        <img src="dist/img/AdminLTELogo.png"
             alt="AdminLTE Logo"
             class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">MIS MUET</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!--Sidebar user (optional)--> 
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <c:choose>
                    <c:when test="${sessionScope.student.profileImage != null}">
                        <img src="${sessionScope.student.profileImage}" class="img-circle elevation-2" alt="User Image" width="160" height="160">
                    </c:when>
                    <c:otherwise>
                        <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                    </c:otherwise>
                </c:choose>
                
            </div>
            <div class="info">
                <a href="student_dashboard.jsp" class="d-block">${sessionScope.student.fullName}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item">
                    <a href="student_dashboard.jsp" class="nav-link"">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Home
                        </p>
                    </a>

                </li>
<!--                <li class="nav-item">
                    <a href="student_registration.jsp" class="nav-link">                        
                        <i class="nav-icon fas fa-money-bill text-info"></i>
                        <p>Registration</p>
                    </a>
                </li>-->
                <li class="nav-item">
                    <a href="student_fees.jsp" class="nav-link">                        
                        <i class="nav-icon fas fa-money-bill"></i>
                        <p>Fees</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="student_seminar.jsp" class="nav-link">                        
                        <i class="nav-icon fas fa-file-powerpoint"></i>
                        <p>Seminar</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="student_profile_view.jsp" class="nav-link">                        
                        <i class="nav-icon fas fa-user-graduate"></i>
                        <p>Profile</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="change_password.jsp" class="nav-link">                        
                        <i class="nav-icon fas fa-key"></i>
                        <p>Update Password</p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>