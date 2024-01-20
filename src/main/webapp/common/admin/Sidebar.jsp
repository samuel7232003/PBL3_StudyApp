<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03/06/2023
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<div class="content-left">
  <a href="<c:url value="/admin-home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc"/> " class="user-list r1">
    <p class="content">Quản lí danh sách User</p>
    <i class="fa-solid fa-bars"></i>
  </a>
  <a href="<c:url value="/admin-list-admin?page=1&maxPageItem=8&sortName=createdDate&sortBy=desc"/> " class="user-list r2">
    <p class="content">Quản lí danh sách Admin</p>
    <i class="fa-solid fa-bars"></i>
  </a>
  <a href="<c:url value="/admin-location"/> " class="user-list r3">
    <p class="content">Quản lí danh sách Địa điểm</p>
    <i class="fa-solid fa-bars"></i>
  </a>
  <a href="<c:url value="/admin-school"/> " class="user-list r4">
    <p class="content">Quản lí danh sách Trường học</p>
    <i class="fa-solid fa-bars"></i>
  </a>
  <a href="<c:url value="/admin-appointment?page=1&maxPageItem=8&sortName=createdDate&sortBy=desc"/> " class="user-list r5">
    <p class="content">Quản lí danh sách Cuộc hẹn</p>
    <i class="fa-solid fa-bars"></i>
  </a>
</div>