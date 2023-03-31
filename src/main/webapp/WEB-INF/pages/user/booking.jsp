<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

   <title>Azure Hotel - Profile Management</title>
  <meta name="description" content="asdas" />

  <!-- Favicon -->
  <link rel="icon" type="image/x-icon" href='<c:url value="/resources/static/assets/img/favicon/favicon.ico" />' />

  <!-- Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
    href='<c:url value="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" />'
    rel="stylesheet" />


  <!-- Icons. Uncomment required icon fonts -->
  <link href='<c:url value="/resources/static/assets/vendor/fonts/boxicons.css" />' rel='stylesheet'>

  <!-- Core CSS -->
  <link href='<c:url value="/resources/static/assets/vendor/css/core.css" />' class="template-customizer-core-css" rel='stylesheet'>
  <link href='<c:url value="/resources/static/assets/vendor/css/theme-default.css" />' class="template-customizer-theme-css" rel='stylesheet'>
  <link href='<c:url value="/resources/static/assets/assets/css/demo.css" />' rel='stylesheet'>

  <!-- Vendors CSS -->
  <link href='<c:url value="/resources/static/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />' rel='stylesheet'>

  <script src='<c:url value="/resources/static/assets/vendor/js/helpers.js" />'></script>
  <script src='<c:url value="/resources/static/assets/js/config.js" />'></script>
</head>

