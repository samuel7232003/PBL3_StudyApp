<%--
  Created by IntelliJ IDEA.
  User: Le Viet Thanh
  Date: 5/20/2023
  Time: 3:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List Friends</title>
        <link rel="icon" href="<c:url value="/template/image/Study1.png"/>" type="image/icon type">
        <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="<c:url value="/template/css/list-friends.css"/>">
        <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
        <script src="<c:url value="/template/paging/jquery.twbsPagination.js"/> " type="text/javascript"></script>
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
                <div class="content-main">
                    <p class="content-main-title">Danh sách bạn bè của bạn (${totalFriend})</p>
                    <div class="list-friends">
                        <c:forEach items="${listFriend}" var="friend">
                            <c:if test="${user.id != friend.requester.id}">
                                <a class="item" href="<c:url value="/profile?id=${friend.requester.id}"/>" >
                                    <img class="avatar" src="data:image/jpeg;base64,${friend.requester.avatar}" alt="avatar">
                                    <p class="user-name">${friend.requester.fullName}</p>
                                </a>
                            </c:if>
                            <c:if test="${user.id != friend.friend.id}">
                                <a class="item" href="<c:url value="/profile?id=${friend.friend.id}"/>" >
                                    <img class="avatar" src="data:image/jpeg;base64,${friend.friend.avatar}" alt="avatar">
                                    <p class="user-name">${friend.friend.fullName}</p>
                                </a>
                            </c:if>
                        </c:forEach>
                    </div>
                    <form action="list-friends" method="get" id="formPaging">
                        <ul class="pagination" id="pagination"></ul>
                        <input type="hidden" value="${pageable.page}" id="page" name="page"/>
                        <input type="hidden" value="${pageable.maxPageItem}" id="maxPageItem" name="maxPageItem">
                        <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                        <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
                    </form>
                </div>
                <div class="content-right">
                    <jsp:include page="/common/web/ListFriend.jsp"></jsp:include>
                </div>
            </div>
        </div>
        <script src="<c:url value="/template/js/navbar.js"/>"></script>
        <script src="<c:url value="/template/js/Appointment.js"/>"></script>
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
