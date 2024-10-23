<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <title>Update User</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
      </head>

      <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />
        <div id="layoutSidenav">
          <jsp:include page="../layout/sidebar.jsp" />
          <div id="layoutSidenav_content">
            <main>
              <div class="container-fluid px-4">
                <h1 class="mt-4">Manage Users</h1>
                <ol class="breadcrumb mb-4">
                  <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                  <li class="breadcrumb-item active">Users</li>
                </ol>
                <div class="container mt-5">
                  <div class="row">
                    <div class="col-md-6 col-12 mx-auto">
                      <h3>Update a user</h3>
                      <hr />
                      <form:form method="post" action="/admin/user/update" modelAttribute="newUser" class="row"
                        enctype="multipart/form-data">
                        <div class="mb-3" style="display: none;">
                          <label class="form-label">Id:</label>
                          <form:input path="id" type="text" class="form-control" />
                        </div>
                        <div class="mb-3 col-12 col-md-6">
                          <label class="form-label">Email:</label>
                          <form:input path="email" type="email" class="form-control" disabled="true" />
                        </div>
                        <div class="mb-3 col-12 col-md-6">
                          <label class="form-label">Phone Number:</label>
                          <form:input path="phone" type="text" class="form-control" />
                        </div>
                        <div class="mb-3 col-12 col-md-6">
                          <label class="form-label">Full Name:</label>
                          <form:input path="fullName" type="text" class="form-control" />
                        </div>
                        <div class="mb-3 col-12 col-md-6">
                          <label class="form-label">Address:</label>
                          <form:input path="address" type="text" class="form-control" />
                        </div>
                        <div class="mb-3 col-12 col-md-6">
                          <label class="form-label">Role:</label>
                          <form:select class="form-select" path="role.name">
                            <form:option value="ADMIN">ADMIN</form:option>
                            <form:option value="USER">USER</form:option>
                          </form:select>
                        </div>

                        <div class="col-12 mb-5">
                          <button type="submit" class="btn btn-warning">Update</button>
                        </div>
                      </form:form>
                    </div>
                  </div>
                </div>
              </div>
            </main>
            <jsp:include page="../layout/footer.jsp" />

          </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
          crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
      </body>

      </html>