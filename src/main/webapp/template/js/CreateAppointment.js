function hideWards(){
    var wards = document.getElementById('list-ward');
    var length = wards.length;
    for(var i = 1; i < length; i++){
        wards[i].setAttribute('hidden','');
    }
}
hideWards();
function showWards(district) {
    var wards = document.getElementById('list-ward');
    var length = wards.length;
    for(var i = 1; i < length; i++){
        if(wards[i].getAttribute('district') === district) {
            wards[i].removeAttribute('hidden');
        }
    }
}
var districts = document.getElementById('list-district');
districts.onchange = function (e) {
    hideWards();
    showWards(e.target.value);
}
function compareTime(start,end) {
    var startTime = start.split(':');
    var endTime = end.split(':');
    var error = "";
    if (((Number.parseInt(endTime[0])*60 + Number.parseInt(endTime[1])) - (Number.parseInt(startTime[0])*60 + Number.parseInt(startTime[1]))) >= 45 ) {
        return error;
    } else if (((Number.parseInt(endTime[0])*60 + Number.parseInt(endTime[1])) - (Number.parseInt(startTime[0])*60 + Number.parseInt(startTime[1]))) < 0 ) {
        error = "Thời gian không hợp lệ!";
        return error;
    } else {
        error = "Tổng thời gian phải lơn hơn 45 phút!";
        return error;
    }
}
var buttonSubmit = document.querySelector('.button');
buttonSubmit.addEventListener('click',function (e) {
    var form = document.querySelector('.create-app-form');
    var startTime = document.querySelector('.time-begin');
    var endTime = document.querySelector('.time-end');
    var dateMeeting = document.getElementById('dateMeeting');
    var max = document.getElementById('max');
    var addressTypes = document.getElementById('addressType');
    var totalAppointment = document.getElementById('totalAppointment');
    if(Number.parseInt(totalAppointment.value) >= 3) {
        var error = document.getElementById('errorAppointment');
        error.innerText = 'Chỉ được tạo tối đa 3 cuộc hẹn!';
        return;
    }
    if(!dateMeeting) {
        var errorDate = document.getElementById('errorDate');
        errorDate.innerText = 'Chưa chọn ngày!';
        return;
    }
    if(max.value === "Số người tối đa"){
        max.style.borderColor = 'red';
        return;
    } else {
        max.style.removeProperty('border-color');
    }
    if (addressTypes.value === "Chọn địa điểm") {
        addressTypes.style.borderColor = 'red';
        return;
    } else {
        addressTypes.style.removeProperty('border-color');
    }
    if(compareTime(startTime.value, endTime.value) === "") {
        if (districts.value === "Chọn quận/huyện") {
            districts.style.borderColor = 'red';
        } else {
            districts.style.removeProperty('border-color');
            var wards = document.getElementById('list-ward');
            if (wards.value === "Chọn phường/xã"){
                wards.style.borderColor = 'red';
            } else {
                wards.style.removeProperty('border-color');
                var address = document.querySelector('.detail-location');
                if (address.value === "") {
                    address.style.borderColor = 'red';
                } else {
                    address.style.removeProperty('border-color');
                    form.submit();
                }
            }
        }
    } else {
        var errorTime = document.getElementById('errorTime');
        errorTime.innerText = compareTime(startTime.value, endTime.value);
    }
})

var times = document.getElementsByName('time');

function time(time) {
    var temp = time.split(':');
    return `${temp[0]}:${temp[1]}`;
}
times.forEach((element,index)=> {
    var startTime = element.getAttribute("startTime");
    var endTime = element.getAttribute("endTime");
    element.innerText = `${time(startTime)} - ${time(endTime)}`;
})

var dateMeetings = document.querySelectorAll('.create-appointment .your-calendar .content .item .date');
console.log(dateMeetings);

function date(date) {
    var temp = date.split('-');
    return `${temp[2]}-${temp[1]}-${temp[0]}`;
}
dateMeetings.forEach((element,index)=>{
    element.innerText = date(element.innerText);
})