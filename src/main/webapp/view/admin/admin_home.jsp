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
    <link rel="stylesheet" href="<c:url value="/template/css/admin-home.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/css/navbar.css"/>">
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
                <h1 class="title-main">DANH SÁCH CÁC USER </h1>
                <div class="content-main-search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="Gõ gì đó để tìm kiếm ...">
                </div>
                <div class="content-list-user">
                    <c:forEach items="${users}" var="user">
                        <div class="item <c:if test="${user.status == 1}">item-lock</c:if>">
                            <div class="ava-name">
                                <img src="data:image/jpeg;base64,${user.avatar}" alt="avatar">
                                <p class="name">${user.fullName}</p>
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
                                <input class="date" type="date" value="${user.dateOfBirth}" disabled="disabled">
                            </div>
                            <div class="buttons">
                                <c:if test="${user.status == 0}">
                                    <button class="button lock">
                                        <i class="fa-solid fa-lock"></i>
                                        <p class="button-content">Khóa</p>
                                    </button>
                                </c:if>
                                <c:if test="${user.status == 1}">
                                    <button class="button unlock">
                                        <i class="fa-solid fa-lock-open"></i>
                                        <p class="button-content">Mở khóa</p>
                                    </button>
                                </c:if>
                                <button class="button remove">
                                    <i class="fa-solid fa-trash"></i>
                                    <p class="button-content">Xóa</p>
                                </button>
                            </div>
                            <input type="hidden" class="school" value="${user.school.nameSchool}">
                            <input type="hidden" class="background" value="data:image/jpeg;base64,${user.background}">
                            <input type="hidden" class="user-name" value="${user.email}">
                            <input type="hidden" value="${user.firstName}">
                            <input type="hidden" value="${user.lastName}">
                            <input type="hidden" value="${user.id}">
                            <c:if test="${idUser == user.id}">
                                <input type="hidden" name="error">
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <form action="admin-home" method="get" id="formPaging">
                    <ul class="pagination" id="pagination"></ul>
                    <input type="hidden" value="${pageable.page}" id="page" name="page"/>
                    <input type="hidden" value="${pageable.maxPageItem}" id="maxPageItem" name="maxPageItem">
                    <input type="hidden" value="${pageable.sorter.sortName}" id="sortName" name="sortName">
                    <input type="hidden" value="${pageable.sorter.sortBy}" id="sortBy" name="sortBy">
                </form>
            </div>
            <div class="content-add"><i class="fa-solid fa-plus"></i></div>
        </div>
    </div>


    <%--    div phụ    --%>
    <div class="detail-div hide">
        <div class="panel"></div>
        <div class="detail-div-main">
            <p class="title-main">Thông tin chi tiết tài khoản</p>
            <img src="" class="background" alt="background"/>
            <img src="" class="ava" alt="avatar" loading="lazy"/>
            <h1 class="name" id="name"></h1>
            <form action="admin-home" method="post" id="editProfile">
                <div class="role">
                    <p class="title">Phân quyền:</p>
                    <select class="list-role" name="role" id="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="infor-user">
                    <p class="title-edit">Thông tin cá nhân</p>
                    <input class="first-name" type="text" name="firstName" value="">
                    <input class="last-name" type="text" name="lastName" value="">
                    <div class="list">
                        <i class="fa-solid fa-heart"></i>
                        <p class="content">Giới tính:</p>
                        <select class="list-sex" name="gender">
                            <option value="male">Nam</option>
                            <option value="female">Nữ</option>
                            <option value="other">Khác</option>
                        </select>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-school"></i>
                        <p class="content">Trường học:</p>
                        <select class="list-school" id="list-school" name="idSchool">
                            <c:forEach items="${listSchool}" var="school">
                                <option value="${school.id}">${school.nameSchool}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-cake-candles"></i>
                        <p class="content">Ngày sinh:</p>
                        <input type="date" date="${profileUser.dateOfBirth}" class="date" name="dateOfBirth"/>
                    </div>
                    <div class="chance-password">
                        <p class="title-edit">Thay đổi mật khẩu tài khoản</p>
                        <div class="list">
                            <p class="content">Email đăng nhập:</p>
                            <input class="email" placeholder="" type="email" name="email">
                            <label><p id="error">${errorEdit}</p></label>
                        </div>
                        <div class="list">
                            <p class="content">Mật khẩu:</p>
                            <input type="password" class="password" placeholder="" name="password">
                        </div>
                        <button class="submit">Xác nhận thay đổi</button>
                    </div>
                    <input type="hidden" value="" name="profileUserId">
                    <input type="hidden" value="editProfile" name="action">
                    <button class="exit" type="button">X</button>
                    <input type="hidden" value="${pageable.page}" name="page"/>
                    <input type="hidden" value="${pageable.maxPageItem}" name="maxPageItem">
                    <input type="hidden" value="${pageable.sorter.sortName}" name="sortName">
                    <input type="hidden" value="${pageable.sorter.sortBy}" name="sortBy">
                </div>
            </form>
        </div>
    </div>

    <div class="confirm-div hide delete">
        <div class="panel"></div>
        <form action="admin-home" method="post">
            <div class="confirm-delete">
                <p class="title">Bạn muốn xóa vĩnh viễn tài khoản này?</p>
                <div class="list">
                    <button class="remove" type="submit">Xác nhận</button>
                    <button class="exit" type="button">Thoát</button>
                </div>
            </div>
            <input type="hidden" name="profileUserId" value="">
            <input type="hidden" name="action" value="deleteUser">
            <input type="hidden" value="${pageable.page}" name="page"/>
            <input type="hidden" value="${pageable.maxPageItem}" name="maxPageItem">
            <input type="hidden" value="${pageable.sorter.sortName}" name="sortName">
            <input type="hidden" value="${pageable.sorter.sortBy}" name="sortBy">
        </form>
    </div>

    <div class="confirm-div hide lock">
        <div class="panel"></div>
        <form action="admin-home" method="post">
            <div class="confirm-lock">
                <p class="title">Bạn muốn khóa tài khoản này?</p>
                <div class="list">
                    <button class="lock" type="submit">Xác nhận</button>
                    <button class="exit" type="button">Thoát</button>
                </div>
            </div>
            <input type="hidden" name="profileUserId" value="">
            <input type="hidden" name="action" value="lockUser">
            <input type="hidden" value="${pageable.page}" name="page"/>
            <input type="hidden" value="${pageable.maxPageItem}" name="maxPageItem">
            <input type="hidden" value="${pageable.sorter.sortName}" name="sortName">
            <input type="hidden" value="${pageable.sorter.sortBy}" name="sortBy">
        </form>
    </div>

    <div class="add-new-user hide">
        <div class="panel"></div>
        <div class="add-new-user-main">
            <div class="title-main">Thêm một người dùng mới</div>
            <form action="admin-home" method="post">
                <div class="content-main">
                    <div class="account">
                        <p class="title-account">Thông tin tài khoản</p>
                        <input class="email" placeholder="Nhập tên tài khoản" name="email" required value="">
                        <label><p id="errorCreate">${errorCreate}</p></label>
                        <input class="password" placeholder="Nhập mật khẩu" name="password" required value="" type="password" id="password" onkeyup="checkPassword()">
                        <label class="pwd-not-same"><p id = "msg"></p> </label>
                        <input class="re-password" placeholder="Nhập lại mật khẩu" name="rePassword" required value="" type="password" id="rePassword" onkeyup="checkPassword()">
                    </div>
                    <div class="infor">
                        <p class="title-infor">Thông tin cá nhân</p>
                        <div class="list">
                            <input class="first-name" type="text" name="firstName" value="" placeholder="Họ và tên đệm">
                            <input class="last-name" type="text" name="lastName" value="" placeholder="Tên">
                        </div>
                        <div class="list">
                            <i class="fa-solid fa-heart"></i>
                            <p class="content">Giới tính:</p>
                            <select class="list-sex" name="gender" required>
                                <option hidden="hidden" selected>Chọn giới tính</option>
                                <option value="male">Nam</option>
                                <option value="female">Nữ</option>
                                <option value="other">Khác</option>
                            </select>
                        </div>
                        <div class="list">
                            <i class="fa-solid fa-school"></i>
                            <p class="content">Trường học:</p>
                        </div>
                        <select class="list-school" name="idSchool" required>
                            <option selected hidden="hidden">Chọn trường học</option>
                            <c:forEach items="${listSchool}" var="school">
                                <option value="${school.id}">${school.nameSchool}</option>
                            </c:forEach>
                        </select>
                        <div class="list">
                            <i class="fa-solid fa-cake-candles"></i>
                            <p class="content">Ngày sinh:</p>
                            <input type="date" value="2003-01-01" class="date" name="dateOfBirth" required/>
                        </div>
                    </div>
                </div>
                <button class="add" id="create">TẠO NGAY</button>
                <button class="exit">X</button>
                <input type="hidden" name="action" value="createUser">
                <input type="hidden" name="profileUserId" value="">
                <input type="hidden" value="${pageable.page}" name="page"/>
                <input type="hidden" value="${pageable.maxPageItem}" name="maxPageItem">
                <input type="hidden" value="${pageable.sorter.sortName}" name="sortName">
                <input type="hidden" value="${pageable.sorter.sortBy}" name="sortBy">
            </form>
        </div>
    </div>
    <script src="<c:url value="/template/js/AdminHome.js"/>"></script>
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
<script>
    function checkPassword(){
        password = document.getElementById("password").value;
        re_password = document.getElementById("rePassword").value;
        register = document.getElementById('create');
        checkLengthPassword();
        if(password!=re_password){
            document.getElementById("msg").innerHTML = "Mật khẩu không khớp";
            register.setAttribute('disabled','disabled');
            return false;
        }
        else{
            register.removeAttribute('disabled');
            document.getElementById("msg").innerHTML = "";
            checkLengthPassword();
            return true;
        }

    }
    function checkLengthPassword(){
        register = document.getElementById('create');
        password = document.getElementById("password").value;
        if (Number.parseInt(password.length) < 8) {
            register.setAttribute('disabled','disabled');
            document.getElementById("msg").innerHTML = "Mật Khẩu Phải có ít nhất 8 kí tự";
            return false;
        } else {
            register.removeAttribute('disabled');
            document.getElementById("msg").innerHTML= "";
            return true;
        }
    }
</script>
</body>
</html>