var dateTime = document.querySelectorAll('.content-left-item .time-date');
dateTime.forEach((element, index) => {
    var dateMeeting = element.getAttribute('dateMeeting');
    var startTime = element.getAttribute('startTime');
    var endTime = element.getAttribute('endTime');
    element.innerText = `${timeStr(startTime)} - ${timeStr(endTime)} ${date(dateMeeting)}`
})
function date(date) {
    var temp = date.split('-');
    return `${temp[2]}-${temp[1]}-${temp[0]}`;
}
function timeStr(time) {
    var temp = time.split(':');
    return `${temp[0]}:${temp[1]}`
}