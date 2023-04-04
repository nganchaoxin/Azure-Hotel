<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
<link rel="stylesheet" href="
    <c:url value="/resources/static/css/style.css" />
    ">
    <link rel="stylesheet" href="
        <c:url value="/resources/static/css/footer.css" />
        ">

    <link href='<c:url value="/resources/static/images/Logo_icon.svg" />' rel='shortcut icon'>

	<title>Azure Hotel - About Us</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="hero page-inner overlay" style="background-image: url('<c:url value="/resources/static/css/aboutpage/images/image_bg_7.jpeg" />');">

		<div class="container">
			<div class="row justify-content-center align-items-center">
				<div class="col-lg-9 text-center mt-5">
					<h1 class="heading" data-aos="fade-up">About</h1>

					<nav aria-label="breadcrumb" data-aos="fade-up" data-aos-delay="200">
						<ol class="breadcrumb text-center justify-content-center" style="background: none;">
							<li class="breadcrumb-item "><a href="./">Home</a></li>
							<li class="breadcrumb-item active text-white-50" aria-current="page">About</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>


	<div class="section">
		<div class="container">
			<div class="row text-left mb-5">
				<div class="col-12">
					<h2 class="font-weight-bold heading text-primary mb-4">About Us</h2>
				</div>
				<div class="col-lg-6">
					<p class="text-black-50">Azure is a 4-star luxury hotel that offers guests an exceptional experience from the moment they step inside. With its elegant design, luxurious amenities, and top-notch service, Azure is the perfect choice for discerning travelers looking for a first-class experience.</p>
					<p class="text-black-50">Azure offers a variety of room options to cater to the needs of both families and individuals. For families, the hotel has spacious family rooms and suites that are designed to accommodate up to four guests comfortably. These rooms feature extra bedding options, such as sofa beds or rollaway beds, as well as amenities like mini-fridges and microwaves to make your family's stay more convenient.</p>
					<p class="text-black-50">For individual travelers, Azure has a range of deluxe rooms and suites that offer comfort and style. These rooms are equipped with modern amenities like flat-screen TVs, high-speed internet access, and luxurious bedding to ensure a comfortable and restful stay. </p>
				</div>
				<div class="col-lg-6">

					<p class="text-black-50">Located in the heart of Da Nang, Azure is close to many popular attractions, including the My Khe Beach, Han River Bridge, and Dragon Bridge. The hotel's central location makes it easy for guests to explore the city and experience all that Da Nang has to offer.</p>
					<p class="text-black-50">Overall, if you're planning a trip to Da Nang, Azure is an excellent choice for your accommodations. With its luxurious amenities, convenient location, and exceptional service, it's sure to provide an unforgettable experience for your travels.</p>
				</div>
			</div>

		</div>
	</div>

	<div class="section pt-0">
		<div class="container">
			<div class="row justify-content-between mb-5">
				<div class="col-lg-7 mb-5 mb-lg-0 order-lg-2">
					<div class="img-about dots">

						<img src="<c:url value="/resources/static/css/aboutpage/images/image_bg_4.jpg" />" alt="Image" class="img-fluid">
					</div>
				</div>
				<div class="col-lg-4">
					<div class="d-flex feature-h">
						<span class="wrap-icon me-3">
							<span class="icon-home2"></span>
						</span>
						<div class="feature-text">
							<h3 class="heading">Wonderful Location</h3>
							<p class="text-black-50">Just door steps to dip your feet on the white sand and feel the glistening water. This beach front hotel enables panoramic view to My Khe Beach and Son Tra peninsula...</p>
						</div>
					</div>

					<div class="d-flex feature-h">
						<span class="wrap-icon me-3">
							<span class="icon-person"></span>
						</span>
						<div class="feature-text">
							<h3 class="heading">A good night sleep</h3>
							<p class="text-black-50">All of our spacious suites and apartments are designed with the latest modern conveniences and number of intelligent configurations to suit your needs.</p>
						</div>
					</div>

					<div class="d-flex feature-h">
						<span class="wrap-icon me-3">
							<span class="icon-security"></span>
						</span>
						<div class="feature-text">
							<h3 class="heading">Secret of Relaxations</h3>
							<p class="text-black-50">Spice up your life at Spice Spa. We offer you several exceptional treatments to refresh your body, mind and soul and many packages to shine your beauty.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="section pt-0">
		<div class="container">
			<div class="row justify-content-between mb-5">
				<div class="col-lg-7 mb-5 mb-lg-0">
					<div class="img-about dots">
						<img src="<c:url value="/resources/static/css/aboutpage/images/image_bg_5.jpeg" />" alt="Image" class="img-fluid">
					</div>
				</div>
				<div class="col-lg-4">
					<div class="d-flex feature-h">
						<span class="wrap-icon me-3">
							<span class="icon-home2"></span>
						</span>
						<div class="feature-text">
							<h3 class="heading">Events</h3>
							<p class="text-black-50">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum iste.</p>
						</div>
					</div>

					<div class="d-flex feature-h">
						<span class="wrap-icon me-3">
							<span class="icon-person"></span>
						</span>
						<div class="feature-text">
							<h3 class="heading">Our Foodies</h3>
							<p class="text-black-50">Our culinary team offer variety of delicious food to everyone. From nutritious breakfast, contemporary dining at FishCá, to casual meal in DELIcious or The Top, we cater to all tastes.</p>
						</div>
					</div>

					<div class="d-flex feature-h">
						<span class="wrap-icon me-3">
							<span class="icon-security"></span>
						</span>
						<div class="feature-text">
							<h3 class="heading">For your Kids</h3>
							<p class="text-black-50">Travel with your kids? Just give them a break! We have Kids Club with a variety of fun activities for different ages.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-4" data-aos="fade-up" data-aos-delay="0">
					<img src="<c:url value="/resources/static/css/aboutpage/images/image_bg_6.jpeg" />" alt="Image" class="img-fluid">
				</div>
				<div class="col-md-4 mt-lg-5" data-aos="fade-up" data-aos-delay="100">
					<img src="<c:url value="/resources/static/css/aboutpage/images/image_bg_7.jpeg" />" alt="Image" class="img-fluid">
				</div>
				<div class="col-md-4" data-aos="fade-up" data-aos-delay="200">
					<img src="<c:url value="/resources/static/css/aboutpage/images/image_bg_8.jpeg" />" alt="Image" class="img-fluid">
				</div>
			</div>
			<div class="row section-counter mt-5">
				<div class="col-6 col-sm-6 col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="300">
					<div class="counter-wrap mb-5 mb-lg-0">
						<span class="number"><span class="countup text-primary">2917</span></span>
						<span class="caption text-black-50"># of Buy Properties</span>
					</div>
				</div>
				<div class="col-6 col-sm-6 col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="400">
					<div class="counter-wrap mb-5 mb-lg-0">
						<span class="number"><span class="countup text-primary">3918</span></span>
						<span class="caption text-black-50"># of Sell Properties</span>
					</div>
				</div>
				<div class="col-6 col-sm-6 col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="500">
					<div class="counter-wrap mb-5 mb-lg-0">
						<span class="number"><span class="countup text-primary">38928</span></span>
						<span class="caption text-black-50"># of All Properties</span>
					</div>
				</div>
				<div class="col-6 col-sm-6 col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="600">
					<div class="counter-wrap mb-5 mb-lg-0">
						<span class="number"><span class="countup text-primary">1291</span></span>
						<span class="caption text-black-50"># of Agents</span>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="section sec-testimonials bg-light">
		<div class="container">
			<div class="row mb-5 align-items-center">
				<div class="col-md-6">
					<h2 class="font-weight-bold heading text-primary mb-4 mb-md-0">The Team</h2>
				</div>
				<div class="col-md-6 text-md-end">
					<div id="testimonial-nav">
						<span class="prev" data-controls="prev">Prev</span>

						<span class="next" data-controls="next">Next</span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4">

				</div>
			</div>
			<div class="testimonial-slider-wrap">
				<div class="testimonial-slider">
					<div class="item">
						<div class="testimonial">
							<img src="images/person_1-min.jpg" alt="Image" class="img-fluid rounded-circle w-25 mb-4">
							<h3 class="h5 text-primary">James Smith</h3>
							<p class="text-black-50">Designer, Co-founder</p>

							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>

							<ul class="social list-unstyled list-inline dark-hover">
								<li class="list-inline-item"><a href="#"><span class="icon-twitter"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-facebook"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-linkedin"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-instagram"></span></a></li>
							</ul>

						</div>
					</div>

					<div class="item">
						<div class="testimonial">
							<img src="images/person_2-min.jpg" alt="Image" class="img-fluid rounded-circle w-25 mb-4">
							<h3 class="h5 text-primary">Carol Houston</h3>
							<p class="text-black-50">Designer, Co-founder</p>

							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>

							<ul class="social list-unstyled list-inline dark-hover">
								<li class="list-inline-item"><a href="#"><span class="icon-twitter"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-facebook"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-linkedin"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-instagram"></span></a></li>
							</ul>


						</div>
					</div>

					<div class="item">
						<div class="testimonial">
							<img src="images/person_3-min.jpg" alt="Image" class="img-fluid rounded-circle w-25 mb-4">
							<h3 class="h5 text-primary">Synthia Cameron</h3>
							<p class="text-black-50">Designer, Co-founder</p>

							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>

							<ul class="social list-unstyled list-inline dark-hover">
								<li class="list-inline-item"><a href="#"><span class="icon-twitter"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-facebook"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-linkedin"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-instagram"></span></a></li>
							</ul>


						</div>
					</div>

					<div class="item">
						<div class="testimonial">
							<img src="images/person_4.jpg" alt="Image" class="img-fluid rounded-circle w-25 mb-4">
							<h3 class="h5 text-primary">Davin Smith</h3>
							<p class="text-black-50">Designer, Co-founder</p>

							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>

							<ul class="social list-unstyled list-inline dark-hover">
								<li class="list-inline-item"><a href="#"><span class="icon-twitter"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-facebook"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-linkedin"></span></a></li>
								<li class="list-inline-item"><a href="#"><span class="icon-instagram"></span></a></li>
							</ul>


						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />


    <!-- Preloader -->
    <div id="overlayer"></div>
    <div class="loader">
    	<div class="spinner-border" role="status">
    		<span class="visually-hidden">Loading...</span>
    	</div>
    </div>
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
  </html>
