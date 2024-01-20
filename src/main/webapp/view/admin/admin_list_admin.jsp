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
    <link rel="stylesheet" href="<c:url value="/template/css/admin-list-admin.css"/> ">
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
            <h1 class="title-main">DANH SÁCH CÁC ADMIN</h1>
            <div class="content-main-search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Gõ gì đó để tìm kiếm ...">
            </div>
            <div class="content-list-user">
                <c:forEach items="${admins}" var="admin">
                    <div class="item">
                        <div class="ava-name">
                            <img src="data:image/jpeg;base64,${admin.avatar}" alt="avatar">
                            <p class="name">${admin.fullName}</p>
                        </div>
                        <div class="sex">
                            <p class="title">Giới tính:</p>
                            <c:if test="${user.gender == 0}">
                                <p class="content">Nam</p>
                            </c:if>
                            <c:if test="${user.gender == 1}">
                                <p class="content">Nữ</p>
                            </c:if>
                            <c:if test="${user.gender == 2}">
                                <p class="content">Khác</p>
                            </c:if>
                        </div>
                        <div class="birth">
                            <p class="title">Ngày sinh:</p>
                            <input class="date" type="date" value="${admin.dateOfBirth}" disabled="disabled">
                        </div>
                        <div class="buttons">
                            <form action="admin-list-admin" method="post" name="formUnSet">
                                <button class="button lock">
                                    <i class="fa-solid fa-user-xmark"></i>
                                    <p class="button-content">Gỡ quyền Admin</p>
                                </button>
                                <input type="hidden" name="action" value="unSet">
                                <input type="hidden" name="profileUserId" value="${admin.id}">
                                <input type="hidden" value="${pageable.page}" name="page"/>
                                <input type="hidden" value="${pageable.maxPageItem}" name="maxPageItem">
                                <input type="hidden" value="${pageable.sorter.sortName}" name="sortName">
                                <input type="hidden" value="${pageable.sorter.sortBy}" name="sortBy">
                            </form>
                        </div>
                        <input type="hidden" class="school" value="${admin.school.nameSchool}">
                        <input type="hidden" class="background" value="data:image/jpeg;base64,${admin.background}">
                    </div>
                </c:forEach>
            </div>
            <form action="admin-list-admin" method="get" id="formPaging">
                <ul class="pagination" id="pagination"></ul>
                <input type="hidden" value="${pageable.page}" id="page" name="page"/>
                <input type="hidden" value="${pageable.maxPageItem}" id="maxPageItem" name="maxPageItem">
                <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
            </form>
        </div>
    </div>



    <-- Div phu -->
    <div class="detail-div hide">
        <div class="panel"></div>
        <div class="detail-div-main">
            <p class="title-main">Thông tin chi tiết tài khoản</p>
            <img src="" class="background" alt="background"/>
            <img src="" class="ava" alt="avatar" loading="lazy"/>
            <h1 class="name" id="name"></h1>
            <div class="role">
                <p class="title">Phân quyền:</p>
                <p class="content">Admin</p>
            </div>
            <div class="infor-user">
                <form action="edit-profile" method="post" enctype='multipart/form-data' id="editProfile">
                    <p class="title-edit">Thông tin cá nhân</p>
                    <div class="list">
                        <i class="fa-solid fa-heart"></i>
                        <p class="content">Giới tính:</p>
                        <p class="sex"></p>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-school"></i>
                        <p class="content">Trường học:</p>
                        <p class="school"></p>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-cake-candles"></i>
                        <p class="content">Ngày sinh:</p>
                        <input type="date" value="2023-01-01" class="date" name="dateOfBirth"/>
                    </div>
                    <input type="hidden" value="editProfile" name="action">
                    <input type="hidden" value="${profileUser.id}" name="profileUserId">
                </form>
                <button class="exit">X</button>
            </div>
        </div>
    </div>

</div>
<script src="<c:url value="/template/js/AdminListAdmin.js"/>"></script>
<script src="<c:url value="/template/js/navbar.js"/>"></script>
<script type="text/javascript">
    var totalPages = ${totalPages};
    var currentPage = ${pageable.page};
    var limit = 6;
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

