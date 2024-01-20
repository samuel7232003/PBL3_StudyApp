<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="header">
    <div class="header-info-user">
        <img src="data:image/jpeg;base64,${user.avatar}" alt="avatar"/>
        <p class="header-info-user-name"> ${user.fullName} </p>
    </div>
    <div class="header-noti">
        <button class="menu-btn">
            <i class="fa-solid fa-bars"></i>
        </button>
        <div class="header-menu hide" id="header-menu">
            <a class="item" href="<c:url value="/logout"/>">
                Đăng xuất
            </a>
            <a class="item" href="<c:url value="/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc"/>">
                Trở lại trang người dùng
            </a>
        </div>
    </div>
</div>

