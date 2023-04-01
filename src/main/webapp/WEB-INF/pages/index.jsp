<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>Azure Hotel | Hotel in Da Nang</title>
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

    <section class="home-slider owl-carousel">
      <div
        class="slider-item"
        style="background-image: url(resources/static/images/hero_1.jpg)"
      >
        <div class="overlay"></div>
        <div class="container">
          <div
            class="row no-gutters slider-text align-items-center justify-content-center"
          >
            <div class="col-md-12 ftco-animate text-center">
              <div class="text mb-5 pb-3">
                <h1 class="mb-3">Welcome To Azure</h1>
                <h2>Hotels &amp; Resorts</h2>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="slider-item"
        style="background-image: url(resources/static/images/hero_2.jpg)"
      >
        <div class="overlay"></div>
        <div class="container">
          <div
            class="row no-gutters slider-text align-items-center justify-content-center"
          >
            <div class="col-md-12 ftco-animate text-center">
              <div class="text mb-5 pb-3">
                <h1 class="mb-3">Enjoy A Luxury Experience</h1>
                <h2>Join With Us</h2>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-booking">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <form:form action="availableRoom" method="get" class="booking-form">
              <div class="row">
                <div class="col-md-3 d-flex">
                  <div
                    class="form-group p-4 align-self-stretch d-flex align-items-end"
                  >
                    <div class="wrap">
                      <label for="#">Check-in Date</label>
                      <input
                        name="checkin"
                        type="date"
                        class="form-control"
                        placeholder="Check-in date"
                        id="date-input-checkin"
                        required
                      />
                    </div>
                  </div>
                </div>
                <div class="col-md-3 d-flex">
                  <div
                    class="form-group p-4 align-self-stretch d-flex align-items-end"
                  >
                    <div class="wrap">
                      <label for="#">Check-out Date</label>
                      <input
                        name="checkout"
                        type="date"
                        class="form-control"
                        placeholder="Check-out date"
                        id="date-input-checkout"
                        required
                      />
                    </div>
                  </div>
                </div>
                <div class="col-md d-flex">
                  <div
                    class="form-group p-4 align-self-stretch d-flex align-items-end"
                  >
                    <div class="wrap">
                      <label for="#">Room</label>
                      <div class="form-field">
                        <div class="select-wrap">
                          <div class="icon">
                            <span class="ion-ios-arrow-down"></span>
                          </div>
                          <select name="roomType" id="" class="form-control" required>

                            <option value="Standard">Standard</option>
                            <option value="Family">Family</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md d-flex">
                  <div
                    class="form-group p-4 align-self-stretch d-flex align-items-end"
                  >
                    <div class="wrap">
                      <label for="#">Customer</label>
                      <div class="form-field">
                        <div class="select-wrap">
                          <div class="icon">
                            <span class="ion-ios-arrow-down"></span>
                          </div>
                          <select name="guests" id="" class="form-control" required>
                            <option value="1">1 Adult</option>
                            <option value="2">2 Adult</option>
                            <option value="3">3 Adult</option>
                            <option value="4">4 Adult</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md d-flex">
                  <div class="form-group d-flex align-self-stretch">
                    <input
                      type="submit"
                      value="Check Availability"
                      class="btn btn-primary py-3 px-4 align-self-stretch"
                    />
                  </div>
                </div>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section ftc-no-pb ftc-no-pt">
      <div class="container">
        <div class="row">
          <div
            class="col-md-5 p-md-5 img img-2 d-flex justify-content-center align-items-center"
            style="background-image: url(resources/static/images/hero_2.jpg)"
          >
            <a
              href="https://www.youtube.com/watch?v=ism1XqnZJEg"
              class="icon popup-vimeo d-flex justify-content-center align-items-center"
            >
              <span class="icon-play"></span>
            </a>
          </div>
          <div class="col-md-7 py-5 wrap-about pb-md-5 ftco-animate">
            <div
              class="heading-section heading-section-wo-line pt-md-5 pl-md-5 mb-5"
            >
              <div class="ml-md-0">
                <span class="subheading">Welcome to Azure Hotel</span>
                <h2 class="mb-4">Welcome To Our Hotel</h2>
              </div>
            </div>
            <div class="pb-md-5">
              <p>
                On her way she met a copy. The copy warned the Little Blind
                Text, that where it came from it would have been rewritten a
                thousand times and everything that was left from its origin
                would be the word "and" and the Little Blind Text should turn
                around and return to its own, safe country. But nothing the copy
                said could convince her and so it didn’t take long until a few
                insidious Copy Writers ambushed her, made her drunk with Longe
                and Parole and dragged her into their agency, where they abused
                her for their.
              </p>
              <p>
                When she reached the first hills of the Italic Mountains, she
                had a last view back on the skyline of her hometown
                Bookmarksgrove, the headline of Alphabet Village and the subline
                of her own road, the Line Lane. Pityful a rethoric question ran
                over her cheek, then she continued her way.
              </p>
              <ul class="ftco-social d-flex">
                <li class="ftco-animate">
                  <a href="#"><span class="icon-twitter"></span></a>
                </li>
                <li class="ftco-animate">
                  <a href="#"><span class="icon-facebook"></span></a>
                </li>
                <li class="ftco-animate">
                  <a href="#"><span class="icon-google-plus"></span></a>
                </li>
                <li class="ftco-animate">
                  <a href="#"><span class="icon-instagram"></span></a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section bg-light">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section text-center ftco-animate">
            <h2 class="mb-4">Our Rooms</h2>
          </div>
        </div>
        <div class="row">
          <c:forEach var="category" items="${categoryList}" varStatus="index">
            <div class="col-sm col-md-6 col-lg-4 ftco-animate">
              <div class="room">
                <a
                  href="rooms.html"
                  class="img d-flex justify-content-center align-items-center"
                  style="
                    background-image: url(resources/static/images/rooms/room-1.jpg);
                  "
                >
                  <div
                    class="icon d-flex justify-content-center align-items-center"
                  >
                    <span class="icon-search2"></span>
                  </div>
                </a>
                <div class="text p-3 text-center">
                  <h3 class="mb-3">
                    <a href="rooms.html">${category.category_name}</a>
                  </h3>

                  <hr />
                  <p class="pt-1">
                    <a href="room-single.html" class="btn-custom"
                      >View Room Details
                      <span class="icon-long-arrow-right"></span
                    ></a>
                  </p>
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </section>

    <div class="facilities">
      <div class="row mx-0">
        <div class="tabs">
          <button class="tab-button active" data-tab="pool">Pool</button>
          <button class="tab-button" data-tab="gym">Gym</button>
          <button class="tab-button" data-tab="spa">Spa</button>
        </div>
      </div>
      <div class="row mx-0">
        <div class="tab-content">
          <div class="tab-item active" data-tab="pool">
            <img src="resources/static/images/rooms/room-1.jpg" alt="Pool" />
            <p>
              Our outdoor pool is open year-round and features a water slide and
              hot tub.
            </p>
          </div>
          <div class="tab-item" data-tab="gym">
            <img src="resources/static/images/rooms/room-1.jpg" alt="Gym" />
            <p>
              Our gym is fully equipped with free weights, cardio machines, and
              strength-training equipment.
            </p>
          </div>
          <div class="tab-item" data-tab="spa">
            <img src="resources/static/images/rooms/room-1.jpg" alt="Spa" />
            <p>
              Relax and rejuvenate with our full-service spa offering massages,
              facials, and body treatments.
            </p>
          </div>
        </div>
      </div>
    </div>
    <section class="why-choose-us">
      <div class="container">
        <h2 class="section-title">Why Choose Us?</h2>
        <div class="container">
          With a fresh modern design and a surprising concept, À La Carte Da
          Nang Beach is comprised of vibrant and spacious sea-view suites at
          amazing prices with a creative à la carte add-on concept, and fun
          casual service. Stay at À La Carte Da Nang Beach and choose from
          several suite styles that feature studios, one or two bedroom options
          and balconies with mountain and sea views... view more
        </div>
        <div class="row">
          <div class="col-md-4">
            <div class="card">
              <img
                src="resources/static/images/rooms/room-1.jpg"
                alt="Image 1"
              />
              <h3>Comfortable Rooms</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card">
              <img
                src="resources/static/images/rooms/room-1.jpg"
                alt="Image 2"
              />
              <h3>Excellent Service</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </div>
          </div>
          <div class="col-md-4">
            <div class="card">
              <img
                src="resources/static/images/rooms/room-1.jpg"
                alt="Image 3"
              />
              <h3>Convenient Location</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section
      class="ftco-section ftco-counter img"
      id="section-counter"
      style="background-image: url(resources/static/images/hero_1.jpg)"
    >
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-10">
            <div class="row">
              <div
                class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate"
              >
                <div class="block-18 text-center">
                  <div class="text">
                    <strong class="number" data-number="50000">0</strong>
                    <span>Happy Guests</span>
                  </div>
                </div>
              </div>
              <div
                class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate"
              >
                <div class="block-18 text-center">
                  <div class="text">
                    <strong class="number" data-number="3000">0</strong>
                    <span>Rooms</span>
                  </div>
                </div>
              </div>
              <div
                class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate"
              >
                <div class="block-18 text-center">
                  <div class="text">
                    <strong class="number" data-number="1000">0</strong>
                    <span>Staffs</span>
                  </div>
                </div>
              </div>
              <div
                class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate"
              >
                <div class="block-18 text-center">
                  <div class="text">
                    <strong class="number" data-number="100">0</strong>
                    <span>Destination</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section testimony-section bg-light">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-8 ftco-animate">
            <div class="row ftco-animate">
              <div class="col-md-12">
                <div class="carousel-testimony owl-carousel ftco-owl">
                  <div class="item">
                    <div class="testimony-wrap py-4 pb-5">
                      <div
                        class="user-img mb-4"
                        style="
                          background-image: url(resources/static/images/persons/person_1.jpg);
                        "
                      >
                        <span
                          class="quote d-flex align-items-center justify-content-center"
                        >
                          <i class="icon-quote-left"></i>
                        </span>
                      </div>
                      <div class="text text-center">
                        <p class="mb-4">
                          A small river named Duden flows by their place and
                          supplies it with the necessary regelialia. It is a
                          paradisematic country, in which roasted parts of
                          sentences fly into your mouth.
                        </p>
                        <p class="name">Nathan Smith</p>
                        <span class="position">Guests</span>
                      </div>
                    </div>
                  </div>
                  <div class="item">
                    <div class="testimony-wrap py-4 pb-5">
                      <div
                        class="user-img mb-4"
                        style="
                          background-image: url(resources/static/images/persons/person_2.jpg);
                        "
                      >
                        <span
                          class="quote d-flex align-items-center justify-content-center"
                        >
                          <i class="icon-quote-left"></i>
                        </span>
                      </div>
                      <div class="text text-center">
                        <p class="mb-4">
                          A small river named Duden flows by their place and
                          supplies it with the necessary regelialia. It is a
                          paradisematic country, in which roasted parts of
                          sentences fly into your mouth.
                        </p>
                        <p class="name">Nathan Smith</p>
                        <span class="position">Guests</span>
                      </div>
                    </div>
                  </div>
                  <div class="item">
                    <div class="testimony-wrap py-4 pb-5">
                      <div
                        class="user-img mb-4"
                        style="
                          background-image: url(resources/static/images/persons/person_3.jpg);
                        "
                      >
                        <span
                          class="quote d-flex align-items-center justify-content-center"
                        >
                          <i class="icon-quote-left"></i>
                        </span>
                      </div>
                      <div class="text text-center">
                        <p class="mb-4">
                          A small river named Duden flows by their place and
                          supplies it with the necessary regelialia. It is a
                          paradisematic country, in which roasted parts of
                          sentences fly into your mouth.
                        </p>
                        <p class="name">Nathan Smith</p>
                        <span class="position">Guests</span>
                      </div>
                    </div>
                  </div>
                  <div class="item">
                    <div class="testimony-wrap py-4 pb-5">
                      <div
                        class="user-img mb-4"
                        style="
                          background-image: url(resources/static/images/persons/person_1.jpg);
                        "
                      >
                        <span
                          class="quote d-flex align-items-center justify-content-center"
                        >
                          <i class="icon-quote-left"></i>
                        </span>
                      </div>
                      <div class="text text-center">
                        <p class="mb-4">
                          A small river named Duden flows by their place and
                          supplies it with the necessary regelialia. It is a
                          paradisematic country, in which roasted parts of
                          sentences fly into your mouth.
                        </p>
                        <p class="name">Nathan Smith</p>
                        <span class="position">Guests</span>
                      </div>
                    </div>
                  </div>
                  <div class="item">
                    <div class="testimony-wrap py-4 pb-5">
                      <div
                        class="user-img mb-4"
                        style="
                          background-image: url(resources/static/images/persons/person_1.jpg);
                        "
                      >
                        <span
                          class="quote d-flex align-items-center justify-content-center"
                        >
                          <i class="icon-quote-left"></i>
                        </span>
                      </div>
                      <div class="text text-center">
                        <p class="mb-4">
                          A small river named Duden flows by their place and
                          supplies it with the necessary regelialia. It is a
                          paradisematic country, in which roasted parts of
                          sentences fly into your mouth.
                        </p>
                        <p class="name">Nathan Smith</p>
                        <span class="position">Guests</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section text-center ftco-animate">
            <h2>Recent Blog</h2>
          </div>
        </div>
        <div class="row d-flex">
          <div class="col-md-3 d-flex ftco-animate">
            <div class="blog-entry align-self-stretch">
              <a
                href="blog-single.html"
                class="block-20"
                style="
                  background-image: url('resources/static/images/rooms/room-1.jpg');
                "
              >
              </a>
              <div class="text mt-3 d-block">
                <h3 class="heading mt-3">
                  <a href="#"
                    >Even the all-powerful Pointing has no control about the
                    blind texts</a
                  >
                </h3>
                <div class="meta mb-3">
                  <div><a href="#">Dec 6, 2018</a></div>
                  <div><a href="#">Admin</a></div>
                  <div>
                    <a href="#" class="meta-chat"
                      ><span class="icon-chat"></span> 3</a
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex ftco-animate">
            <div class="blog-entry align-self-stretch">
              <a
                href="blog-single.html"
                class="block-20"
                style="
                  background-image: url('resources/static/images/rooms/room-1.jpg');
                "
              >
              </a>
              <div class="text mt-3">
                <h3 class="heading mt-3">
                  <a href="#"
                    >Even the all-powerful Pointing has no control about the
                    blind texts</a
                  >
                </h3>
                <div class="meta mb-3">
                  <div><a href="#">Dec 6, 2018</a></div>
                  <div><a href="#">Admin</a></div>
                  <div>
                    <a href="#" class="meta-chat"
                      ><span class="icon-chat"></span> 3</a
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex ftco-animate">
            <div class="blog-entry align-self-stretch">
              <a
                href="blog-single.html"
                class="block-20"
                style="
                  background-image: url('resources/static/images/rooms/room-1.jpg');
                "
              >
              </a>
              <div class="text mt-3">
                <h3 class="heading mt-3">
                  <a href="#"
                    >Even the all-powerful Pointing has no control about the
                    blind texts</a
                  >
                </h3>
                <div class="meta mb-3">
                  <div><a href="#">Dec 6, 2018</a></div>
                  <div><a href="#">Admin</a></div>
                  <div>
                    <a href="#" class="meta-chat"
                      ><span class="icon-chat"></span> 3</a
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex ftco-animate">
            <div class="blog-entry align-self-stretch">
              <a
                href="blog-single.html"
                class="block-20"
                style="
                  background-image: url('resources/static/images/rooms/room-1.jpg');
                "
              >
              </a>
              <div class="text mt-3">
                <h3 class="heading mt-3">
                  <a href="#"
                    >Even the all-powerful Pointing has no control about the
                    blind texts</a
                  >
                </h3>
                <div class="meta mb-3">
                  <div><a href="#">Dec 6, 2018</a></div>
                  <div><a href="#">Admin</a></div>
                  <div>
                    <a href="#" class="meta-chat"
                      ><span class="icon-chat"></span> 3</a
                    >
                  </div>
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