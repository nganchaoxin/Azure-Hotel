<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

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
            <div class="navbar-nav">
              <a
                class="nav-link active"
                style="color: white"
                aria-current="page"
                href="#"
                >Availability</a
              >
              <a class="nav-link" style="color: white" href="./myCart/cartId=1"
                >My Cart</a
              >
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
          <div class="">
            <c:forEach
              var="room"
              items="${availableRoomList}"
              varStatus="index"
            >
              <div class="room-card">
                <div class="row">
                  <div class="col-6">
                    <!-- <img style="width: 250px; height: auto;" src="../images/room-1.jpg" class="card-img-top" alt="..."> -->
                    <div
                      id="carouselExampleFade"
                      class="carousel slide carousel-fade my-auto"
                    >
                      <div class="carousel-inner my-auto mx-auto">
                        <div class="carousel-item active">
                          <img
                            style="
                              width: 250px;
                              height: 220px;
                              border-radius: 20px 20px 20px 20px;
                            "
                            src='<c:url value="/resources/static/images/rooms/room-1.jpg" />'
                            class="d-block w-100"
                            alt="..."
                          />
                        </div>

                        <div class="carousel-item">
                          <img
                            style="
                              width: 250px;
                              height: 220px;
                              border-radius: 20px 20px 20px 20px;
                            "
                            src='<c:url value="/resources/static/images/rooms/room-2.jpg" />'
                            class="d-block w-100"
                            alt="..."
                          />
                        </div>
                        <div class="carousel-item">
                          <img
                            style="
                              width: 250px;
                              height: 220px;
                              border-radius: 20px 20px 20px 20px;
                            "
                            src='<c:url value="/resources/static/images/rooms/room-3.jpg" />'
                            class="d-block w-100"
                            alt="..."
                          />
                        </div>
                      </div>
                      <button
                        class="carousel-control-prev"
                        type="button"
                        data-bs-target="#carouselExampleFade"
                        data-bs-slide="prev"
                      >
                        <span
                          class="carousel-control-prev-icon"
                          aria-hidden="true"
                        ></span>
                        <span class="visually-hidden">Previous</span>
                      </button>
                      <button
                        class="carousel-control-next"
                        type="button"
                        data-bs-target="#carouselExampleFade"
                        data-bs-slide="next"
                      >
                        <span
                          class="carousel-control-next-icon"
                          aria-hidden="true"
                        ></span>
                        <span class="visually-hidden">Next</span>
                      </button>
                    </div>
                  </div>
                  <div class="col-6 py-4">
                    <div class="card-body">
                        <h1>${room.categoryEntity.category_name} | ${room.room_name}</h1>
                      <div class="room-info">
                        <div class="room-number_of_bed">
                          <i class="fas fa-user"></i> Max ${room.categoryEntity.max_occupancy} guests
                        </div>
                        <div class="room-number_of_double_bed">
                          <i class="fas fa-bed"></i> ${room.categoryEntity.bed_info} Bed(s)
                        </div>
                        <div class="room-number_of_bathroom">
                          <i class="fas fa-bath"></i> 1 Bathroom
                        </div>
                      </div>
                      <div class="room-info-special">
                        <div class="room-square">${room.categoryEntity.square} m&#178;</div>
                        <div class="heart_icon">
                          <i class="fas fa-map-marker"></i>
                        </div>
                        <div class="room-smoking">Non-smoking</div>
                        <div class="heart_icon">
                          <i class="fas fa-map-marker"></i>
                        </div>
                        <div class="room-views">${room.position}</div>
                      </div>
                      <div class="room-cost">
                        <div class="cost-before"><fmt:formatNumber value="${room.categoryEntity.price*1.2}" pattern="#,###.##" /> VND</div>
                        <div class="cost-after"><fmt:formatNumber value="${room.categoryEntity.price}" pattern="#,###.##" /> VND</div>
                      </div>
                      <div style="padding-right:25px; padding-top: 10px;">
                        <button
                           style="background-color: #cca772; border:none; --bs-btn-font-size: 18px;"
                          onclick="location.href='addToCart/room=${room.id}'"
                          href="#"
                          class="btn btn-primary btn-lg float-end "
                        >
                          I'll reserve
                        </button>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${empty availableRoomList}">
      <p>No available rooms found.</p>
    </c:if>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
      crossorigin="anonymous"
    ></script>
    <script src="js/main.js"></script>
  </body>
</html>
