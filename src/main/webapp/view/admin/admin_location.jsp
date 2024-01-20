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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/PBL3/template/css/admin-location.css">
    <link rel="stylesheet" href="/PBL3/template/css/navbar.css">
</head>
<body>
<div id="main">
    <jsp:include page="/common/admin/admin-navbar.jsp"></jsp:include>
    <div id="content">
        <jsp:include page="/common/admin/Sidebar.jsp"></jsp:include>
        <div class="content-main">
            <h1 class="title-main">DANH SÁCH CÁC ĐỊA ĐIỂM </h1>

            <div class="content-list-location">
                    <c:forEach items="${districts}" var="district">
                        <div class="item">
                            <p class="district-name">${district.district}</p>
                            <form action="admin-location" method="post">
                                <input type="hidden" name="action" value="deleteDistrict">
                                <input type="hidden" name="idDistrict" value="${district.id}">
                                <button class="fa-solid fa-trash"></button>
                            </form>
                            <div class="list-ward hide">
                                <p class="title">Danh sách phường/xã: </p>
                                <c:forEach items="${district.wards}" var="ward">
                                    <form action="admin-location" method="post">
                                        <input type="hidden" name="action" value="deleteWard">
                                        <input type="hidden" name="idWard" value="${ward.id}">
                                        <p class="ward-item">${ward.ward}<button class="remove">Xóa</button> </p>
                                    </form>
                                </c:forEach>
                                <form class="add-ward" action="admin-location" method="post">
                                    <input type="hidden" name="idDistrict" value="${district.id}">
                                    <input type="hidden" name="action" value="createWard">
                                    <input type="text" placeholder="Nhập tên phường/xã..." value="" name="ward" required>
                                    <button class="add" type="submit"><i class="fa-solid fa-plus"></i></button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
            </div>
            <div class="content-add"><i class="fa-solid fa-plus"></i></div>
        </div>
    </div>
</div>
<div class="add-new-district-main hide-add-div">
    <p class="title">Nhập tên quận/huyện:</p>
    <form action="admin-location" method="post">
        <textarea rows="5" placeholder="Hải Châu..." name="district"></textarea>
        <input type="hidden" name="action" value="createDistrict">
        <button type="submit">Thêm</button>
    </form>
</div>
<script>
    var oppenDiv = document.querySelector('#content .content-add')
    var addDiv = document.querySelector('.add-new-district-main')

    oppenDiv.addEventListener('click', function (e){
        addDiv.classList.toggle('hide-add-div')
    })

</script>
<script src="<c:url value="/template/js/navbar.js"/>"></script>
</body>
</html>