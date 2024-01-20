CH
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="icon" href="<c:url value="/template/image/Study1.png"/> " type="image/icon type">
    <link rel="stylesheet" href="<c:url value="/template/css/register.css"/>">
</head>
<body>
<c:if test="${user!=null }">
    <c:redirect url="/login"/>
</c:if>

<div id="main">
    <div id="header">
        <li><a href="">Thông tin về chúng tôi <i class="fa-sharp fa-solid fa-caret-down"></i></a></li>
        <li><a href="">Đóng góp <i class="fa-sharp fa-solid fa-caret-down"></i></a></li>
    </div>
    <div id="content">
        <div class="content-signin">
            <div class="content-signin-title">
                <h1 class="content-signin-title-text">Đăng Kí</h1>
                <li class="signin-icon"><i class="fa-brands fa-facebook"></i></li>
                <li class="signin-icon"><i class="fa-brands fa-github"></i></li>
                <li class="signin-icon"><i class="fa-brands fa-google"></i></li>

            </div>
            <div>
                <form action="/PBL3/register" method="post">
                    <input class="signin-submit" name="firstName" id="firstName" type="text" placeholder="Họ"
                           required="required" value="${firstName }">
                    <input class="signin-submit" name="lastName" id="lastName" type="text" placeholder="Tên"
                           required="required" value="${lastName }">

                    <input name="sex" id="male" type="radio" required="required" value="male" class="sex">
                    <label for="male">Nam</label>
                    <input name="sex" id="female" type="radio" required="required" value="female" class="sex">
                    <label for="female">Nữ</label>
                    <input name="sex" id="another" type="radio" required="required" value="another" class="sex">
                    <label for="another">Khác</label>

                    <input class="signin-submit" name="email" id="email" type="email" placeholder="Email"
                           required="required" value="${email }">


                    <c:if test="${error_email!= null }">
                        <div id="error_email">${error_email }</div>
                    </c:if>
                    <input class="signin-submit" name="password" id="password" type="password" placeholder="Mật khẩu"
                           required="required" onkeyup="checkPassword()">

                    <input class="signin-submit" name="re_password" id="re_password" type="password"
                           placeholder="Nhập lại mật khẩu" required="required" onkeyup="checkPassword()">


                    <label class="pwd-not-same" for="re_password"><p id="msg"></p></label>


                    <div id="error_password">${error_password }</div>


                    <input class="signin-submit-button" type="submit" name="dangKi" id="dangKi" value="ĐĂNG KÍ">
                </form>
            </div>
            <p class="login-signup">Bạn đã có tài khoản? <a href="/PBL3/login">Đăng nhập</a></p>
        </div>
    </div>
</div>
</body>

<script>
    function checkPassword() {
        password = document.getElementById("password").value;
        re_password = document.getElementById("re_password").value;

        if (password != re_password) {
            document.getElementById("msg").innerHTML = "Mật khẩu không khớp";
            return false;
        } else {
            document.getElementById("msg").innerHTML = "";
            return true;
        }
    }
</script>
</html>
