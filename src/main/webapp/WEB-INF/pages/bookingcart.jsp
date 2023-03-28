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
    <link href='<c:url value="/resources/static/css/bookingdetail.css" />' rel='stylesheet'>
    <title>Tanz Hotel</title>
</head>

<body>
    <header>
        <a href="./index"><img src='<c:url value="/resources/static/images/Logo_logo_white.png" />' alt=""
                style="height: 45px; margin-left: 100px;"></a>
    </header>
    <main>
            <div class="container">
                <div class="main-content-left">
                    <c:forEach var="cartItem" items="${cartItemList}">
                        <div class="room-card">
                            <div class="room-card-image">
                                <img src='<c:url value="/resources/static/images/rooms/room-1.jpg" />' alt="">
                            </div>
                            <div class="room-card-content">
                                <div class="room-name">
                                    <h1>${cartItem.roomEntity.room_name}</h1>
                                </div>
                                <div class="room-number_of_person">${cartItem.roomEntity.categoryEntity.max_occupancy} Guestss</div>
                                <div class="room-info">
                                    <div class="room-number_of_bed"><i class="fas fa-user"></i> Sleeps ${cartItem.roomEntity.categoryEntity.max_occupancy}</div>
                                    <div class="room-number_of_double_bed"><i class="fas fa-bed"></i> ${cartItem.roomEntity.categoryEntity.bed_info} Double bed</div>
                                    <div class="room-number_of_bathroom"><i class="fas fa-bath"></i> ${cartItem.roomEntity.categoryEntity.bed_info} Bathroom</div>
                                </div>
                                <div class="room-info-special">
                                    <div class="room-square">${cartItem.roomEntity.categoryEntity.square} m&#178; </div>
                                    <div class="heart_icon"> <i class="fas fa-map-marker"></i></div>
                                    <div class="room-smoking">Non-smoking</div>
                                    <div class="heart_icon"> <i class="fas fa-map-marker"></i></div>
                                    <div class="room-views">Various views</div>
                                </div>
                                <div class="room-cost">
                                    <div class="cost-before"><fmt:formatNumber value="${cartItem.roomEntity.categoryEntity.price*1.2}" pattern="#,###.##" /> VND</div>
                                    <div class="cost-after"><fmt:formatNumber value="${cartItem.roomEntity.categoryEntity.price}" pattern="#,###.##" /> VND</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="customer-dtails">
                        <div id="customer-detail">
                            <div class="dropbtn">
                                <div class="dropbtn-detail">Check Your Information</div>
                                <div class="dropbtn-space"></div>
                                <div class="dropbtn-icon"><i class="fas fa-chevron-down"></i></div>
                            </div>
                        </div>
                        <div id="customer-detail-content">
                            <div class="customer-info">
                                <div class="customer-info_detail">
                                    <div class="customer-info_heading">Full Name:</div>
                                    <div class="customer-info_content">${accountEntity.last_name}
                                        ${accountEntity.first_name}</div>
                                </div>
                                <div class="customer-info_detail">
                                    <div class="customer-info_heading">Birth Date</div>
                                    <div class="customer-info_content"><fmt:formatDate value="${accountEntity.birth_date}" pattern="dd/MM/yyyy" /></div>
                                </div>
                                <div class="customer-info_detail">
                                    <div class="customer-info_heading">Phone Number:</div>
                                    <div class="customer-info_content">${accountEntity.phone_number}</div>
                                </div>
                                <div class="customer-info_detail">
                                    <div class="customer-info_heading">Gender</div>
                                    <div class="customer-info_content">${accountEntity.gender}</div>
                                </div>
                                <div class="customer-info_detail">
                                    <div class="customer-info_heading">Address</div>
                                    <div class="customer-info_content">${accountEntity.address}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="customer-dtails">
                            <div id="customer-review">
                                <div class="dropbtn">
                                    <div class="dropbtn-detail">Check Your Payment Info</div>
                                    <div class="dropbtn-icon"><i class="fas fa-chevron-down"></i></div>
                                </div>
                            </div>
                            <div id="customer-review-content">
                                <c:if test="${payment_status.equals('payment_unavailable')}">
                                    <div class="wrapper">
                                        <div class="payment">
                                            <div class="form">
                                                <form:form action="bookingcart/payment" method="POST"
                                                    modelAttribute="accountBanking">
                                                    <div class="card space icon-relative">
                                                        <label class="label">Full name</label>
                                                        <form:input path="full_name" type="text" class="input"
                                                            placeholder="Full name" required="true" />
                                                        <i class="fas fa-user"></i>
                                                    </div>
                                                    <div class="card space icon-relative">
                                                        <label class="label">Card number:</label>
                                                        <form:input path="card_number" type="text" class="input"
                                                            placeholder="0000 0000 0000 0000" required="true" />
                                                        <i class="far fa-credit-card"></i>
                                                    </div>
                                                    <div class="card-grp space">
                                                        <div class="card-item icon-relative">
                                                            <label class="label">Expired date:</label>
                                                            <form:input path="expired_date" type="text" class="input"
                                                                data-mask="dd/MM/yyyy" placeholder="dd/MM/yyyy" required="true"/>
                                                            <i class="far fa-calendar-alt"></i>
                                                        </div>
                                                        <div class="card-item icon-relative">
                                                            <label class="label">CVV:</label>
                                                            <form:input path="cvv" type="text" class="input" data-mask="000"
                                                                placeholder="000" maxlength="3" required="true"/>
                                                            <i class="fas fa-lock"></i>
                                                        </div>
                                                    </div>

                                                    <button class="btn_submit_payment" type="submit">Add Payment</button>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${payment_status.equals('payment_available')}">
                                    <div class="wrapper">
                                        <div class="payment">
                                            <div class="form">
                                                <form:form action="bookingcart/payment" method="POST"
                                                    modelAttribute="accountBanking">
                                                    <div class="card space icon-relative">
                                                        <label class="label">Full name</label>
                                                        <input type="text" class="input" readonly="readonly"
                                                            value="${accountBanking.full_name}">
                                                        <i class="fas fa-user"></i>
                                                    </div>
                                                    <div class="card space icon-relative">
                                                        <label class="label">Card number:</label>
                                                        <input value="${accountBanking.card_number}" class="input"
                                                            data-mask="0000 0000 0000 0000" placeholder="Card Number"
                                                            readonly="readonly">
                                                        <i class="far fa-credit-card"></i>
                                                    </div>
                                                    <div class="card-grp space">
                                                        <div class="card-item icon-relative">
                                                            <label class="label">Expired date:</label>
                                                            <div class="input"><fmt:formatDate value="${accountBanking.expired_date}" pattern="MM/yy" /> </div>
                                                            <i class="far fa-calendar-alt"></i>
                                                        </div>
                                                        <div class="card-item icon-relative">
                                                            <label class="label">CVV:</label>
                                                            <input value="${accountBanking.cvv}" class="input" data-mask="000"
                                                                placeholder="000" readonly="readonly">
                                                            <i class="fas fa-lock"></i>
                                                        </div>
                                                    </div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                </div>
                <div class="main-content-right">
                    <div class="booking-summary">
                        <div class="booking-summary-info-top">
                            <div class="booking-summary-heading">
                                <div class="room-p_vnd">VND</div>
                                <div class="room-price">
                                    <fmt:formatNumber value="${totalPrices}" pattern="#,###.##" />
                                </div>
                                <div class="room-p_total">total</div>
                            </div>
                            <div class="booking-summary-date_and_staynight">
                                <div class="booking_checkin"><fmt:formatDate value="${cartItemList.get(0).getCheck_in()}" pattern="dd MMM yy"/></div>
                                <div class="booking-to" style="margin:0px .1em;">-</div>
                                <div class="booking_checkout"><fmt:formatDate value="${cartItemList.get(0).getCheck_out()}" pattern="dd MMM yy"/></div>
                                <div class="booking_space"></div>
                                <div class="booking_staynight">${totalDays} night</div>
                            </div>
                            <div class="booking-summary-accupancy-room">
                                <div class="booking-summary-accupancy-number_of_room">${cartItemList.size()} room,</div>
                                <div class="booking-summary-accupancy-number_of_guest">${totalGuests} guests</div>
                            </div>
                            <div class="booking-summary-room_info"></div>
                        </div>
                        <div class="booking-summary-stay-detail">
                            <div class="booking-summary-stay-detail-heading">Stay details</div>
                            <div class="booking-summary-stay-detail-room-detail-list">
                                <c:forEach var="cartItem" items="${cartItemList}">
                                    <div class="booking-summary-stay-detail-room-detail">
                                        <div class="booking-summary-stay-detail-room-detail_name">
                                            ${cartItem.roomEntity.room_name}</div>
                                        <div class="booking-summary-stay-detail-room-detail_info">
                                            <div class="booking-summary-stay-detail-room-detail_info_guest">
                                                ${cartItem.roomEntity.categoryEntity.max_occupancy} guests</div>
                                            <div class="booking-summary-stay-detail-room-detail_info_staynight">1 night
                                            </div>
                                            <div class="booking-summary-stay-detail-room-detail_info_space"></div>
                                            <div class="booking-summary-stay-detail-room-detail_info_price">VND
                                                <fmt:formatNumber value="${cartItem.roomEntity.categoryEntity.price}"
                                                    pattern="#,###.##" />
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="booking-summary-stay-detail-total">
                                <div class="booking-summary-stay-detail-total_total">Total</div>
                                <div class="booking-summary-stay-detail-total_space"></div>
                                <div class="booking-summary-stay-detail-total_totalprice">VND
                                    <fmt:formatNumber value="${totalPrices}" pattern="#,###.##" />
                                </div>
                            </div>
                        </div>
                        <c:if test="${payment_status.equals('payment_available')}">
                            <div class="customer_note">
                                <input name="checkout_note" class="input_note" type='text' placeholder='Enter your note...' />
                            </div>
                            <div class="button_checkout">

                                <form:form action="bookingcart/checkout" method="POST">
                                    <button type="submit" class="btn_checkout">Check Out</button>
                                </form:form>

                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </main>
</body>
<script>
    $(document).ready(function () {
        $("#customer-detail").click(function () {
            $("#customer-detail-content").slideToggle("slow");
        });
    });
</script>
<script>
    $(document).ready(function () {
        $("#customer-review").click(function () {
            $("#customer-review-content").slideToggle("slow");
        });
    });
</script>

</html>