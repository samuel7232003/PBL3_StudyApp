<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Study With Me | Ứng dụng tìm kiếm người bạn học cùng</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/template/css/Q&A.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
</head>
<body>
<input type="hidden" value="<c:url value="/Q&A"/>" id="href">
    <div id="main">
        <jsp:include page="/common/web/navbar.jsp"></jsp:include>
        <div id="content">
            <div class="content-left">
                <jsp:include page="/common/web/Calender.jsp"></jsp:include>
                <div class="cal-panel"></div>
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
                    <div class="content-main-item">
                        <div class="profile">                            
                            <img class="avatar" src="../image/ava1.png" alt="">
                            <p class="name">Họ và Tên</p>
                        </div>
                        <div class="content">                                  
                                    <p class="content1">Mọi người ơi!
                                    <br> Cho em xin công thức tình điện trở toàn phần của mạch hình sao và chứng mình ạ. Em cảm ơn mn ạ.</p>                                                                                                                                      
                        </div>
                        <div class="key-word">
                            Vật lý
                        </div>
                        <p class="see-more">Xem thêm câu trả lời...</p>
                        <div class="comment hide">
                            <div class="list-comment">
                                <div class="item">
                                    <div class="profile-cmt">
                                        <img src="../../template/image/avatarDefault.png">
                                        <p class="name">Le Viet Thanh</p>
                                    </div>
                                    <p class="content-cmt">Công thức đây em nhé!</p>
                                </div>
                                <div class="item">
                                    <div class="profile-cmt">
                                        <img src="../../template/image/avatarDefault.png">
                                        <p class="name">Le Viet Thanh</p>
                                    </div>
                                    <p class="content-cmt">Công thức đây em nhé!</p>
                                </div>
                            </div>
                            <form class="write-cmt">
                                <input type="text" placeholder="Nhập câu trả lời...">
                                <button type="submit"><i class="fa-solid fa-paper-plane"></i></button>
                            </form>
                            <p class="hidden">Ẩn câu trả lời</p>
                        </div>
                    </div>
                </div>               
            </div>
            <div class="content-right">
                <jsp:include page="/common/web/ListFriend.jsp"/>
                <form class="add-question">
                    <p class="title">Đặt câu hỏi của bạn</p>
                    <div class="add-question-main">
                        <textarea rows="10" placeholder="Nhập nội dung câu hỏi tại đây..."></textarea>
                        <div class="box">
                            <p class="title">Thêm từ khóa:</p>
                            <input class="add-key-word">
                        </div>
                        <button class="add" type="submit"><i class="fa-solid fa-plus"></i></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="<c:url value="/template/js/Calender.js"/>"></script>
    <script src="<c:url value="/template/js/navbar.js"/>"></script>
    <script src="<c:url value="/template/js/Appointment.js"/>"></script>
    <script src="<c:url value="/template/js/Q&A.js"/>"></script>
</body>
</html>