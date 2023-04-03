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


<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Azure Hotel | Hotel in Da Nang</title>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://cdn.bootcss.com/font-awesome/5.11.2/css/all.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    />


    <link
      href='<c:url value="/resources/static/images/Logo_icon.svg" />'
      rel="shortcut icon"
    />

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
      crossorigin="anonymous"
    />

    <link
      href='<c:url value="/resources/static/css/search.css" />'
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="resources/static/assets/vendor/css/pages/page-misc.css"
    />

  </head>

  <body>
    <header>
      <nav class="navbar navbar-expand-lg bg-body-tertiary py-0">
        <div
          class="container-fluid py-2 header-azure"
          style="background-color: #cca772"
        >
          <a class="px-2" href="/Azure-Hotel"
            ><img
              src='<c:url value="/resources/static/images/Logo_logo_white.png" />'
              alt=""
              style="height: 45px; margin-left: 100px"
          /></a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav align-items-center pt-2">
              <a
                class="nav-link active"
                style="color: white"
                aria-current="page"
                href="#"
                >Availability</a
              >

              <sec:authorize access="isAuthenticated()">
                  <sec:authorize access="hasRole('ROLE_USER')">
                      <a class="nav-link d-flex align-items-center  "style="color: white" href="<c:url value="/bookingcart" />">My Cart
                        <span class="cart-basket d-flex align-items-center justify-content-center" style="height: 1.4em;border-radius: 50%;width: 1.4em;background-color: #fff700b8;position: relative;top: -18px;left:0px;font-size: 105%;color: #a67f01;font-weight: 500">
                          ${cartItemList.size()}
                        </span>
                       </a>
                  </sec:authorize>
              </sec:authorize>

              <a class="nav-link" style="color: white" href="./">Home</a>
              <a class="nav-link" style="color: white" href="#">Contact</a>
              <a class="nav-link" style="color: white" href="#">Policies</a>
            </div>
          </div>
        </div>
      </nav>
    </header>
    <c:if test="${not empty availableRoomList}">
      <div
        class="container"
        style="
          margin-bottom: 50px;
          margin-top: 50px;
          padding-left: 20%;
          padding-right: 20%;
        "
      >
        <div class="row">

            <div class="room-card">
              <div class="row">

                <div class="col-6 px-0">
                  <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <c:forEach items="${imageList}" var="image" varStatus="status">
                            <li data-target="#myCarousel" data-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
                        </c:forEach>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <c:forEach items="${imageList}" var="image" varStatus="status">
                            <div class="item ${status.index == 0 ? 'active' : ''}">
                                <img style="
                                   width: 400px;
                                   height: 220px;
                                   border-radius: 10px 10px 10px 10px;
                                 " src="getImagePhoto/${image.id}" />
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                </div>
                <div class="col-6 py-4 ">
                  <div class="card-body">
                    <h1>
                      ${availableRoomList[0].categoryEntity.category_name}
                    </h1>
                    <div class="room-info">
                      <div class="room-number_of_bed">
                        <i class="fas fa-user"></i> Max
                        ${availableRoomList[0].categoryEntity.max_occupancy}
                        guests
                      </div>
                      <div class="room-number_of_double_bed">
                        <i class="fas fa-bed"></i>
                        ${availableRoomList[0].categoryEntity.bed_info} Bed(s)
                      </div>
                      <div class="room-number_of_bathroom">
                        <i class="fas fa-bath"></i> 1 Bathroom
                      </div>
                    </div>
                    <div class="room-info-special">
                      <div class="room-square">
                        ${availableRoomList[0].categoryEntity.square} m&#178;
                      </div>
                      <div class="heart_icon">
                        <i class="fas fa-map-marker"></i>
                      </div>
                      <div class="room-smoking">Non-smoking</div>
                      <div class="heart_icon">
                        <i class="fas fa-map-marker"></i>
                      </div>
                      <div class="room-views">
                        ${availableRoomList[0].position}
                      </div>
                    </div>


                  </div>
                </div>
              </div>
            </div>
            <div class="container" style="padding-left: 1%; padding-right: 2%;">
              <c:forEach items="${availableRoomList}" var="room">
              <div class="room-card-2 px-4 py-4">
                <h1>${room.room_name}</h1>
                <div class="room-cost ">
                    <div class="col-6 text-end">
                      <div class="cost-before">
                        <fmt:formatNumber
                          value="${availableRoomList[0].categoryEntity.price*1.2}"
                          pattern="#,###.##"
                        />
                        VND
                      </div>
                      <div class="cost-after">
                        <fmt:formatNumber
                          value="${availableRoomList[0].categoryEntity.price}"
                          pattern="#,###.##"
                        />
                        VND
                      </div>
                    </div>


                  <div style="margin-left: 10px; padding-top: 10px;">
                    <button
                      style="
                        background-color: #cca772;
                        border: none;
                        --bs-btn-font-size: 18px;
                      "
                      onclick="location.href='addToCart/room=${room.id}'"
                      href="#"
                      class="btn btn-primary btn-lg float-end"
                    >
                      I'll reserve
                    </button>
                  </div>
                </div>
              </div>
            </c:forEach>
            </div>
        </div>
        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage > 0}">
                        <li class="page-item"><a class="page-link" href="?checkin=${param.checkin}&checkout=${param.checkout}&roomType=${param.roomType}&guests=${param.guests}&page=${currentPage - 1}">&laquo;</a></li>
                    </c:if>
                    <c:forEach begin="0" end="${totalPages - 1}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}"><a class="page-link" href="?checkin=${param.checkin}&checkout=${param.checkout}&roomType=${param.roomType}&guests=${param.guests}&page=${i}">${i + 1}</a></li>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages - 1}">
                        <li class="page-item"><a class="page-link" href="?checkin=${param.checkin}&checkout=${param.checkout}&roomType=${param.roomType}&guests=${param.guests}&page=${currentPage + 1}">&raquo;</a></li>
                    </c:if>
                </ul>
            </div>
        </c:if>
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

     <script>
          $(document).ready(function () {
              // Activate the first slide
              $('.carousel-inner .item:first-child').addClass('active');

              // Loop through each slide
              $('.carousel').carousel({
                  interval: 2000 // Change the interval time here
              });
          });
      </script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </body>
</html>
