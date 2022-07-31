<%-- 
    Document   : 500
    Created on : Oct 28, 2020, 12:20:46 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <jsp:include page="head_links.jsp"/>
    </head>
    <body class="hold-transition layout-top-nav">
          <div class="wrapper">

        <!-- Main content -->
        <section class="content">
      <div class="error-page">
        <h2 class="headline text-danger">500</h2>

        <div class="error-content">
          <h3><i class="fas fa-exclamation-triangle text-danger"></i> Oops! Something went wrong.</h3>

          <p>
            We will work on fixing that right away.
            Meanwhile, you may <a href="login.jsp">return to Login Page</a>
          </p>
        </div>
      </div>
      <!-- /.error-page -->

    </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="bottom_links.jsp"/>
</body>
</html>
