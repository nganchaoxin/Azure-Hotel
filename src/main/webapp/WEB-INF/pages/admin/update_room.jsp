<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html
  lang="en"
  class="light-style customizer-hide"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="resources/static/assets/"
  data-template="vertical-menu-template-free"
>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Azure Hotel - Profile Management</title>
    <meta name="description" content="asdas" />

    <!-- Favicon -->
    <link
      rel="icon"
      type="image/x-icon"
      href='<c:url value="/resources/static/assets/img/favicon/favicon.ico" />'
    />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href='<c:url value="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" />'
      rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link
      href='<c:url value="/resources/static/assets/vendor/fonts/boxicons.css" />'
      rel="stylesheet"
    />

    <!-- Core CSS -->
    <link
      href='<c:url value="/resources/static/assets/vendor/css/core.css" />'
      class="template-customizer-core-css"
      rel="stylesheet"
    />
    <link
      href='<c:url value="/resources/static/assets/vendor/css/theme-default.css" />'
      class="template-customizer-theme-css"
      rel="stylesheet"
    />
    <link
      href='<c:url value="/resources/static/assets/assets/css/demo.css" />'
      rel="stylesheet"
    />

    <!-- Vendors CSS -->
    <link
      href='<c:url value="/resources/static/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />'
      rel="stylesheet"
    />

    <script src='<c:url value="/resources/static/assets/vendor/js/helpers.js" />'></script>
    <script src='<c:url value="/resources/static/assets/js/config.js" />'></script>
  </head>

  <body>
    <!-- Content -->

    <div class="container-xxl">
      <div class="authentication-wrapper authentication-basic container-p-y">
        <div class="authentication-inner">
          <!-- Register Card -->
          <div class="card">
            <div class="card-body">
              <!-- Logo -->
              <div class="app-brand justify-content-center">
                <a href="index.html" class="app-brand-link gap-2">
                  <span class="app-brand-logo demo">
                    <span class="app-brand-text demo menu-text fw-bolder ms-2"
                      ><img
                        src='<c:url value="/resources/static/assets/img/Logo_logo.png" />'
                        alt=""
                        width="190px"
                    /></span>
                  </span>
                </a>
              </div>
              <!-- /Logo -->
              <h4 class="mb-2">Edit and Add Room Here 🚀</h4>

              <form:form
                action="${action}"
                modelAttribute="room"
                method="post"
                id="formAuthentication"
                class="mb-3"
              >
                <legend class="scheduler-border">
                  <c:out value="${msg}" />
                </legend>
                <c:if test="${type.equals('update')}">
                  <div class="mb-3">
                    <label for="username" class="form-label">Room ID</label>
                    <form:input
                      path="id"
                      type="text"
                      class="form-control"
                      id="id"
                      disabled="true"
                    />
                    <form:hidden path="id" />
                    <form:hidden path="categoryEntity.id" />
                  </div>
                </c:if>

                <div class="mb-3">
                  <label for="username" class="form-label">Room name</label>
                  <form:input
                    path="room_name"
                    type="text"
                    class="form-control"
                    id="username"
                    name="username"
                    placeholder="Enter room name"

                  />
                  <small style="color: red">
                     <c:out value="${error_duplicate}" />
                   </small>
                   <small style="color: red">
                      <c:out value="${message}" />
                    </small>

                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">Room number</label>
                  <form:input
                    path="room_number"
                    type="number"
                    class="form-control"
                    id="email"
                    name="email"
                    placeholder="Enter room number"

                  />
                  <small style="color: red">
                     <c:out value="${error_duplicate}" />
                   </small>
                   <small style="color: red">
                      <c:out value="${message}" />
                    </small>

                </div>
                <div class="mb-3">
                  <label for="username" class="form-label">Room status</label>
                  <form:select
                    path="room_status"
                    class="form-control"
                    id="status"
                    name="status"

                  >
                    <c:forEach items="${roomStatusList}" var="roomStatus">
                      <form:option
                        value="${roomStatus}"
                        label="${roomStatus}"
                      />
                    </c:forEach>
                  </form:select>

                </div>

                    <div class="mb-3">
                      <label for="username" class="form-label">Category name</label>

                      <form:select
                        path="categoryEntity.id"
                        class="form-control"
                      >
                           <form:options items="${categoryList}" />
                      </form:select>
                    
                    </div>

                <button type="submit" class="btn btn-primary d-grid w-100">
                  Save
                </button>
              </form:form>
            </div>
          </div>
          <!-- Register Card -->
        </div>
      </div>
    </div>

    <script src='<c:url value="/resources/static/assets/vendor/libs/jquery/jquery.js" />'></script>
    <script src='<c:url value="/resources/static/assets/vendor/libs/popper/popper.js" />'></script>
    <script src='<c:url value="/resources/static/assets/vendor/js/bootstrap.js" />'></script>

    <script src='<c:url value="/resources/static/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js" />'></script>

    <script src='<c:url value="/resources/static/assets/vendor/js/menu.js" />'></script>

    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src='<c:url value="/resources/static/assets/js/main.js" />'></script>

    <!-- Page JS -->
    <script src='<c:url value="/resources/static/assets/js/pages-account-settings-account.js" />'></script>

    <!-- Place this tag in your head or just before your close body tag. -->
    <script
      async
      defer
      src='<c:url value="https://buttons.github.io/buttons.js" />'
    ></script>
  </body>
</html>
