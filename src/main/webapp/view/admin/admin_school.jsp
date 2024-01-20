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
    <link rel="stylesheet" href="/PBL3/template/css/admin-school.css">
    <link rel="stylesheet" href="/PBL3/template/css/navbar.css">
</head>
<body>
<div id="main">
    <jsp:include page="/common/admin/admin-navbar.jsp"></jsp:include>
    <div id="content">
        <jsp:include page="/common/admin/Sidebar.jsp"></jsp:include>
        <div class="content-main">
            <h1 class="title-main">DANH SÁCH CÁC TRƯỜNG HỌC</h1>
            <div class="content-main-search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Gõ gì đó để tìm kiếm ...">
            </div>
            <div class="content-list-school">
                <c:forEach items="${schools}" var="school">
                    <div class="item">
                        <i class="fa-solid fa-school"></i>
                        <p class="name">${school.nameSchool}</p>
                        <form action="admin-school" method="post">
                            <button class="delete">
                                <i class="fa-solid fa-trash"></i>
                                <p class="button-content">Xóa</p>
                            </button>
                            <input type="hidden" name="idSchool" value="${school.id}">
                            <input type="hidden" name="action" value="deleteSchool">
                        </form>
                    </div>
                </c:forEach>
            </div>
            <div class="content-add"><i class="fa-solid fa-plus"></i></div>
        </div>
    </div>
</div>

<div class="add-new-school-main hide-add-div">
    <p class="title">Nhập tên trường:</p>
    <form action="admin-school" method="post">
        <textarea rows="5" placeholder="Trường Đại học Bách khoa - Đại học Đà Nẵng" name="nameSchool"></textarea>
        <label><p id="error">${error}</p></label>
        <button type="submit">Thêm trường</button>
        <input type="hidden" name="action" value="createSchool">
    </form>
</div>
<script>
    var oppenDiv = document.querySelector('#content .content-add')
    var addDiv = document.querySelector('.add-new-school-main')

    oppenDiv.addEventListener('click', function (e){
        addDiv.classList.toggle('hide-add-div')
    })
    var error = document.getElementById('error');
    if (error.innerText !== "") {
        addDiv.classList.remove('hide-add-div');
    }
</script>
<script src="<c:url value="/template/js/navbar.js"/>"></script>
</body>
</html>
