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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value="/template/paging/jquery.twbsPagination.js"/> " type="text/javascript"></script>
    <link rel="stylesheet" href="<c:url value="/template/css/rate.css"/> ">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
    <script src="<c:url value="/template/js/JQRate.js"/> " ></script>
</head>
<body>
<div id="main">
    <jsp:include page="/common/web/navbar.jsp"></jsp:include>
    <div id="content">
        <div class="content-left">
            <div class="content-list-meeting">
                <p class="text">Lịch hẹn</p>
                <div class="content scroll-style">
                    <jsp:include page="/common/web/Appointment.jsp"></jsp:include>
                </div>
            </div>
        </div>
        <div class="content-main">
            <h1 class="content-main-title">Danh sách các cuộc hẹn đã tham gia</h1>
            <div class = "crete-rate">
                <div class="scrollable-container">
                    <c:if test="${appointments != null}">
                        <c:forEach items="${appointments}" var="appointment">
                            <div class="list-rate">
                                <div class="rate">
                                    <p class="ID"><img class="avata-host" src="data:image/jpeg;base64,${appointment.host.avatar}" alt=""></i>${appointment.host.fullName}</p>
                                    <p class="time-date" endTime="${appointment.ending_time}" startTime="${appointment.starting_time}" dateMeeting="${appointment.dateMeeting}"><i class="fa-solid fa-clock"></i> 8 A.M - 9 A.M 08/03/2023</p>
                                    <p class="address"><i class="fa-solid fa-location-dot"></i>${appointment.address.detail}</p>
                                    <button class="show-button" title="Xem thành viên"></i>Đánh giá<i class="fa-solid fa-caret-down"></i></button>
                                </div>
                                <form class="list" name="formsRate" action="rate" method="post">
                                    <p class="content-list">Danh sách thành viên:</p>
                                    <div class="member">
                                        <c:if test="${user.id != appointment.host.id}">
                                            <div class="item">
                                                <img class="avata-member" src="data:image/jpeg;base64,${appointment.host.avatar}" alt="avatar">
                                                <p class="Name-member">${appointment.host.fullName}</p>
                                                <input type="hidden" name="idParticipant" value="${appointment.host.id}">
                                                <div class="rate-start">
                                                    <i class="fa-solid fa-star s1"></i>
                                                    <i class="fa-solid fa-star s2"></i>
                                                    <i class="fa-solid fa-star s3"></i>
                                                    <i class="fa-solid fa-star s4"></i>
                                                    <i class="fa-solid fa-star s5"></i>
                                                </div>
                                                <input type="hidden" name="point" value="">
                                            </div>
                                        </c:if>
                                        <c:forEach items="${appointment.participants}" var="participant">
                                            <c:if test="${user.id != participant.id}">
                                                <div class="item">
                                                    <img class="avata-member" src="data:image/jpeg;base64,${participant.avatar}" alt="avatar">
                                                    <p class="Name-member">${participant.fullName}</p>
                                                    <input type="hidden" name="idParticipant" value="${participant.id}">
                                                    <div class="rate-start">
                                                        <i class="fa-solid fa-star s1"></i>
                                                        <i class="fa-solid fa-star s2"></i>
                                                        <i class="fa-solid fa-star s3"></i>
                                                        <i class="fa-solid fa-star s4"></i>
                                                        <i class="fa-solid fa-star s5"></i>
                                                    </div>
                                                    <input type="hidden" name="point" value="">
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="abc">
                                        <button class="accept button" type="button">Xác nhận</button>
                                        <button class="denied button" type="button">Hủy</button>
                                    </div>
                                    <input type="hidden" name="rate" value="">
                                    <input type="hidden" name="idAppointment" value="${appointment.id}">
                                </form>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <form action="rate" method="get" id="formPaging">
                    <ul class="pagination" id="pagination"></ul>
                    <input type="hidden" value="" id="page" name="page"/>
                    <input type="hidden" value="" id="maxPageItem" name="maxPageItem">
                    <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                    <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
                </form>
            </div>
        </div>
        <div class="content-right">
            <jsp:include page="/common/web/ListFriend.jsp"></jsp:include>
        </div>
    </div>
</div>
<script src="<c:url value="/template/js/navbar.js"/>"></script>
<script src="<c:url value="/template/js/Appointment.js"/>"></script>
<script src="<c:url value="/template/js/Rate.js"/>" ></script>
<script type="text/javascript">
    var totalPages = ${totalPages};
    var currentPage = ${pageable.page};
    var limit = 7;
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

