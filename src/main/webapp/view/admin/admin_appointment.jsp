<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Study With Me | Ứng dụng tìm kiếm người bạn học cùng</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/template/css/admin-appointment.css"/> ">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/> ">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value="/template/paging/jquery.twbsPagination.js"/> " type="text/javascript"></script>
</head>
<body>
<div id="main">
    <jsp:include page="/common/admin/admin-navbar.jsp"></jsp:include>
    <div id="content">
        <jsp:include page="/common/admin/Sidebar.jsp"></jsp:include>
        <div class="content-main">
            <h1 class="title-main">DANH SÁCH CÁC CUỘC HẸN</h1>
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
                        <p class="name">${appointment.host.fullName}</p>
                    </div>
                    <div class="content">
                        <div class="item">
                            <p class="date">${appointment.dateMeeting}</p>
                            <p class="time" startTime="${appointment.starting_time}" endTime = "${appointment.ending_time}">00:00 - 00:00</p>
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
            </div>
            <form action="admin-appointment" method="get" id="formPaging">
                <ul class="pagination" id="pagination"></ul>
                <input type="hidden" value="${pageable.page}" id="page" name="page"/>
                <input type="hidden" value="${pageable.maxPageItem}" id="maxPageItem" name="maxPageItem">
                <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
            </form>
        </div>

    </div>
</div>
<%-- div phu --%>
<div class="booking-apm hide" id="myDIV">
    <div class="panel"> </div>
    <div class="content">
        <img src="" alt="" class="background">
        <img src="" alt="" class="ava">
        <h1 class="name">Tên Trống</h1>
        <form class="infor-apm" action="admin-appointment" method="post">
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
            <input class="submit" type="submit" value="Xóa cuộc hẹn này">
            <input type="hidden" value="" name="idAppointment">
            <input type="hidden" name="action" value="deleteAppointment">
            <input type="hidden" value="${pageable.page}" name="page"/>
            <input type="hidden" value="${pageable.maxPageItem}" name="maxPageItem">
            <input type="hidden" value="${pageable.sorter.sortName}" name="sortName">
            <input type="hidden" value="${pageable.sorter.sortBy}" name="sortBy">
        </form>
        <button class="exit">X</button>
    </div>
</div>
<script src="<c:url value="/template/js/AdminAppointment.js"/>"></script>
<script src="<c:url value="/template/js/navbar.js"/>"></script>
<script type="text/javascript">
    var totalPages = ${totalPages};
    var currentPage = ${pageable.page};
    var limit = 8;
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
