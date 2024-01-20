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
    <link rel="stylesheet" href="<c:url value="/template/css/create_appointment.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
</head>
<body>
<div id="main">
    <input type="hidden" value="<c:url value="/createAppointment?"/>" id="href">
    <jsp:include page="/common/web/navbar.jsp"></jsp:include>
    <div id="content">
        <div class="content-left">
            <div class="content-list-meeting">
                <p class="text">Lịch hẹn</p>
                <p class="all"><a href="">Tất cả</a></p>
                <jsp:include page="/common/web/Appointment.jsp"></jsp:include>
            </div>
        </div>
        <div class="content-main">
            <h1 class="content-main-title">Chỉnh sửa lịch đặt hẹn của bạn</h1>
            <div class="create-appointment">
                <div class="your-calendar">
                    <p class = "title">Lịch của bạn</p>
                    <div class="under-title">
                        <div class="profile">
                            <img src="data:image/jpeg;base64,${user.background}" class="background" alt="background"/>
                            <img src="data:image/jpeg;base64,${user.avatar}" class="avatar" alt="avatar"/>
                        </div>
                        <div class="content">
                            <input type="hidden" id="totalAppointment" name="totalAppointment" value="${totalAppointment}">
                            <c:forEach var="appointment" items="${appointments}">
                                <form class="item" method="post" action="createAppointment">
                                    <input type="hidden" value="delete" name="action">
                                    <input type="hidden" value="${appointment.id}" name="idAppointment">
                                    <p class="date">${appointment.dateMeeting}</p>
                                    <div class="date-content">
                                        <div class="time" startTime="${appointment.starting_time}" endTime="${appointment.ending_time}" name="time">
                                        </div>
                                        <i class="fa-solid fa-location-dot"></i>
                                        <div class="address">${appointment.address.detail}, ${appointment.address.ward.ward}, ${appointment.address.ward.district.district}</div>
                                        <button type="submit" class="delete">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                    </div>
                                    <input type="hidden" value="${appointment.dateMeeting}" name="date">
                                </form>
                            </c:forEach>
                            <%--<form class="item">
                                <p class="date">07/05/2023</p>
                                <div class="date-content">
                                    <div class="time" starttime="2023-05-15 15:22:19.0" endtime="2023-05-15 19:22:13.0">
                                        15:20 PM - 19:20 PM
                                    </div>
                                    <i class="fa-solid fa-location-dot"></i>
                                    <div class="address">123 Nguyễn Lương Bằng</div>
                                    <button type="submit" class="delete">
                                        <i class="fa-solid fa-trash"></i>
                                    </button>
                                </div>
                            </form>
                            <form class="item">
                                <p class="date">07/05/2023</p>
                                <div class="date-content">
                                    <div class="time" starttime="2023-05-15 15:22:19.0" endtime="2023-05-15 19:22:13.0">
                                        15:20 PM - 19:20 PM
                                    </div>
                                    <i class="fa-solid fa-location-dot"></i>
                                    <div class="address">123 Nguyễn Lương Bằng</div>
                                    <button type="submit" class="delete">
                                        <i class="fa-solid fa-trash"></i>
                                    </button>
                                </div>
                            </form>--%>
                        </div>
                    </div>
                </div>
                <div class="create-app">
                    <p class = "title">Tạo mới các lịch hẹn</p>
                    <form class="create-app-form" method="post" action="createAppointment">
                        <input type="hidden" value="create" name="action">
                        <jsp:include page="/common/web/Calender.jsp"></jsp:include>
                        <label class="error"><p id="errorDate"></p></label>
                        <c:if test="${dateMeeting != null}">
                            <input type="hidden" value="${dateMeeting}" id="dateMeeting" name="dateMeeting">
                        </c:if>
                        <div class="item">
                            <p class = "title">Thời gian: </p>
                            <input type="time" class="time-begin" name="startTime" value="00:00">
                        </div>
                        <div class="item">
                            <p class = "title"> đến </p>
                            <input type="time" class="time-end" name="endTime" value="00:00">
                        </div>
                        <label class="error"><p id="errorTime"></p></label>
                        <p class="title">Số người tối đa: </p>
                        <select class="list num" id="max" name="max">
                            <option selected>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                        </select>
                        <select class="list type" id="addressType" name="addressType">
                            <option disabled selected hidden>Chọn địa điểm</option>
                            <c:if test="${addressTypes != null}">
                                <c:forEach items="${addressTypes}" var="addressType">
                                    <option value="${addressType.id}">${addressType.type}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <div class="item last">
                            <p class = "title">Địa điểm:</p>
                            <select class="list-district list" id="list-district" name="district" required="required">
                                <option selected disabled hidden>Chọn quận/huyện</option>
                                <c:if test="${districts != null}">
                                    <c:forEach var="district" items="${districts}">
                                        <option value="${district.district}">${district.district}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <select class="list-ward list" id="list-ward" name="ward" required="required">
                                <option selected disabled hidden>Chọn phường/xã</option>
                                <c:if test="${districts != null}">
                                    <c:forEach var="district" items="${districts}">
                                        <c:forEach var="ward" items="${district.wards}">
                                            <option district="${district.district}" value="${ward.id}">${ward.ward}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <label class="error"><p id="errorAddress"></p></label>
                            <input class="detail-location" type="text" placeholder="Nhập số nhà và tên đường" required="required" name="address">
                            <label class="error"><p id="errorAppointment"></p></label>
                        </div>
                        <button class="button" type="button">TẠO NGAY</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="content-right">
            <jsp:include page="/common/web/ListFriend.jsp"></jsp:include>
        </div>
    </div>
</div>
<script src="<c:url value="/template/js/Calender.js"/>"></script>
<script src="<c:url value="/template/js/navbar.js"/>"></script>
<script src="<c:url value="/template/js/CreateAppointment.js"/>"></script>
<script src="<c:url value="/template/js/Appointment.js"/>"></script>
</body>
</html>

