<%-- 
    Document   : navbar
    Created on : July 1, 2020, 9:24:22 AM
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
    <a href="index.jsp" class="brand-link">
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
                <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="index.jsp" class="d-block">${sessionScope.user.fullName}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item">
                        <!--<a href="index.jsp?username=${sessionScope.user.fullName}" class="nav-link"">-->
                            <a href="index.jsp" class="nav-link"">
                            <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>
                                Dashboard
                            </p>
                        </a>

                </li>

                <li class="nav-item">
                    <a href="faculty.jsp" class="nav-link">                        
                            <i class="nav-icon fas fa-user-friends"></i>
                            <p>Faculty</p>

                        </a>
                </li>

                <li class="nav-item">
                     <a href="department.jsp" class="nav-link">
                        <i class="nav-icon fas fa-university"></i>
                        <p>Department</p>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="batch.jsp" class="nav-link">
                        <i class="nav-icon fas fa-users"></i>
                        <p>Batch</p>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="student.jsp" class="nav-link">
                        <i class="nav-icon fas fa-user-graduate"></i>
                        <p>Students</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="fees.jsp" class="nav-link">
                        <i class="nav-icon fas fa-money-bill-alt"></i>
                        <p>Fees</p>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="fees_details.jsp" class="nav-link">
                        <i class="nav-icon fas fa-money-bill-alt"></i>
                        <p>Fees Details</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="thesis.jsp" class="nav-link">
                        <i class="nav-icon fas fa-book"></i>
                        <p>Thesis</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="thesis_details.jsp" class="nav-link">
                        <i class="nav-icon fas fa-book"></i>
                        <p>Thesis Details</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="seminar.jsp" class="nav-link">
                        <i class="nav-icon fas fa-file-powerpoint"></i>
                        <p>Seminar</p>
                    </a>
                </li>
                
                <li class="nav-item">
                    <a href="seminar_details.jsp" class="nav-link">
                        <i class="nav-icon fas fa-file-powerpoint"></i>
                        <p>Seminar Details</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="uploadImages.jsp" class="nav-link">
                        <i class="nav-icon fas fa-users"></i>
                        <p>Upload Images</p>
                        
                    </a>
                </li>
                
                <li class="nav-item">
                    <a href="admin_update_password.jsp" class="nav-link">                        
                        <i class="nav-icon fas fa-key"></i>
                        <p>Update Password</p>
                    </a>
                </li>
                <c:if test="${sessionScope.user.role == 'admin'}">
                    
                    <li class="nav-item">
                        <a href="user.jsp" class="nav-link">
                            <i class="nav-icon fas fa-users"></i>
                            <p>Users</p>
                        </a>
                    </li>
                </c:if>

            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>