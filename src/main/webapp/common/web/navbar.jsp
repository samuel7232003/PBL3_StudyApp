<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="header">
    <div class="header-info-user">
        <img src="data:image/jpeg;base64,${user.avatar}" alt="avatar"/>
        <a href="<c:url value="/profile?id=${user.id}"/>"><p class="header-info-user-name"> ${user.fullName} </p></a>
    </div>

    <div class="header-main">
        <li><a href="">
            <button>
                <i class="fa-solid fa-magnifying-glass"></i>
            </button>
        </a>
        </li>
        <li><a href="<c:url value="/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc"/>">
            <button class="header-main-mainbutton">
                <i class="fa-solid fa-globe"></i>
            </button>
        </a>
        </li>
        <li><a href="<c:url value="/Q&A?listFriend=1"/>">
            <button class = "header-main-rightbutton">
                <i class="fa-solid fa-book"></i>
            </button>
        </a></li>
    </div>
    <div class="header-noti">
        <c:if test="${requestFriend != null}">
            <div class="num-noti">${fn:length(requestFriend)}</div>
        </c:if>
        <button class="noti-btn">
            <i class="fa-sharp fa-regular fa-bell"></i>
        </button>
        <div class="noti-content hide">
            <c:if test="${requestFriend != null}">
                <c:forEach items="${requestFriend}" var="request">
                    <div class="list">
                        <div class="content">
                            <img src="data:image/jpeg;base64,${request.requester.avatar}">
                            <p class="noti"><a href="<c:url value="/profile?id=${request.requester.id}"/>">${request.requester.fullName}</a> vừa gửi lời mời kết bạn cho bạn.</p>
                        </div>
                        <form class="buttons" method="post" action="replyFriend" name="formReplyNavbar">
                            <input type="hidden" name="action" value="reply">
                            <input type="hidden" name="idFriendship" value="${request.id}">
                            <input type="hidden" name="idRequester" value="${request.requester.id}">
                            <input type="hidden" name="reply" value="" id="replyNavbar">
                            <button class="yes" id="agreeNavbar">Đồng ý</button>
                            <button class="no" id="denyNavbar">Từ chối</button>
                        </form>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${requestFriend == null}">
                <p>Không có thông báo!</p>
            </c:if>
        </div>
        <button class="menu-btn">
            <i class="fa-solid fa-bars"></i>
        </button>
        <div class="header-menu hide" id="header-menu">
            <a class="item" href="<c:url value="/logout"/>">
                Đăng xuất
            </a>
            <a class="item" href="<c:url value="/profile?id=${user.id}"/>">
                Thay đổi thông tin tài khoản
            </a>
            <a class="item" href="<c:url value="/rate?page=1&maxPageItem=7&sortName=createdDate&sortBy=desc"/>">
                Đánh giá các cuộc hẹn
            </a>
            <a class="item" href="<c:url value="/list-friends?page=1&maxPageItem=8&sortName=createdDate&sortBy=desc"/> ">
                Xem danh sách bạn bè
            </a>
            <c:if test="${user.role.code == 'ADMIN'}">
                <a class="item" href="<c:url value="/admin-home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc"/>">
                    Đến trang quản lý
                </a>
            </c:if>
        </div>
    </div>
</div>
