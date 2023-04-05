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
    <title>Restaurant | Azure Da Nang</title>
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

    <link href='<c:url value="/resources/static/images/Logo_icon.svg" />' rel='shortcut icon'>
  </head>
  <body>
    <jsp:include page="header.jsp" />
       <div class="hero-wrap" style="background-image: url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80');">
             <div class="overlay"></div>
             <div class="container">
               <div class="row no-gutters slider-text d-flex align-itemd-end justify-content-center">
                 <div class="col-md-9 ftco-animate text-center d-flex align-items-end justify-content-center">
                 	<div class="text">
       	            <p class="breadcrumbs mb-2"><span class="mr-2"><a href="index.html">Home</a></span> <span>Restaurant</span></p>
       	            <h1 class="mb-4 bread">Restaurant</h1>
                   </div>
                 </div>
               </div>
             </div>
           </div>
      <section class="ftco-section ftco-menu" style="background-image: url(images/restaurant-pattern.jpg);">
      			<div class="container">
      				<div class="row justify-content-center mb-5 pb-3">
                <div class="col-md-7 heading-section text-center ftco-animate">
                	<span class="subheading">Restaurant</span>
                  <h2>Restaurant</h2>
                </div>
              </div>
      				<div class="row">
              	<div class="col-md-6">
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img order-md-last" style="background-image: url(resources/static/images/menu/menu-1.jpg);"></div>
              			<div class="desc pr-3 text-md-right">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3 class="order-md-last heading-left"><span>Grilled Crab with Onion</span></h3>
      	        				<span class="price price-left">$20.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
              			</div>
              		</div>
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img order-md-last" style="background-image: url(resources/static/images/menu/menu-2.jpg);"></div>
              			<div class="desc pr-3 text-md-right">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3 class="order-md-last heading-left"><span>Grilled Beef with potatoes</span></h3>
      	        				<span class="price price-left">$29.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img order-md-last" style="background-image: url(resources/static/images/menu/menu-3.jpg);"></div>
              			<div class="desc pr-3 text-md-right">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3 class="order-md-last heading-left"><span>Grilled Beef with potatoes</span></h3>
      	        				<span class="price price-left">$20.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img order-md-last" style="background-image: url(resources/static/images/menu/menu-4.jpg);"></div>
              			<div class="desc pr-3 text-md-right">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3 class="order-md-last heading-left"><span>Grilled Beef with potatoes</span></h3>
      	        				<span class="price price-left">$20.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              	</div>

              	<div class="col-md-6">
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img" style="background-image: url(resources/static/images/menu/menu-5.jpg);"></div>
              			<div class="desc pl-3">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3><span>Grilled Beef with potatoes</span></h3>
      	        				<span class="price">$49.91</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img" style="background-image: url(resources/static/images/menu/menu-6.jpg);"></div>
              			<div class="desc pl-3">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3><span>Ultimate Overload</span></h3>
      	        				<span class="price">$20.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img" style="background-image: url(resources/static/images/menu/menu-7.jpg);"></div>
              			<div class="desc pl-3">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3><span>Grilled Beef with potatoes</span></h3>
      	        				<span class="price">$20.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              		<div class="pricing-entry d-flex ftco-animate">
              			<div class="img" style="background-image: url(resources/static/images/menu/menu-8.jpg);"></div>
              			<div class="desc pl-3">
      	        			<div class="d-md-flex text align-items-center">
      	        				<h3><span>Ham &amp; Pineapple</span></h3>
      	        				<span class="price">$20.00</span>
      	        			</div>
      	        			<div class="d-block">
      	        				<p>A small river named Duden flows by their place and supplies</p>
      	        			</div>
      	        		</div>
              		</div>
              	</div>
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