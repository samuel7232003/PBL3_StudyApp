<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Study With Me | Ứng dụng tìm kiếm người bạn học cùng</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/PBL3/template/css/login.css">
    <link rel="icon" href="/PBL3/template/image/Study1.png" type="image/icon type">
    <link rel="stylesheet" href="/PBL3/template/css/register.css">
    <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
    <script src="<c:url value="/template/js/JQLogin.js"/>"></script>
</head>
<body>

   <div id="main">
        <div id="header">
            <li><a href="">Thông tin về chúng tôi <i class="fa-sharp fa-solid fa-caret-down"></i></a></li>
            <li><a href="">Đóng góp <i class="fa-sharp fa-solid fa-caret-down"></i></a></li>
        </div>
        <div id="content">
            <div class="content-slogan">
                <p class="content-sologan-text">Nơi giúp bạn tìm kiếm những người bạn học cùng ...</p>
            </div>
            <div class="content-login">
                <div class="content-login-title">
                    <h1 class="content-login-title-text">Đăng Nhập</h1>
                    <li class="login-icon"> <i class="fa-brands fa-facebook"></i> </li>
                    <li class="login-icon"> <i class="fa-brands fa-github"></i> </li>
                    <li class="login-icon"> <i class="fa-brands fa-google"></i> </li>
                </div>
                
			<c:if test="${user==null}">
				<form action="login" method = "post">
                    <input class="login-submit" name="email" id = "email-login" type="email" required="required" placeholder="Email" value = "${emailLogin}">
                    <input class="login-submit-pwd" name="password" id = "password" type="password" required="required" placeholder="Mật khẩu" required>
                    <p class="error">${errorLogin}</p>
                    <input class="login-submit-button" type="submit" value="ĐĂNG NHẬP">
                </form>
                <p class="login-signup">Bạn chưa có tài khoản? <button class="button-signin">Đăng kí</button></p>
			</c:if>
			
			<c:if test="${user!=null}">
				<div id="content-user">
                    <img src="data:image/jpeg;base64,${user.avatar}"/>
                    <h1 class="user-name">${user.firstName} ${user.lastName}</h1>
                    <li>
                        <button class="button-cont">
                            <a href="<c:url value="/home?page=1&maxPageItem=6&sortName=createdDate&sortBy=desc"/> ">Tiếp tục đăng nhập</a>
                        </button>
                    </li>
                    <li class="last-list">
                        <button class="button-logout">
                            <a href="/PBL3/logout">Đăng xuất </a> 
                        </button>
                    </li>
                </div>
			</c:if>
            </div>
            <div class="content-signin">
                <div class="content-signin-title">
                    <h1 class="content-signin-title-text">Đăng Kí</h1>
                    <li class="signin-icon"> <i class="fa-brands fa-facebook"></i> </li>
                    <li class="signin-icon"> <i class="fa-brands fa-github"></i> </li>
                    <li class="signin-icon"> <i class="fa-brands fa-google"></i> </li>
                </div>
                <div>
                <form action="register"  method ="post">
                			<%--@declare id="re_password"--%><input class="signin-submit" name="firstName" id="firstName" type="text" placeholder="Họ" required="required" value="${firstName }">
                			<input class="signin-submit" name="lastName" id="lastName" type="text" placeholder="Tên" required="required" value="${lastName }">
                			<input name="sex" id="male" type="radio" required="required" value="male" class="sex">
                			<label for="male">Nam</label>
                			<input name="sex" id="female" type="radio" required="required" value="female" class="sex">
                			<label for="female">Nữ</label>
                		    <input name="sex" id="another" type="radio" required="required" value="another" class="sex">
                			<label for="another">Khác</label>
                			<input class="signin-submit" name="email" id = "email" type="email" placeholder="Email" 
                    		required="required" value = "${email}">
                		<c:if test="${error_email!= null }">
                				<div id = "error_email">${error_email }</div>
                		</c:if>
                			<input class="signin-submit" name="password" id = "password-register" type="password" placeholder="Mật khẩu" required="required" onkeyup = "checkPassword()">
                			<input class="signin-submit" name="re_password" id ="re_password-register" type="password"
                    placeholder="Nhập lại mật khẩu" required = "required" onkeyup = "checkPassword()">
                			<label class="pwd-not-same" for="re_password"><p id = "msg"></p> </label>
                			<div id = "error_password">${error_password}</div>
                    <input class="signin-submit-button" type="submit" name = "dangKi" id = "dangKi" value="ĐĂNG KÍ" disabled>
                </form>
                </div>
                <p class="login-signup">Bạn đã có tài khoản? <button class="button-login">Đăng nhập</button></p>
            </div>
        </div>
    </div>
</body>
<script>
	function checkPassword(){
		password = document.getElementById("password-register").value;
		re_password = document.getElementById("re_password-register").value;
        register = document.getElementById('dangKi');
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
            register = document.getElementById('dangKi');
            password = document.getElementById("password-register").value;
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
</html>