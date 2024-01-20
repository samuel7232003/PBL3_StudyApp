

function isLeapYear(year) {
    if (year % 4 == 0 && year % 100 != 0)
        return true;
    if (year % 400 == 0)
        return true;
    return false;
}

function Nday(month,year) {
    switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            if (isLeapYear(year))
                return 29;
            return 28;
    }
    return 0;
}

function getDay(month,year) {
    var n = year - 1;
    var d = n * 365 + n / 4 - n / 100 + n / 400;
    for (var i = 1; i < month; i++)
        d += Nday(i, n + 1);
    return d;
}

function getThu(month,year) {
    return getDay(month,year) % 7 + 2;
}

function update(month,year) {
    var a = [[],[],[],[],[],[]];
    var thu = Math.floor(getThu(month,year));
    var day = Nday(month,year);
    var pday = 0;
    if (month > 1){
        pday = Nday(month - 1, year);
    }
    else{
        pday = Nday(12, year - 1);
    }
    var start = thu - 1;
    if (start == 7){
        start = 0;
    }
    var I = 0, J = start;
    for (var i = 1; i <= day; i++) {
        a[I][J] = i;
        J++;
        if (J == 7) {
            J = 0;
            I++;
        }
    }
    for (var i = start - 1; i >= 0; i--) {
        a[0][i] = pday--;
    }
    var st = 1;
    while (!(I == 6 && J == 0)) {
        a[I][J] = st++;
        J++;
        if (J == 7) {
            J = 0;
            I++;
        }
    }
    return a;
}

function calender(month,year){
    var href = document.getElementById('href').defaultValue;
    var dateMeetingElement = document.querySelector('#dateMeeting');
    console.log(dateMeetingElement)
    var dateMeeting = [];
    if (dateMeetingElement){
        dateMeeting = dateMeetingElement.defaultValue.split('-');
    } else {
        dateMeeting = [0,0,0];
    }
    var calender = document.querySelector('.calender');
    var calenderHTML = `<p class="month">${month}</p><p class="icon" >/</p> <p class="year">${year}</p> <table><tr class="tr_1"><td class="CN">CN</td><td>T2</td><td>T3</td><td>T4</td><td>T5</td><td>T6</td><td>T7</td>`;
    var today = update(month,year);
    for(var i = 0; i < today.length; i++){
        var row = today[i];
        calenderHTML+='<tr class="tr">';
        for(var j = 0; j < row.length; j++){
            if((i <= 1) && row[j] > 15) {
                var pMonth = month;
                var pYear = year;
                if(pMonth == 1){
                    pMonth = 12;
                    pYear -= 1;
                } else {
                    pMonth -=1;
                }
                if (Number.parseInt(dateMeeting[0]) == pYear && Number.parseInt(dateMeeting[1]) == (pMonth) && Number.parseInt(dateMeeting[2]) == row[j]) {
                    calenderHTML+= `<td class = "day-chose"> <a href="${href}&dateMeeting=${pYear}-${pMonth}-${row[j]}">${row[j]}</a> </td>`;
                    continue;
                }
                if(checkDate(year, month - 1, row[j])){
                    calenderHTML+= `<td class = "green"> <a href="${href}&dateMeeting=${pYear}-${pMonth}-${row[j]}">${row[j]}</a> </td>`;
                }else{
                    calenderHTML+= `<td> <a class = "gray" href="${href}&dateMeeting=${pYear}-${pMonth}-${row[j]}">${row[j]}</a> </td>`;
                }
            } else if ((i >= today.length - 2) && row[j] < 15){
                var pMonth = month;
                var pYear = year;
                if(pMonth == 12){
                    pMonth = 1;
                    pYear += 1;
                } else {
                    pMonth +=1;
                }
                if (Number.parseInt(dateMeeting[0]) == pYear && Number.parseInt(dateMeeting[1]) == (pMonth) && Number.parseInt(dateMeeting[2]) == row[j]) {
                    calenderHTML+= `<td class = "day-chose"> <a href="${href}&dateMeeting=${pYear}-${pMonth}-${row[j]}">${row[j]}</a> </td>`;
                    continue;
                }
                if(checkDate(year, month + 1, row[j])){
                    calenderHTML+= `<td class = "green"> <a href="${href}&dateMeeting=${pYear}-${pMonth}-${row[j]}" >${row[j]}</a> </td>`;
                }else{
                    calenderHTML+= `<td> <a class = "gray" href="${href}&dateMeeting=${pYear}-${pMonth}-${row[j]}">${row[j]}</a> </td>`;
                }
            } else {
                if (Number.parseInt(dateMeeting[0]) == year && Number.parseInt(dateMeeting[1]) == month && Number.parseInt(dateMeeting[2]) == row[j]) {
                    calenderHTML+= `<td class = "day-chose"> <a href="${href}&dateMeeting=${year}-${month}-${row[j]}">${row[j]}</a> </td>`;
                    continue;
                }
                if(checkDate(year, month, row[j])){
                    calenderHTML+= `<td class = "green"> <a href="${href}&dateMeeting=${year}-${month}-${row[j]}">${row[j]}</a> </td>`;
                } else {
                    if(j==0){
                        calenderHTML+= `<td> <a href="${href}&dateMeeting=${year}-${month}-${row[j]}" class="CN">${row[j]}</a> </td>`;
                    }
                    else calenderHTML+= `<td> <a href="${href}&dateMeeting=${year}-${month}-${row[j]}" class = "black">${row[j]}</a> </td>`;
                }
            }
        }
        calenderHTML+='</tr>';
    }
    calenderHTML+='</table>'
    calender.innerHTML = calenderHTML;
}

function checkDate(year,month,day){
    var dateAppointmentArr = [];
    var dateAppointment = document.getElementsByName('date');
    dateAppointment.forEach(element => {
        dateAppointmentArr.push(getDate(element.defaultValue));
    });
    for(var i = 0; i < dateAppointmentArr.length; i++){
        var date = dateAppointmentArr[i].split('-');
        if(year == Number.parseInt(date[0]) && month == Number.parseInt(date[1]) && day == Number.parseInt(date[2])) {
            return true;
        }
    }
}

var buttonLeft = document.getElementById("left");
buttonLeft.onclick = function (e) {
    var month = Number.parseInt(document.querySelector('.month').textContent);
    var year = Number.parseInt(document.querySelector('.year').textContent);
    if(month > 1){
        calender(month - 1, year);
    } else if (month == 1){
        calender(12,year-1);
    }
}

var buttonRight = document.getElementById("right");
buttonRight.onclick = function (e) {
    var month = Number.parseInt(document.querySelector('.month').textContent);
    var year = Number.parseInt(document.querySelector('.year').textContent);
    if(month < 12){
        calender(month + 1, year);
    } else if (month == 12) {
        calender(1,year+1);
    }
}

function getDate(date){
    return date.split(' ')[0];
}

var dateMeetingElement = document.getElementById('dateMeeting');
var dateMeeting = [];
if (dateMeetingElement){
    dateMeeting = dateMeetingElement.defaultValue.split('-');
    calender(Number.parseInt(dateMeeting[1]), Number.parseInt(dateMeeting[0]));
} else {
    var date = new Date();
    calender(date.getMonth() + 1, date.getFullYear());
}