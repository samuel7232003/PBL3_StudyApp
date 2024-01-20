var openDetailDiv = document.querySelectorAll('.content-main-item')
var detailDiv = document.querySelector('.booking-apm')
var exitDetailDiv = document.querySelector('.booking-apm .exit')
var panelDetailDiv = document.querySelector('.booking-apm .panel')
var inforDetailDiv = document.querySelector('.booking-apm .infor')
var formBooking = document.querySelector('.booking-apm .infor-apm');

console.log(detailDiv)
console.log(openDetailDiv)

openDetailDiv.forEach((item, index)=>{
    item.children[2].addEventListener('click', function (){
        //reset
        inforDetailDiv.nextElementSibling.children[2].innerHTML = "";
        //background
        detailDiv.children[1].children[0].attributes[0].value = item.children[0].children[0].attributes[0].value
        //avatar
        detailDiv.children[1].children[1].attributes[0].value = item.children[0].children[1].attributes[0].value
        //name
        detailDiv.children[1].children[2].childNodes[0].nodeValue = item.children[0].children[2].childNodes[0].nodeValue

        var itemInner = item.children[1].children[0]
        //date
        inforDetailDiv.children[1].children[1].childNodes[0].nodeValue = itemInner.children[0].childNodes[0].nodeValue
        //time
        inforDetailDiv.children[1].children[2].childNodes[0].nodeValue = itemInner.children[1].childNodes[0].nodeValue
        //type
        inforDetailDiv.children[2].children[1].childNodes[0].nodeValue = item.children[1].children[0].children[3].value
        //location
        if (itemInner.children[2].childNodes[1]) {
            inforDetailDiv.children[3].children[1].childNodes[0].nodeValue = itemInner.children[2].childNodes[1].nodeValue
        }
        //
        inforDetailDiv.nextElementSibling.children[1].innerHTML = `(<i class="fa-solid fa-users"></i> ${item.children[1].children[0].children[5].value} / ${item.children[1].children[0].children[4].value})`;
        let listMemberHTML = "";
        let listMember =  item.children[1].children[0].children[6].children;
        for(let i = 0; i < listMember.length; i++){
            listMemberHTML+= `<div class="item"> <img src="data:image/jpeg;base64,${listMember[i].children[0].value}">
                           <a class="name">${listMember[i].children[1].value}</a> </div>`;
        }
        if (listMemberHTML !== "") {
            inforDetailDiv.nextElementSibling.children[2].innerHTML = listMemberHTML;
        }
        //idAppointment
        formBooking.children[2].defaultValue = item.children[1].children[1].defaultValue;
        detailDiv.classList.remove('hide')
    })
})

exitDetailDiv.addEventListener('click', function (){
    detailDiv.classList.add('hide')
})

panelDetailDiv.addEventListener('click', function (){
    detailDiv.classList.add('hide')
})

function timeStr(time) {
    var temp = time.split(':');
    return `${temp[0]}:${temp[1]}`
}

var time = document.querySelectorAll(".item .time");
time.forEach(function (element) {
    element.innerHTML = timeStr(element.getAttribute("startTime")) + ' - ' + timeStr(element.getAttribute("endTime"));
})

function date(date) {
    var temp = date.split('-');
    return `${temp[2]}-${temp[1]}-${temp[0]}`;
}

var dateMeetings = document.querySelectorAll('.item .date');
dateMeetings.forEach((element,index)=>{
    element.innerText = date(element.innerText);
})