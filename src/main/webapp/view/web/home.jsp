<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Study With Me | Ứng dụng tìm kiếm người bạn học cùng</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <%--    <script src="https://code.jquery.com/jquery-3.6.4.js"
                integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>--%>
    <link rel="icon" href="<c:url value="/template/image/Study1.png"/>" type="image/icon type">
    <link rel="stylesheet" href="<c:url value="/template/css/home.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value="/template/paging/jquery.twbsPagination.js"/> " type="text/javascript"></script>
</head>
<body>
<input type="hidden" value="<c:url value="/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc"/>" id="href">
<jsp:include page="/common/web/navbar.jsp"></jsp:include>
<div id="main">
    <div id="content">
        <div class="content-left">
            <jsp:include page="/common/web/Calender.jsp"></jsp:include>
            <div class="content-list-meeting">
                <p class="text">Lịch hẹn</p>
                <div class="content scroll-style">
                    <jsp:include page="/common/web/Appointment.jsp"></jsp:include>
                </div>
            </div>
        </div>
        <div class="content-main">

            <div class="content-main-search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Gõ gì đó để tìm kiếm ...">
            </div>
            <div class="content-main-list">
                <c:forEach items="${appointments}" var="appointment">
                    <div class="content-main-item">
                        <div class="profile">
                            <img src="data:image/jpeg;base64,${appointment.host.background}" class="background" alt="background"/>
                            <img src="data:image/jpeg;base64,${appointment.host.avatar}" class="avatar" alt="avatar"/>
                            <a href="<c:url value="/profile?id=${appointment.host.id}"/> " class="name">${appointment.host.fullName}</a>
                        </div>
                        <div class="content">
                            <div class="item">
                                <p class="date">${appointment.dateMeeting}</p>
                                <p class="time" startTime="${appointment.starting_time}" endTime = "${appointment.ending_time}"></p>
                                <p class="address"><i class="fa-solid fa-location-dot"></i>${appointment.address.detail}</p>
                                <input type="hidden" value="${appointment.address.addressType.type}">
                                <input type="hidden" value="${appointment.maximum}">
                                <input type="hidden" value="${fn:length(appointment.participants)}" name="totalParticipants">
                                <div class="participants">
                                    <c:forEach var="participant" items="${appointment.participants}">
                                        <div class="participant">
                                            <input type="hidden" value="${participant.avatar}">
                                            <input type="hidden" value="${participant.fullName}">
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <input type="hidden" name="idAppointment" value="${appointment.id}">
                        </div>
                        <button class="see-more">Xem chi tiết</button>
                    </div>
                </c:forEach>
                    <form action="<c:url value="/home"/> " id="formPaging" method="get">
                        <ul class="pagination" id="pagination"></ul>
                        <input type="hidden" value="" id="page" name="page"/>
                        <input type="hidden" value="" id="maxPageItem" name="maxPageItem">
                        <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                        <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
                        <c:if test="${pageable.sorter.dateMeeting != null}">
                            <input type="hidden" value="${pageable.sorter.dateMeeting}" id="dateMeeting" name="dateMeeting">
                        </c:if>
                    </form>
                </div>
            </div>
            <div class="content-right">
                <jsp:include page="/common/web/ListFriend.jsp"/>
                <div class="your-profile">
                    <p class="title">Lịch đặt hẹn của bạn</p>
                    <div class="your-profile-main">
                        <div class="profile">
                            <img src="data:image/jpeg;base64,${user.background}" class="background" alt="background"/>
                            <img src="data:image/jpeg;base64,${user.avatar}" class="avatar" alt="avatar"/>
                            <p class="name">${user.fullName}</p>
                        </div>
                        <div class="content">
                            <c:if test="${appointmentOf != null}">
                                <c:forEach items="${appointmentOf}" var="appointment">
                                    <div class="item">
                                        <div class="date-time">
                                            <p class="date">${appointment.dateMeeting}</p>
                                            <input type="hidden" value="${appointment.dateMeeting}" name="date">
                                            <p class="time" startTime="${appointment.starting_time}" endTime="${appointment.ending_time}"></p>
                                        </div>
                                        <p class="address"><i class="fa-solid fa-location-dot"></i>${appointment.address.detail}</p>
                                    </div>
                                    <input type="hidden" name="idAppointmentOf" value="${appointment.id}">
                                    <span class="line"></span>
                                </c:forEach>
                            </c:if>
                        </div>
                        <a href="<c:url value="/createAppointment"/> ">
                            <button class="add">
                                <i class="fa-solid fa-gear"></i>
                            </button>
                        </a>
                    </div>
                </div>
            </div>


        <div class="booking-apm hide" id="myDIV">
            <div class="panel"> </div>
            <div class="content">
                <img src="" alt="" class="background">
                <img src="" alt="" class="ava">
                <h1 class="name">Tên Trống</h1>
                <form class="infor-apm" action="home" method="post">
                    <div class="flex">
                        <div class="infor">
                            <p class="title">Thông tin cuộc hẹn:</p>
                            <div class="list">
                                <i class="fa-solid fa-clock"></i>
                                <p class="date">01-01-2003</p>
                                <p class="time">00:00 - 00:00</p>
                            </div>
                            <div class="list inline">
                                <i class="fa-solid fa-store"></i>
                                <p class="type-location">Trống</p>
                            </div>
                            <div class="list">
                                <i class="fa-solid fa-location-dot"></i>
                                <p class="address">Mặc định</p>
                            </div>
                        </div>
                        <div class="list-members">
                            <p class="title inline">Người đã tham gia: </p>
                            <p class="number"></p>
                            <div class="list scroll-style">
                            </div>
                        </div>
                    </div>
                    <input class="submit" type="submit" value="KẾT NỐI NGAY">
                    <input type="hidden" value="" name="idAppointment">
                    <input type="hidden" name="action" value="join">
                    <input type="hidden" value="${pageable.page}" id="page" name="page"/>
                    <input type="hidden" value="${pageable.maxPageItem}" id="maxPageItem" name="maxPageItem">
                    <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                    <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
                    <c:if test="${pageable.sorter.dateMeeting != null}">
                        <input type="hidden" value="${pageable.sorter.dateMeeting}" id="dateMeeting" name="dateMeeting">
                    </c:if>
                </form>
                <button class="exit">X</button>
            </div>
        </div>
    </div>
    <script src="<c:url value="/template/js/Home.js"/>"></script>
    <script src="<c:url value="/template/js/Calender.js"/>"></script>
    <script src="<c:url value="/template/js/navbar.js"/>"></script>
    <script src="<c:url value="/template/js/Appointment.js"/>"></script>
<%--    <script type="text/javascript"></script>--%>
    <script type="text/javascript">
        var totalPages = ${totalPages};
        var currentPage = ${pageable.page};
        var limit = 6;
        var dateMeeting = "";
        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 10,
                startPage: currentPage,
                onPageClick: function (event, page) {
                    if(currentPage !== page){
                        $('#maxPageItem').val(limit);
                        $('#page').val(page);
                        $('#formPaging').submit();
                    }
                }
            }).on('page', function (event, page) {
                console.info(page + ' (from event listening)');
            });
        });
    </script>
</body>
</html>