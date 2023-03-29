<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.bootcss.com/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

    <link href='<c:url value="/resources/static/images/Logo_icon.svg" />' rel='shortcut icon'>
    <link href='<c:url value="/resources/static/css/successpage.css" />' rel='stylesheet'>
    <script src='<c:url value="/resources/static/js/successpage.js" />'></script>
    <title>Tanz Hotel</title>
</head>
<body>
<c:if test="${status.equals('completed')}">
<div class="order-status order-success">
  <div class="top-part">
    <i class="far fa-check-circle"></i>
    <h3>
       Thank you for your reservation.
      <span>Order #: ${newBookingEntity.id}</span>
    </h3>
      <small>
        We have send an email for you to confirm this booking.
        We’re dedicated to giving you the best experience possible. If you have any questions, feel free to get in touch.
      </small>
  </div>
  <ul>
    <li>
      <div>Username</div>
      <div>${accountEntity.username}</div>
    </li>
    <li>
      <div>Check In:</div>
      <div><fmt:formatDate value="${newBookingEntity.getBookingDetailEntities().get(0).getBooking_check_in()}" pattern="dd/MM/yyyy" /></div>
    </li>
    <li>
      <div>Check Out:</div>
      <div><fmt:formatDate value="${newBookingEntity.getBookingDetailEntities().get(0).getBooking_check_out()}" pattern="dd/MM/yyyy" /></div>
    </li>
    <li>
      <div>Total Price:</div>
      <div><fmt:formatNumber value="${newBookingEntity.total_price}" pattern="#,###.##" /> VND</div>
    </li>
    <li>
      <div>Created Date:</div>
      <div><fmt:formatDate value="${newBookingEntity.booking_date}" pattern="dd/MM/yyyy" /></div>
    </li>
    <li>
      <div>Status:</div>
      <div style="color:green;">completed</div>
    </li>
  </ul>
</div>
</c:if>
<c:if test="${status.equals('dismiss')}">
<div class="order-status order-error">
  <div class="top-part">
    <i class="far fa-times-circle"></i>
    <h3>
       Payment Failed
      <span>Order #: 22</span>
    </h3>
      <small>
        An account error occurs. Procedure. Check whether the account balance is sufficient for the calling party.
      </small>
  </div>
  <ul>
    <li>
      <div>Username</div>
      <div>${accountEntity.username}</div>
    </li>
    <li>
      <div>Check In:</div>
      <div><fmt:formatDate value="${cartItemList.get(0).getCheck_in()}" pattern="dd/MM/yyyy" /></div>
    </li>
    <li>
      <div>Check Out:</div>
      <div><fmt:formatDate value="${cartItemList.get(0).getCheck_out()}" pattern="dd/MM/yyyy" /></div>
    </li>
    <li>
      <div>Number Rooms:</div>
      <div>${cartItemList.size()} Rooms</div>
    </li>
    <li>
      <div>Total price:</div>
      <div><fmt:formatNumber value="${totalPrices}" pattern="#,###.##" /> VND</div>
    </li>
    <li>
      <div>Created Date:</div>
      <div><script>
        document.write(new Date().getDate()+"/"+new Date().getMonth()+"/"+new Date().getFullYear());
       </script></div>
    </li>
    <li>
      <div>Status:</div>
      <div style="color:red;">Failed</div>
    </li>
  </ul>
</div>
</c:if>
</body>
</html>