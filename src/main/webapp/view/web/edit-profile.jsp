<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value="/template/image/Study1.png"/>" type="image/icon type">
    <title>Study With Me | Ứng dụng tìm kiếm người bạn học cùng</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/template/css/edit-profile.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
    <script src='<c:url value="/template/js/JQProfile.js"/>'> </script>
</head>
<body>
    <div id="main">
        <jsp:include page="/common/web/navbar.jsp"></jsp:include>
        <div id="content">
            <div class="content-left">
                <div class="content-list-meeting">
                    <p class="text">Lịch hẹn</p>
                    <p class="all"><a href="">Tất cả</a></p>
                    <jsp:include page="/common/web/Appointment.jsp"></jsp:include>
                </div>
            </div>

            <%--Thong tin chinh--%>
            <div class="content-main">
                <h1 class="content-main-title">Xem thông tin cá nhân</h1>
                <div class="content-main-edit-infor">
                    <img src="data:image/jpeg;base64,${profileUser.background}" class="background" alt="background"/>
                    <img src="data:image/jpeg;base64,${profileUser.avatar}" class="ava" alt="avatar" loading="lazy"/>
                    <h1 class="name">${profileUser.fullName}</h1>
                    <div class="infor-user">
                        <p class="title-infor-user">Thông tin cá nhân</p>
                        <div class="content-infor-user">
                            <div class="item">
                                <i class="icon-infor fa-solid fa-heart"></i>
                                <p class="title">Giới tính: </p>
                                <c:if test="${profileUser.gender==0}">
                                    <p class="content">Nam</p>
                                </c:if>
                                <c:if test="${profileUser.gender==1}">
                                    <p class="content">Nữ</p>
                                </c:if>
                                <c:if test="${profileUser.gender==2}">
                                    <p class="content">Khác</p>
                                </c:if>
                            </div>
                            <c:if test="${profileUser.school!=null}">
                                <div class="item">
                                    <i class="icon-infor fa-solid fa-school"></i>
                                    <p class="title">Trường học: </p>
                                    <p class="content">${profileUser.school.nameSchool}</p>
                                </div>
                            </c:if>
                            <div class="item">
                                <i class="icon-infor fa-solid fa-cake-candles"></i>
                                <p class="title">Ngày sinh:</p>
                                <input type="date" date="${profileUser.dateOfBirth}" class="date" disabled="disabled" id="dateOfBirth">
                            </div>
                        </div>
                        <c:if test="${user.id == profileUser.id}">
                            <button class="edit"><i class="fa-solid fa-pen-to-square"></i>Chỉnh sửa</button>
                        </c:if>
                        <c:if test="${user.id != profileUser.id}">
                            <c:if test="${friendship == null}">
                                <form action="profile" method="post">
                                    <input type="hidden" name="profileUserId" value="${profileUser.id}">
                                    <input type="hidden" value="addFriend" name="action">
                                    <button class="friend" type="submit"><i class="fa-solid fa-user-plus"></i></i>Kết bạn</button>
                                </form>
                            </c:if>
                            <c:if test="${friendship != null}">
                                <c:if test="${friendship.status == 0}">
                                    <form class="" method="post" action="profile" id="">
                                        <input type="hidden" name="profileUserId" value="${profileUser.id}">
                                        <input type="hidden" name="idFriendship" value="${friendship.id}">
                                        <input type="hidden" name="action" value="unfriend">
                                        <button class="friend" type="submit"><i class="fa-solid fa-xmark"></i>Hủy kết bạn</button>
                                    </form>
                                </c:if>
                                <c:if test="${friendship.status == 1}">
                                    <c:if test="${friendship.friend.id == user.id}">
                                        <button class="friend reply"><i class="fa-sharp fa-solid fa-user-check"></i>Phản hồi</button>
                                        <form class="form-reply hide" method="post" action="replyFriend" id="formReply">
                                            <input type="hidden" name="action" value="reply">
                                            <input type="hidden" name="idFriendship" value="${friendship.id}">
                                            <input type="hidden" name="idRequester" value="${profileUser.id}">
                                            <input type="hidden" name="reply" value="" id="reply">
                                            <button class="reply-btn" id="agree"><i class="fa-solid fa-check"></i>Chấp nhận</button>
                                            <button class="reply-btn" id="deny"><i class="fa-solid fa-xmark"></i>Từ chối</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${friendship.requester.id == user.id}">
                                        <form class="" method="post" action="profile" id="">
                                            <input type="hidden" name="profileUserId" value="${profileUser.id}">
                                            <input type="hidden" name="idFriendship" value="${friendship.id}">
                                            <input type="hidden" name="action" value="unfriend">
                                            <button class="friend" type="submit"><i class="fa-solid fa-xmark"></i>Hủy lời mời</button>
                                        </form>
                                    </c:if>
                                </c:if>

                            </c:if>
                        </c:if>
                    </div>
                    <%--Kết thúc thông tin chính--%>

                    <div class="list-apm">
                        <p class="title-list-apm">Danh sách cuộc hẹn đã tham gia</p>

                        <c:forEach items="${listAppointment}" var="appointment">
                            <div class="item-apm">
                                <div class="content-apm">
                                    <div class="user list">
                                        <img src="data:image/jpeg;base64,${appointment.host.avatar}" alt="avatar"/>
                                        <p class="name text">${appointment.host.fullName}</p>
                                    </div>
                                    <div class="time list">
                                        <p class="title text">Thời gian:</p>
                                        <p class="content text times" startTime="${appointment.starting_time}" endTime = "${appointment.ending_time}"></p>
                                    </div>
                                    <div class="date list">
                                        <p class="title text">Ngày: </p>
                                        <div class="date">${appointment.dateMeeting}</div>
                                    </div>
                                    <div class="list-rate list text">
                                        <p class="title">Đánh giá<i class="fa-solid fa-chevron-down"></i></p>
                                    </div>
                                </div>
                                <div class="list-rate-apm">
                                    <div class="item">
                                        <img src="data:image/jpeg;base64,${appointment.host.avatar}" alt="avatar">
                                        <p class="name text">${appointment.host.fullName}</p>
                                        <div class="rate-star">
                                            <c:if test="${appointment.host.id != profileUser.id}">
                                                <p class="rate-star-title">Đánh giá:</p>
                                                <form>
                                                <c:forEach items="${appointment.rates}" var="rate">
                                                    <c:if test="${rate.rated.id == profileUser.id and rate.rateBy.id == appointment.host.id}">
                                                        <div class="form-group" value="${rate.point}">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>

                                                </form>
                                            </c:if>
                                            <c:if test="${appointment.host.id == profileUser.id}">
                                                <div class="rate-star">
                                                    <p class="rate-star-title">Bạn</p>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <c:forEach items="${appointment.participants}" var="participant">
                                        <div class="item">
                                            <img src="data:image/jpeg;base64,${participant.avatar}" alt="avatar">
                                            <p class="name text">${participant.fullName}</p>
                                            <c:if test="${participant.id != profileUser.id}">
                                                <div class="rate-star">
                                                    <p class="rate-star-title">Đánh giá:</p>
                                                    <form>
                                                        <c:forEach items="${appointment.rates}" var="rate">
                                                            <c:if test="${rate.rated.id == profileUser.id and rate.rateBy.id == participant.id}">
                                                                <div class="form-group" value="${rate.point}">
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </div>
                                                            </c:if>
                                                        </c:forEach>
 <%--                                                       <c:if test="${fn:length(appointment.rates) == 0}">
                                                            <div class="form-group" value="">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                            </div>
                                                        </c:if>--%>
                                                    </form>
                                                </div>
                                            </c:if>
                                            <c:if test="${participant.id == profileUser.id}">
                                                <div class="rate-star">
                                                    <p class="rate-star-title">Bạn</p>
                                                </div>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                        <input type="hidden" id="totalItem" value="${fn:length(listAppointment)}">
                        <input type="hidden" name="totalAppointment" id="totalAppointment" value="${totalAppointment}">
                        <form action="profile" method="get" id="seeMore">
                            <input type="hidden" name="id" id="id" value="${profileUser.id}">
                            <input type="hidden" name="maxItem" id="maxItem" value="">
                        </form>
                        <c:if test="${totalAppointment > fn:length(listAppointment)}">
                            <input class="see-more" type="button" name="seeMore" value="Xem thêm" id="buttonSeeMore">
                        </c:if>
                        <c:if test="${fn:length(listAppointment) == 0}">
                            <div class="status" >
                                Trống
                            </div>
                        </c:if>
                        <%--<div class="item-apm">
                            <div class="content-apm">
                                <div class="user list">
                                    <img src="data:image/jpeg;base64,${user.avatar}" alt="avatar"/>
                                    <p class="name text">${user.fullName}</p>
                                </div>
                                <div class="time list">
                                    <p class="title text">Thời gian:</p>
                                    <p class="content text">8 AM - 9 AM</p>
                                </div>
                                <div class="date list">
                                    <p class="title text">Ngày:</p>
                                    <input type="date" class="date" disabled="disabled" value="2023-02-02">
                                </div>
                                <div class="list-rate list text">
                                    <p class="title">Đánh giá<i class="fa-solid fa-chevron-down"></i></p>
                                </div>
                            </div>
                            <div class="list-rate-apm">
                                <div class="item a1">
                                    <img src="/PBL3/template/image/avatarDefault.jpg" alt="">
                                    <p class="name text">Ho va ten</p>
                                    <div class="rate-star">
                                        <p class="rate-star-title">Đánh giá:</p>
                                        <form>
                                            <div class="form-group" value="4">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="item a2">
                                    <img src="/PBL3/template/image/avatarDefault.jpg" alt="">
                                    <p class="name text">Ho va ten</p>
                                    <div class="rate-star">
                                        <p class="rate-star-title">Đánh giá:</p>
                                        <form>
                                            <div class="form-group" value="3">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>--%>
                        <%--<div class="item-apm">
                            <div class="content-apm">
                                <div class="user list">
                                    <img src="/PBL3/template/image/avatarDefault.jpg" alt="">
                                    <p class="name text">Ho va ten</p>
                                </div>
                                <div class="time list">
                                    <p class="title text">Thời gian:</p>
                                    <p class="content text">8 AM - 9 AM</p>
                                </div>
                                <div class="date list">
                                    <p class="title text">Ngày:</p>
                                    <input type="date" class="date" disabled="disabled" value="2023-02-02">
                                </div>
                                <div class="list-rate list text">
                                    <p class="title">Đánh giá<i class="fa-solid fa-chevron-down"></i></p>
                                </div>
                                <div class="list-rate-apm" id="b">
                                    <div class="item b1">
                                        <img src="/PBL3/template/image/avatarDefault.jpg" alt="">
                                        <p class="name text">Ho va ten</p>
                                        <div class="rate-star">
                                            <p class="rate-star-title">Đánh giá:</p>
                                            <form>
                                                <div class="form-group" value="5">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>--%>
                    </div>
                </div>
            </div>
            <div class="content-right">
                <jsp:include page="/common/web/ListFriend.jsp"/>
            </div>
        </div>
    </div>


    <%--div chỉnh sửa--%>
    <div class="vice-main">
        <div class="edit-infor">
            <h1 class="edit-infor-title">Chỉnh sửa thông tin cá nhân</h1>
            <p class="title-edit">Ảnh bìa</p>
            <form class="edit-background" action="profile" method="post" enctype="multipart/form-data">
                <img id="blah1" src="data:image/jpeg;base64,${profileUser.background}" alt="your image" />
                <input class="input-img" accept="image/*" type="file" id="imgInp1" name="background"/>
                <button class="submit" disabled="disabled" type="submit">Xác nhận thay đổi</button>
                <script>
                    imgInp1.onchange = evt => {
                        const [file] = imgInp1.files
                        if (file) {
                            blah1.src = URL.createObjectURL(file)
                        }
                    }
                  </script>
                <input type="hidden" value="editBackground" name="action">
                <input type="hidden" value="${profileUser.id}" name="profileUserId">
            </form>
            <p class="title-edit">Ảnh đại diện</p>
            <form class="edit-ava" action="profile" method="post" enctype="multipart/form-data">
                <img src="data:image/jpeg;base64,${profileUser.avatar}" id="blah2">
                <input class="input-img" accept="image/*" type="file" id="imgInp2" name="avatar"/>
                <button class="submit" disabled="disabled">Xác nhận thay đổi</button>
                <script>
                    imgInp2.onchange = evt => {
                        const [file] = imgInp2.files
                        if (file) {
                            blah2.src = URL.createObjectURL(file)
                        }
                    }
                  </script>
                <input type="hidden" value="editAvatar" name="action">
                <input type="hidden" value="${profileUser.id}" name="profileUserId">
            </form>
            <form action="profile" method="post" id="editProfile">
                <p class="title-edit">Thông tin cá nhân</p>
                <div class="list">
                    <i class="fa-solid fa-user"></i>
                    <p class="content">Họ và tên: </p>
                    <input class="first-name" value="${profileUser.firstName}" name="firstName">
                    <input class="last-name" value="${profileUser.lastName}" name="lastName">
                </div>
                <div class="list">
                    <i class="fa-solid fa-heart"></i>
                    <p class="content">Giới tính:</p>
                    <select class="list-sex" name="gender" id="gender" value="${profileUser.gender}">
                        <option value="male">Nam</option>
                        <option value="female">Nữ</option>
                        <option value="other">Khác</option>
                    </select>
                </div>
                <div class="list">
                    <i class="fa-solid fa-school"></i>
                    <p class="content">Trường học:</p>
                    <select class="list-school" id="list-school" name="school">
                        <option selected hidden>${profileUser.school.nameSchool}</option>
                        <c:forEach items="${listSchool}" var="school">
                            <option idSchool="${school.id}">${school.nameSchool}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="list">
                    <i class="fa-solid fa-cake-candles"></i>
                    <p class="content">Ngày sinh:</p>
                    <input type="date" date="${profileUser.dateOfBirth}" class="date" name="dateOfBirth"/>
                </div>
                <input type="hidden" value="editProfile" name="action">
                <input type="hidden" value="${profileUser.id}" name="profileUserId">
                <button class="submit" disabled="disabled">Xác nhận thay đổi</button>
            </form>
            <div class="chance-password">
                <p class="title-edit">Thay đổi mật khẩu tài khoản</p>
                <div class="list">
                    <p class="content">Email đăng nhập:</p>
                    <p class="email">${profileUser.email}</p>
                </div>
                <div class="list">
                    <p class="content">Mật khẩu:</p>
                    <p class="password">************</p>
                </div>
                <button class="submit">Đổi mật khẩu</button>
                <input type="hidden" value="${profileUser.id}" name="profileUserId">
            </div>
            <button class="exit">X</button>
        </div>
    </div>

    <%--div thay doi mat khau--%>
    <div class="div-chance-pwd hide">
        <div class="chance-pwd">
            <p class="title">Thay đổi mật khẩu</p>
            <form class="chance-pwd-form" method="post" action="profile">
                <input class="input" type="password" placeholder="Nhập mật khẩu hiện tại" name="passwordCurrent" id="passwordCurrent">
                <label><p id="errorChange">${errorChange}</p></label>
                <p class="note">*Mật khẩu mới phải trên 8 kí tự.</p>
                <input class="input" type="password" placeholder="Nhập mật khẩu mới" name="newPassword" id="newPassword" onkeyup="checkPassword()" onchange="checkNewPassword(this)">
                <label><p id="errorNewPassword"></p></label>
                <input class="input" type="password" placeholder="Nhập lại mật khẩu mới" name="rePassword" id="rePassword" onkeyup="checkPassword()">
                <label><p id="errorPassword"></p></label>
                <input class="submit" type="submit" disabled value="XÁC NHẬN">
                <input type="hidden" value="changePassword" name="action">
                <input type="hidden" name="profileUserId" value="${profileUser.id}">
            </form>
            <button class="exit">X</button>
        </div>
    </div>
    <script src="<c:url value="/template/js/Profile.js"/>"></script>
    <script src="<c:url value="/template/js/navbar.js"/>"></script>
</body>
</html>