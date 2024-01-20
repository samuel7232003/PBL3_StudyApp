var openHeader = document.querySelector('.menu-btn')
var menuHeader = document.querySelector('.header-menu')

openHeader.addEventListener('click', function (){
    menuHeader.classList.toggle('hide')
} )

var openNoti = document.querySelector('.noti-btn')
var notiContent = document.querySelector('.noti-content')
if (openNoti){
    openNoti.addEventListener('click', function (){
        notiContent.classList.toggle('hide')
    })
}
// var buttonAgreeNavbar = document.getElementById('agreeNavbar');
// console.log(buttonAgreeNavbar);
// if (buttonAgreeNavbar) {
//     buttonAgreeNavbar.onclick = function (e) {
//     var reply = document.getElementById('replyNavbar');
//     reply.defaultValue = 'agree';
//     var formRely = document.getElementById('formReplyNavbar');
//     formRely.submit();
//     }
// }
//
//
// var buttonDenyNavbar = document.getElementById('denyNavbar');
// if (buttonAgreeNavbar) {
//     buttonDenyNavbar.onclick = function (e) {
//     var reply = document.getElementById('replyNavbar');
//     reply.defaultValue = 'deny';
//     var formRely = document.getElementById('formReplyNavbar');
//     formRely.submit();
//     }
// }
var formRely = document.getElementsByName('formReplyNavbar');
if(formRely){
    formRely.forEach((element, index) => {
        element.children[4].addEventListener('click', (e)=> {
            element.children[3].defaultValue = 'agree';
            element.submit();
        })
        element.children[5].addEventListener('click', (e) => {
            element.children[3].defaultValue = 'deny';
            element.submit();
        })
    })
}
