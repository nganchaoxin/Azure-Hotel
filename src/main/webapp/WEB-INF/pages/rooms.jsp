<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>Rooms & Rates | Azure Da Nang</title>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <link
      href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="
    <c:url value="/resources/static/css/open-iconic-bootstrap.min.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/animate.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/owl.carousel.min.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/owl.theme.default.min.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/magnific-popup.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/aos.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/ionicons.min.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/bootstrap-datepicker.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/jquery.timepicker.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/flaticon.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/icomoon.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/style.css" />
    "> <link rel="stylesheet" href="
    <c:url value="/resources/static/css/footer.css" />
    ">

    <link rel="stylesheet" href="<c:url value="/resources/static/css/our_room.css" />">
    <link href='<c:url value="/resources/static/images/Logo_icon.svg" />' rel='shortcut icon'>
  </head>
  <body>
    <jsp:include page="header.jsp" />

    <div class="hero-wrap" style="background-image: url('https://www.chiclandhotel.com/files/thumb/1920/1015//uploads//Room/Phong%20Ba/Desktop/9525D.jpg');">
                 <div class="overlay"></div>
                 <div class="container">
                   <div class="row no-gutters slider-text d-flex align-itemd-end justify-content-center">
                     <div class="col-md-9 ftco-animate text-center d-flex align-items-end justify-content-center">
                     	<div class="text">
           	            <p class="breadcrumbs mb-2"><span class="mr-2"><a href="index.html">Home</a></span> <span>Azure's rooms</span></p>
           	            <h1 class="mb-4 bread">Azure's rooms</h1>
                       </div>
                     </div>
                   </div>
                 </div>
               </div>

    <section class="section-margin">
        <div class="row justify-content-center mb-5 pb-3 px-0 mx-0">
            <div class="col-md-7 heading-section text-center ftco-animate justify-item-center">
                <span class="subheading">Azure Rooms</span>
              <h2 class="mb-4">Hotel Master's Rooms</h2>
            </div>
        </div>

        <div class="container">


          <div class="row">
            <c:forEach items="${categoryList}" var="category" varStatus="index">
                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="card card-explore">
                        <div class="card-explore__img">
                            <img style="height: 250px;" class="card-img" src="getImagePhotoByCategory/${category.id}" alt="">
                          </div>
                      <div class="card-body">
                        <h3 class="card-explore__price font-weight-bold">$ <fmt:formatNumber value="${category.price}" pattern="#,###.##" /><sub>/ Per Night</sub></h3>

                        <h4 class="card-explore__title font-weight-bold"><a href="#">${category.category_name}</a></h4>
                        <p>${category.description}</p>
                        <a class="card-explore__link font-weight-bold" href="#">Book Now <i class="ti-arrow-right"></i></a>
                      </div>
                    </div>
                  </div>
            </c:forEach>
          </div>
        </div>
      </section>


    <jsp:include page="footer.jsp" />

    <!-- loader -->
    <div id="ftco-loader" class="show fullscreen">
      <svg class="circular" width="48px" height="48px">
        <circle
          class="path-bg"
          cx="24"
          cy="24"
          r="22"
          fill="none"
          stroke-width="4"
          stroke="#eeeeee"
        />
        <circle
          class="path"
          cx="24"
          cy="24"
          r="22"
          fill="none"
          stroke-width="4"
          stroke-miterlimit="10"
          stroke="#F96D00"
        />
      </svg>
    </div>
    <script>
        var today = new Date().toISOString().split('T')[0];
        document.getElementById('date-input-checkin').setAttribute('min', today);

        var checkInDateInput = document.getElementById("date-input-checkin");
        var checkInDate = checkInDateInput.value + today;
        document.getElementById('date-input-checkout').setAttribute('min', checkInDate);
    </script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery-migrate-3.0.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.easing.1.3.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.stellar.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/aos.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.animateNumber.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/jquery.timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/scrollax.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/google-map.js"></script>
    <script src="${pageContext.request.contextPath}/resources/static/js/main.js"></script>
  </body>
</html>