<body>
  <!-- Layout wrapper -->
  <div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
      <!-- Menu -->

      <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
        <div class="app-brand demo" style="padding-top:1em;">
          <a href="../" class="app-brand-link">
            <span class="app-brand-text demo menu-text fw-bolder ms-2"><img src='<c:url value="/resources/static/assets/img/Logo_logo.png" />' alt=""
                width="190px"></span>
          </a>

          <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
            <i class="bx bx-chevron-left bx-sm align-middle"></i>
          </a>
        </div>

        <div class="menu-inner-shadow"></div>

        <ul class="menu-inner py-1">
          <!-- Dashboard -->

          <li class="menu-header small text-uppercase">
            <span class="menu-header-text">Account Management</span>
          </li>
          <li class="menu-item">
            <a href="javascript:void(0);" class="menu-link menu-toggle">
              <i class="menu-icon tf-icons bx bx-dock-top"></i>
              <div data-i18n="Account Settings">Account Settings</div>
            </a>
            <ul class="menu-sub">
              <li class="menu-item">
                <a href="account" class="menu-link">
                  <div data-i18n="Account">Account</div>
                </a>
              </li>
              <li class="menu-item">
                <a href="forgotpassword" class="menu-link">
                  <div data-i18n="Notifications">Forgot password</div>
                </a>
              </li>
            </ul>
          </li>
          <li class="menu-item">
            <a href="javascript:void(0);" class="menu-link menu-toggle">
              <i class="menu-icon tf-icons bx bx-lock-open-alt"></i>
              <div data-i18n="Authentications">Payment Info</div>
            </a>
            <ul class="menu-sub">
              <li class="menu-item">
                <a href="cardpayment" class="menu-link">
                  <div data-i18n="Basic">Your Card</div>
                </a>
              </li>
              <li class="menu-item">
                <a href="paymenthistory" class="menu-link">
                  <div data-i18n="Basic">History Payment</div>
                </a>
              </li>
            </ul>
          </li>
          <!-- Components -->
          <li class="menu-header small text-uppercase"><span class="menu-header-text">Booking Managerment</span></li>
          <!-- Cards -->
          <li class="menu-item active open">
            <a href="booking" class="menu-link">
              <i class="menu-icon tf-icons bx bx-collection"></i>
              <div data-i18n="Basic">Booking</div>
            </a>
          </li>
        </ul>
      </aside>
      <!-- / Menu -->

      <!-- Layout container -->
      <div class="layout-page">
        <!-- Navbar -->

        <nav
          class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
          id="layout-navbar">
          <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
            <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
              <i class="bx bx-menu bx-sm"></i>
            </a>
          </div>

          <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
            <!-- Search -->
            <div class="navbar-nav align-items-center">
              <div class="nav-item d-flex align-items-center">
                <i class="bx bx-search fs-4 lh-0"></i>
                <input type="text" class="form-control border-0 shadow-none" placeholder="Search..."
                  aria-label="Search..." />
              </div>
            </div>
            <!-- /Search -->

            <ul class="navbar-nav flex-row align-items-center ms-auto">
              <!-- Place this tag where you want the button to render. -->
              <li class="nav-item lh-1 me-3">
                <a class="github-button" href="https://github.com/themeselection/sneat-html-admin-template-free"
                  data-icon="octicon-star" data-size="large" data-show-count="true"
                  aria-label="Star themeselection/sneat-html-admin-template-free on GitHub">Star</a>
              </li>

              <!-- User -->
              <li class="nav-item navbar-dropdown dropdown-user dropdown">
                <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                  <div class="avatar avatar-online">
                    <img src='<c:url value="/resources/static/assets/img/avatars/1.png" />' alt class="w-px-40 h-auto rounded-circle" />

                  </div>
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li>
                    <a class="dropdown-item" href="#">
                      <div class="d-flex">
                        <div class="flex-shrink-0 me-3">
                          <div class="avatar avatar-online">
                            <img src='<c:url value="/resources/static/assets/img/avatars/1.png" />' alt class="w-px-40 h-auto rounded-circle" />

                          </div>
                        </div>
                        <div class="flex-grow-1">
                          <span class="fw-semibold d-block">John Doe</span>
                          <small class="text-muted">Admin</small>
                        </div>
                      </div>
                    </a>
                  </li>
                  <li>
                    <div class="dropdown-divider"></div>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      <i class="bx bx-user me-2"></i>
                      <span class="align-middle">My Profile</span>
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      <i class="bx bx-cog me-2"></i>
                      <span class="align-middle">Settings</span>
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      <span class="d-flex align-items-center align-middle">
                        <i class="flex-shrink-0 bx bx-credit-card me-2"></i>
                        <span class="flex-grow-1 align-middle">Billing</span>
                        <span class="flex-shrink-0 badge badge-center rounded-pill bg-danger w-px-20 h-px-20">4</span>
                      </span>
                    </a>
                  </li>
                  <li>
                    <div class="dropdown-divider"></div>
                  </li>
                  <li>
                    <a class="dropdown-item" href="auth-login-basic.html">
                      <i class="bx bx-power-off me-2"></i>
                      <span class="align-middle">Log Out</span>
                    </a>
                  </li>
                </ul>
              </li>
              <!--/ User -->
            </ul>
          </div>
        </nav>

        <!-- / Navbar -->
        <div class="content-wrapper">
          <!-- Content -->

          <div class="container-xxl flex-grow-1 container-p-y">
            <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Booking /</span> Your Booking</h4>

            <!-- Basic Bootstrap Table -->
            <div class="card">
              <h5 class="card-header">Your Booking Information
                  <div class="alert alert-success">${msg}</div>
              </h5>

              <div class="table-responsive text-nowrap">
                <table class="table">
                  <thead>
                    <tr>
                      <th>Username</th>
                      <th>Booking Date</th>
                      <th>Status</th>
                      <th>Total Price</th>
                      <th style="padding-left: 3em;">Actions</th>
                    </tr>
                  </thead>
                  <tbody class="table-border-bottom-0">
                  <c:forEach var="booking" items="${bookingEntityList}">
                    <tr>
                      <td><i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${booking.accountEntity.username}</strong></td>
                      <td><fmt:formatDate value="${booking.booking_date}" pattern="dd-MM-yyyy" /></td>
                      <td><span class="badge bg-label-primary me-1">${booking.booking_status}</span></td>
                      <td>${booking.total_price} VND</td>
                      <td>
                        <div class="dropdown" style="display: flex;">
                          </button>
                            <a style="width: 20%; border-radius: 1.3em;color: rgb(132, 122, 5); font-weight: 500;" class="dropdown-item" href="bookingdetail&bookingid=${booking.id}"><i class="bx bx-edit-alt me-1"></i>
                              View</a>
                              <form action="cancelbooking&id=${booking.id}" method ="POST">
                            <button style="width: 20%; border-radius: 1.3em; padding-right: 5em; color: rgb(221, 75, 75);" class="dropdown-item" type="submit"><i class="bx bx-trash me-1"></i>
                              Cancel</button>
                              </form>
                        </div>
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>

          </div>
          <!-- / Content -->

          <!-- Footer -->
          <footer class="content-footer footer bg-footer-theme">
            <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
              <div class="mb-2 mb-md-0">
                ©
                <script>
                  document.write(new Date().getFullYear());
                </script>
                , made with ❤️ by
                <a href="https://themeselection.com" target="_blank" class="footer-link fw-bolder">ThemeSelection</a>
              </div>
              <div>
                <a href="https://themeselection.com/license/" class="footer-link me-4" target="_blank">License</a>
                <a href="https://themeselection.com/" target="_blank" class="footer-link me-4">More Themes</a>

                <a href="https://themeselection.com/demo/sneat-bootstrap-html-admin-template/documentation/"
                  target="_blank" class="footer-link me-4">Documentation</a>

                <a href="https://github.com/themeselection/sneat-html-admin-template-free/issues" target="_blank"
                  class="footer-link me-4">Support</a>
              </div>
            </div>
          </footer>
          <!-- / Footer -->

          <div class="content-backdrop fade"></div>
        </div>
        <!-- Content wrapper -->
      </div>
      <!-- / Layout page -->
    </div>

    <!-- Overlay -->
    <div class="layout-overlay layout-menu-toggle"></div>
  </div>
  <!-- / Layout wrapper -->

  <!-- <div class="buy-now">
    <a href="https://themeselection.com/products/sneat-bootstrap-html-admin-template/" target="_blank"
      class="btn btn-danger btn-buy-now">Upgrade to Pro</a>
  </div> -->

  <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src='<c:url value="/resources/static/assets/vendor/libs/jquery/jquery.js" />'></script>
    <script src='<c:url value="/resources/static/assets/vendor/libs/popper/popper.js" />'></script>
    <script src='<c:url value="/resources/static/assets/vendor/js/bootstrap.js" />'></script>



    <script src='<c:url value="/resources/static/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js" />'></script>


    <script src='<c:url value="/resources/static/assets/vendor/js/menu.js" />'></script>

    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src='<c:url value="/resources/static/assets/js/main.js" />'></script>

    <!-- Page JS -->
    <script src='<c:url value="/resources/static/assets/js/pages-account-settings-account.js" />'></script>


    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src='<c:url value="https://buttons.github.io/buttons.js" />'></script>

  </body>

  </html>