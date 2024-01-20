var dateOfBirth1 = document.querySelector(".content-main-edit-infor .item .date");
var date1 = dateOfBirth1.getAttribute('date');
dateOfBirth1.defaultValue = date1.slice(0,10);
var dateOfBirth2 = document.querySelector(".vice-main .edit-infor .list .date");
var date2 = dateOfBirth2.getAttribute('date');
dateOfBirth2.defaultValue = date2.slice(0,10);

var buttonEdit = document.querySelector('.content-main-edit-infor .edit');
if(buttonEdit) {
    buttonEdit.onclick = function (e){
        var div = document.querySelector('.vice-main');
        div.style.display = 'block';
    };
}

var buttonExit = document.querySelector('.vice-main .edit-infor .exit');
buttonExit.onclick = function (e) {
    var div = document.querySelector('.vice-main');
    div.style.display = 'none';
}

function myFunction() {
    var x = document.getElementById("myDIV");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

var inputAvatar = document.querySelector('.vice-main .edit-infor .edit-ava .input-img');
inputAvatar.oninput = function (e) {
    Object.assign(e.target.nextElementSibling.style, {
        cursor: 'pointer',
        backgroundColor: '#18C0e6'
    });
    e.target.nextElementSibling.removeAttribute('disabled');
};

var inputBackground = document.querySelector('.vice-main .edit-infor .edit-background .input-img');
inputBackground.oninput = function (e) {
    Object.assign(e.target.nextElementSibling.style, {
        cursor: 'pointer',
        backgroundColor: '#18C0e6'
    });
    e.target.nextElementSibling.removeAttribute('disabled');
};

var divEditProfile = document.getElementById('editProfile');
divEditProfile.onchange = function (e) {
    Object.assign(divEditProfile.lastElementChild.style, {
        cursor: 'pointer',
        backgroundColor: '#18C0e6'
    });
    divEditProfile.lastElementChild.removeAttribute('disabled');
};

var selectGender = document.querySelector(' .vice-main .edit-infor .list .list-sex');
if (selectGender.getAttribute('value')==='0'){
    selectGender.childNodes[1].setAttribute('selected','selected');
} else if (selectGender.getAttribute('value')==='1'){
    selectGender.childNodes[3].setAttribute('selected','selected');
} else {
    selectGender.childNodes[5].setAttribute('selected','selected');
}


function timeStr(t) {
    var temp = t.split(':');
    return `${temp[0]}:${temp[1]}`;
}

var time = document.querySelectorAll(".list-apm .item-apm .times");
time.forEach(function (element) {
    element.innerHTML = timeStr(element.getAttribute("startTime")) + ' - ' + timeStr(element.getAttribute("endTime"));
})

var date = document.querySelectorAll(".list-apm .item-apm .date");

var groupStar = document.querySelectorAll('.form-group');
var length = groupStar.length;
for(var j = 0; j < length; j++){
    var noStar = Number.parseInt(groupStar[j].getAttribute('value'));
    for(var i = 1; i < noStar*2; i+=2){
        groupStar[j].childNodes[i].classList.add('checked');
    }
}

var chancePwd =document.querySelector('.chance-password .submit')
var exitChancePwd = document.querySelector('.chance-pwd .exit')
var divChancePwd = document.querySelector('.div-chance-pwd')

chancePwd.addEventListener('click', function (){
    divChancePwd.classList.remove('hide')
})

exitChancePwd.addEventListener('click', function (){
    divChancePwd.classList.add('hide')
})

function dateMeeting(date) {
    var temp = date.split('-');
    return `${temp[2]}-${temp[1]}-${temp[0]}`;
}

var dates = document.querySelectorAll('.item-apm .date div');
dates.forEach((element,index)=>{
    element.innerText = dateMeeting(element.innerText);
})

var buttonSeeMore = document.getElementById('buttonSeeMore');
console.log(buttonSeeMore);
if(buttonSeeMore){
    buttonSeeMore.addEventListener('click', function (e) {
        var totalItem = document.getElementById('totalItem');
        var totalAppointment = document.getElementById('totalAppointment');
        if (Number.parseInt(totalItem.defaultValue) < Number.parseInt(totalAppointment.defaultValue)) {
            var maxItem = document.getElementById('maxItem');
            var form = document.getElementById('seeMore');
            maxItem.defaultValue = Number.parseInt(totalItem.defaultValue) + 100;
            form.submit();
        }
    })
}

function checkPassword() {
    var newPassword = document.getElementById('newPassword');
    var rePassword = document.getElementById('rePassword');
    var errorPassword = document.getElementById('errorPassword');
    if (newPassword.value !== rePassword.value) {
        errorPassword.innerText = 'Mật khẩu không khớp';
    } else {
        errorPassword.innerText = '';
        if (checkNewPassword(newPassword)) {
            var buttonSubmitChangePassword = document.querySelector('.div-chance-pwd .chance-pwd .chance-pwd-form .submit');
            buttonSubmitChangePassword.style.backgroundColor = '#0fb8de';
            buttonSubmitChangePassword.removeAttribute('disabled');
        }
    }
}

function checkNewPassword(element){
    var errorNewPassword = document.getElementById('errorNewPassword');
    if(element.value.length < 8) {
        errorNewPassword.innerText = 'Mật khẩu phải hơn 8 kí tự'
    } else {
        errorNewPassword.innerText = '';
        return true;
    }
}

var errorChange = document.getElementById('errorChange');
if(errorChange.innerText !==""){
    divChancePwd.classList.remove('hide');
}

var buttonAgree = document.getElementById('agree');
if (buttonAgree) {
    buttonAgree.onclick = function (e) {
        var reply = document.getElementById('reply');
        reply.defaultValue = 'agree';
        var formRely = document.getElementById('formReply');
        formRely.submit();
    }
}


var buttonDeny = document.getElementById('deny');
if(buttonDeny) {
    buttonDeny.onclick = function (e) {
        var reply = document.getElementById('reply');
        reply.defaultValue = 'deny';
        var formRely = document.getElementById('formReply');
        formRely.submit();
    }
}

var replyFriend = document.querySelector('.friend.reply')
var replyDiv = document.querySelector('.form-reply')
if(replyFriend) {
    replyFriend.addEventListener('click', function (e){
        replyDiv.classList.toggle('hide');
        e.stopPropagation();
    })
}
if (replyDiv) {
    document.addEventListener('click', function (e){
        if(e.target!==replyDiv){
            replyDiv.classList.add('hide')
        }
    })
}
