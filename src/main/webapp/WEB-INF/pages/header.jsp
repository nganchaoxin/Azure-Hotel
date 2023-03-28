<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <header>
    <nav
      class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
      <div class="container">
        <a class="navbar-brand" href="./">
          <img width="200" height="auto" src="resources/static/images/Logo_logo.png"/>
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#ftco-nav"
          aria-controls="ftco-nav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="oi oi-menu"></span> Menu
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a href="./" class="nav-link">Home</a>
            </li>
            <li class="nav-item">
              <a href="rooms.html" class="nav-link">Rooms</a>
            </li>
            <li class="nav-item">
              <a href="restaurant.html" class="nav-link">Restaurant</a>
            </li>
            <li class="nav-item">
              <a href="about.html" class="nav-link">About</a>
            </li>
            <li class="nav-item">
              <a href="blog.html" class="nav-link">Blog</a>
            </li>
            <li class="nav-item">
              <a href="contact.html" class="nav-link">Contact</a>
            </li>
            <!-- Login -->
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item">
                    <a href="<c:url value="/myCart" />">Manage Hotel</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li class="nav-item">
                        <a href="<c:url value="/bookingcart" />" class="nav-link">My Cart</a>
                     </li>
                </sec:authorize>
                <li class="nav-item">
                      <a href="<c:url value="/user/account" />" class="nav-link" style="font-weight: 500;">
                        ${accountEntity.username}
                      </a>
                 </li>
                <li class="nav-item">
                  <a href="<c:url value="/logout" />" class="nav-link">LogOut</a>
                </li>
            </sec:authorize>

            <!-- If No login then will show Login Page -->
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item ">
                    <a class="nav-link" href="<c:url value="/login" />">My Cart</a>
                 </li>
                <li class="nav-item">
                  <a href="<c:url value="/login" />" class="nav-link">Login</a>
                </li>
                <li class="nav-item">
                   <a href="<c:url value="/signup" />" class="nav-link">SignUp</a>
                 </li>
            </sec:authorize>
          </ul>
        </div>
      </div>
    </nav>

  </header>

