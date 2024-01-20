<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/04/2023
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>

<div class="list-friends">
    <p class="list-friends-title">Danh sách bạn bè</p>
    <c:forEach items="${listFriend}" var="friend">
        <c:if test="${user.id != friend.requester.id}">
            <div class="list-friends-item">
                <img src="data:image/jpeg;base64,${friend.requester.avatar}" alt="avatar" />
                <p class="name"><a href="<c:url value="/profile?id=${friend.requester.id}"/>"> ${friend.requester.fullName} </a></p>
                <i class="fa-solid fa-mug-saucer"></i>
            </div>
        </c:if>
        <c:if test="${user.id != friend.friend.id}">
            <div class="list-friends-item">
                <img src="data:image/jpeg;base64,${friend.friend.avatar}" alt="avatar" />
                <p class="name"><a href="<c:url value="/profile?id=${friend.friend.id}"/>"> ${friend.friend.fullName} </a></p>
                <i class="fa-solid fa-mug-saucer"></i>
            </div>
        </c:if>
    </c:forEach>
</div>