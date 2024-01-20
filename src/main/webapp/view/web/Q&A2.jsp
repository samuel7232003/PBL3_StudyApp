<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Study With Me | Ứng dụng tìm kiếm người bạn học cùng</title>
    <script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/template/css/Q&A2.css"/> ">
    <link rel="stylesheet" href="./fontawesome-free-6.4.0/css/all.min.css">
</head>
<body>
    <div id="main">
        <div id="header">
            <div class="header-info-user" >
                <img src="../image/ava.png" alt="">
                <a href="../edit-infor/edit-infor.html"><p class="header-info-user-name"> Ho va ten </p></a>
                <i class="fa-solid fa-user-pen"></i>
            </div>
            <div class="header-main">
                <li>
                    <a href="">
                        <button>
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </a>
                </li>
                <li>
                    <a href="">
                        <button class="header-main-mainbutton">
                            <i class="fa-solid fa-globe"></i>
                        </button>
                    </a>
                </li>
                <li>
                    <a href="">
                        <button>
                            <i class="fa-solid fa-book"></i>
                        </button>
                    </a>
                </li>
            </div>
            <div class="header-noti">
                <a href="">
                    <i class="fa-sharp fa-regular fa-bell"></i>
                </a>
            </div>
        </div>
        <div id="content">
            <div class="content-left">
                <div class="content-calender">
                    <div class="calender-title">
                        <p class="Lichcuaban">Lịch của bạn</p>
                    </div>
                    <div class="calender-main">
                    <script type="text/javascript">
                        var dt= new Date();
                        var month=dt.getMonth(); // read the current month
                        var year=dt.getFullYear(); // read the current year
                        
                        dt=new Date(year, month, 01);//Year , month,date format
                        
                        var first_day=dt.getDay(); //, first day of present month
                        //document.write("first_day=" + first_day + "<br><br>");
                        
                        dt.setMonth(month+1,0); // Set to next month and one day backward.
                        var last_date=dt.getDate(); // Last date of present month
                        //document.write(dt); // Last date in full
                        //document.write("<br><br> Last Date of the month =" + last_date + "<br><br>");
                        
                        var dy=1; // day variable for adjustment of starting date.
                        document.write ("<table><tr><td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>");
                        
                        for(i=0;i<=41;i++){
                        if((i%7)==0){document.write("</tr><tr>");} // if week is over then start a new line
                        if((i>= first_day) && (dy<= last_date)){
                        document.write("<td>"+ dy +"</td>");
                        dy=dy+1;
                        }else {document.write("<td> </td>");} // Blank dates.
                        } // end of for loop
                        
                        document.write("</tr></table>")
                    </script>
                    </div>
                </div>
                <div class="content-list-meeting">
                    <p class="text">Lịch hẹn</p>
                    <p class="all"><a href="">Tất cả</a></p>
                    <div class="content-left-item">
                        <p class="time-date"> 8 A.M - 9 A.M 08/03/2023</p>
                        <p class="with">Với</p>
                        <p class="name">Tran Le Nguyen</p>
                    </div>
                    <div class="content-left-item">
                        <p class="time-date"> 8 A.M - 9 A.M 08/03/2023</p>
                        <p class="with">Với</p>
                        <p class="name">Tran Le Nguyen</p>
                    </div>
                </div>
            </div>
            <div class="content-main">
                <h1 class="content-main-title">Hỏi Đáp</h1>
                <div class="content-main-search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="Gõ gì đó để tìm kiếm ...">
                </div>
                <div class="content-main-list">   
                    <div class="content-main-item">
                        <div class="profile">                            
                            <img class="avatar" src="../image/ava1.png" alt="">
                            <p class="name">Họ và Tên</p>
                        </div>
                        <div class="content">                                  
                                    <p class="content1"></i>Mọi người ơi!
                                    <br> Cho em xin công thức tình điện trở toàn phần của mạch hình sao và chứng mình ạ. Em cảm ơn mn ạ.</p>                                                                                                                                      
                        </div>                       
                        <div class="content2">Trả lời</div>                           
                        <div class="List_Answer">
                            <div class = "Answer1">
                                <img class="avatar1" src="../image/ava1.png" alt="">
                                <p class="name1">Họ và Tên</p>
                                <p class = "content_Ans">câu trả lời là ở đây</p>   
                            </div>
                            <div class = "Answer1">
                                <img class="avatar1" src="../image/ava1.png" alt="">
                                <p class="name1">Họ và Tên</p>
                                <p class = "content_Ans">câu trả lời là ở đây</p>   
                            </div>
                            <div class = "Answer1">
                                <img class="avatar1" src="../image/ava1.png" alt="">
                                <p class="name1">Họ và Tên</p>
                                <p class = "content_Ans">câu trả lời là ở đây</p>   
                            </div>                                                                                                                                             
                        </div>
                        <div class="container">
                            <textarea id="text" rows="5" cols="50"placeholder="Nhập câu trả lời của bạn"></textarea>
                            <button id="submit">Gửi</button>
                        </div>                                                                                                                                                                   
                        <a><button class="see-more" onclick="myFunction()">< 1 2... ></1.2...></button></a>                             
                    </div>
                   
                                                                        
                </div>               
            </div>
            <div class="content-right">
                <div class="list-friends">
                    <p class="list-friends-title">Danh sách bạn bè</p>
                    <div class="list-friends-item">
                        <img src="../image/ava.png" alt="">
                        <p class="name"><a href=""> Ho va ten </a></p>
                        <i class="fa-solid fa-mug-saucer"></i>
                    </div>
                    <div class="list-friends-item">
                        <img src="../image/ava.png" alt="">
                        <p class="name"><a href=""> Ho va ten </a></p>
                        <i class="fa-solid fa-mug-saucer"></i>
                    </div>
                    <div class="list-friends-item">
                        <img src="../image/ava.png" alt="">
                        <p class="name"><a href=""> Họ và tên </a></p>
                        <i class="fa-solid fa-mug-saucer"></i>
                    </div>
                    <div class="list-friends-item">
                        <img src="../image/ava.png" alt="">
                        <p class="name"><a href=""> Họ và tên </a></p>
                        <i class="fa-solid fa-mug-saucer"></i>
                    </div>
                </div>
                <div class="your-profile">
                    <p class="title">Đặt câu hỏi của bạn</p>
                    <div class="your-profile-main">
                        <div class="paper">
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>
                            <div class="line"></div>   
                        </div>
                        <div class="box">
                            <div class="title">Thêm từ khóa:</div>
                            <div class="square1"></div>       
                        </div>
                        <p class="add"><a href="">+</a></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="booking-apm" id="myDIV" >
            <div class="content">
                <img src="../image/background.png" alt="" class="background">
                <button class="exit" onclick="myFunction()">X</button> 
                <img src="../image/ava1.png" alt="" class="ava">
                <h1 class="name">Phúc Tân</h1>
                <form class="infor-apm">
                    <p class="title">Thông tin cuộc hẹn:</p>
                    <div class="list">
                        <i class="fa-solid fa-clock"></i><p class="time">8 A.M - 9 A.M</p>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-store"></i><p class="type-location">Coffee</p>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-location-dot"></i><p class="address">60 Nguyen Luong Bang</p>
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10688.770641389345!2d108.15040840348887!3d16.075210696191036!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218d68dff9545%3A0x714561e9f3a7292c!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBCw6FjaCBLaG9hIC0gxJDhuqFpIGjhu41jIMSQw6AgTuG6tW5n!5e0!3m2!1svi!2s!4v1681118874787!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                    <div class="list">
                        <i class="fa-solid fa-users"></i><p class="number">3 người khác tham gia</p>
                    </div>
                    <input class="submit" type="submit" value="KẾT NỐI NGAY">
                </form>
            </div>
        </div>
    </div>
    <script>
        function myFunction() {
          var x = document.getElementById("myDIV");
          if (x.style.display === "none") {
            x.style.display = "block";
          } else {
            x.style.display = "none";
          }
        }
    </script>
</body>
</html>