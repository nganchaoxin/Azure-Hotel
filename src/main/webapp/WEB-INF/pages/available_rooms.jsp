<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="author" content="Untree.co">
	<link rel="shortcut icon" href="favicon.png">

	<meta name="description" content="" />
	<meta name="keywords" content="bootstrap, bootstrap5" />

	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">

<link href='<c:url value="/resources/static/css/aboutpage/fonts/icomoon/style.css" />' rel='stylesheet'>
<link href='<c:url value="/resources/static/css/aboutpage/fonts/flaticon/font/flaticon.css" />' rel='stylesheet'>
<link href='<c:url value="/resources/static/css/aboutpage/css/tiny-slider.css" />' rel='stylesheet'>
<link href='<c:url value="/resources/static/css/aboutpage/css/aos.css" />' rel='stylesheet'>
<link href='<c:url value="/resources/static/css/aboutpage/css/style.css" />' rel='stylesheet'>
<link href='<c:url value="/resources/static/css/header.css" />' rel='stylesheet'>
<link href='<c:url value="/resources/static/css/bookingdetail.css" />' rel='stylesheet'>
    <link href='<c:url value="/resources/static/css/bookingcart.css" />' rel='stylesheet'>
    <link href='<c:url value="/resources/static/css/footer.css" />' rel='stylesheet'>s
<link rel="stylesheet" href="
    <c:url value="/resources/static/css/style.css" />
    ">
    <link rel="stylesheet" href="
        <c:url value="/resources/static/css/footer.css" />
        ">

    <link href='<c:url value="/resources/static/images/Logo_icon.svg" />' rel='shortcut icon'>
    <link href='<c:url value="/resources/static/images/Logo_icon.svg" />' rel='shortcut icon'>

	<title>Azure Hotel - About Us</title>
</head>
<body>
<header style="display: flex;">
            <div class="logo_top">
           <a href="./">
                     <img src="resources/static/images/Logo_logo_white.png"/>
                   </a>
            </a></div>
            <nav>
                <ul class="navbar_nav">
                    <li class="li_active"><a href="./bookingcart">Avaiability</a></li>
                    <li><a href="./about">About</a></li>
                    <li><a href="./contact">Contact</a></li>
                    <li><a href="./policy">Policies</a></li>
                  </ul>
            </nav>
            <div class="user_info">

                <sec:authorize access="isAuthenticated()">
                <ul class="user_ul">
                    <li class="nav_item">
                          <a href="<c:url value="/user/account" />" class="nav_link" style="font-weight: 500;">
                            ${accountEntity.username}
                          </a>
                     </li>
                    <li class="nav_item">
                      <a href="<c:url value="/logout" />" class="nav_link">LogOut</a>
                    </li>
                     </ul>
                </sec:authorize>

            </div>
        </header>
<main style="padding-top:0px;">
<c:if test="${not empty availableRoomList}">
	<div class="section section-properties">
		<div class="container">
			<div class="row">
			<c:forEach items="${availableRoomList}" var="room">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
					<div class="property-item mb-30">

						<a href="property-single.html" class="img">
							<img src='<c:url value="/resources/static/images/rooms/room-3.jpg" />' alt="Image" class="img-fluid">
						</a>

						<div class="property-content">
							<div class="price mb-2"><span><fmt:formatNumber value="${room.categoryEntity.price}" pattern="#,###.##" /> VND</span></div>
							<div>
								<span style="font-size: 17px;" class="d-block mb-2 text-black-50"><strong>${fn:toUpperCase(room.room_name)} - ${room.categoryEntity.category_name}</strong></span>

								<div class="specs d-flex mb-4">
									<span style="margin-right: 1em;" class="d-block d-flex align-items-center me-3">
										<span class="icon-bed me-2" style="margin-right: .5em;"></span>
										<span class="caption" style="margin-right: .5em;">${room.categoryEntity.bed_info} beds</span>
									</span>
									<span class="d-block d-flex align-items-center">
										<span class="icon-bath me-2" style="margin-right: .5em;"></span>
										<span class="caption" style="margin-right: .5em;">2 baths</span>
									</span>
								</div>

								<a href="addToCart/room=${room.id}" class="btn btn-primary py-2 px-3" style="margin-top: 0px;">Add to cart</a>
							</div>
						</div>
					</div> <!-- .item -->
				</div>
            </c:forEach>
<c:if test="${totalPages > 1}">
			<div class="row align-items-center py-5" style="justify-content: center;">
				<div class="col-lg-6 text-center">
					<div class="custom-pagination">
						<c:if test="${currentPage > 0}">
                            <li><a href="?checkin=${param.checkin}&checkout=${param.checkout}&roomType=${param.roomType}&guests=${param.guests}&page=${currentPage - 1}">&laquo;</a></li>
                        </c:if>
                        <c:forEach begin="0" end="${totalPages - 1}" var="i">
                            <li ${currentPage == i ? 'active' : ''}><a href="?checkin=${param.checkin}&checkout=${param.checkout}&roomType=${param.roomType}&guests=${param.guests}&page=${i}">${i + 1}</a></li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages - 1}">
                            <li><a href="?checkin=${param.checkin}&checkout=${param.checkout}&roomType=${param.roomType}&guests=${param.guests}&page=${currentPage + 1}">&raquo;</a></li>
                        </c:if>
					</div>
				</div>
			</div>
</c:if>
		</div>
	</div>
	</c:if>
	<c:if test="${empty availableRoomList}">
          <div class="misc-wrapper " >
            <h2 class="mx-2">No available rooms found!</h2>
            <p class="mb-4 mx-2">Sorry for the inconvenience</p>
            <a
              href="./"
              class="btn btn-primary btn-lg"
              style="background-color: #cca772; border: none"
              >Back to home</a
            >
            <div class="mt-4">
              <img
                src="resources/static/assets/img/illustrations/girl-doing-yoga-light.png"
                alt="girl-doing-yoga-light"
                width="500"
                class="img-fluid"
                data-app-dark-img="illustrations/girl-doing-yoga-dark.png"
                data-app-light-img="illustrations/girl-doing-yoga-light.png"
              />
            </div>
          </div>
        </c:if>
    <!-- Preloader -->
    <div id="overlayer"></div>
    <div class="loader">
    	<div class="spinner-border" role="status">
    		<span class="visually-hidden">Loading...</span>
    	</div>
    </div>
    </main>

<script>
    $(document).ajaxStart(function() {
      $('#overlayer').fadeIn();
      $('.loader').fadeIn();
    });

    $(document).ajaxStop(function() {
      $('#overlayer').fadeOut();
      $('.loader').fadeOut();
    });
</script>


   <script src='<c:url value="/resources/static/css/aboutpage/js/bootstrap.bundle.min.js" />'></script>
   <script src='<c:url value="/resources/static/css/aboutpage/js/tiny-slider.js" />'></script>
   <script src='<c:url value="/resources/static/css/aboutpage/js/aos.js" />'></script>
   <script src='<c:url value="/resources/static/css/aboutpage/js/navbar.js" />'></script>
   <script src='<c:url value="/resources/static/css/aboutpage/js/counter.js" />'></script>
   <script src='<c:url value="/resources/static/css/aboutpage/js/custom.js" />'></script>
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
   <jsp:include page="footer.jsp" />
  </html>
