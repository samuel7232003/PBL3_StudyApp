// 3-5-21-4-44-5
var formsRate = document.getElementsByName('formsRate');
formsRate.forEach((element,index)=> {
    let items = element.children[1].children;
    for (let i = 0; i < items.length; i++) {
        let start = items[i].children[3].children;
        for (let j = 0; j < start.length; j++) {
            start[j].addEventListener('click', (e) => {
                items[i].children[4].defaultValue = j + 1;
                for (let k = 0; k < items[i].children[3].children.length; k++) {
                    items[i].children[3].children[k].style.color = '#8A8a8a';
                }
                for (let k = 0; k < j+1; k++) {
                    items[i].children[3].children[k].style.color = 'gold';
                }
                rate();
                console.log(element.children[3].defaultValue);
                console.log(checkRate());
                if (checkRate()) {
                    element.children[2].children[0].style.backgroundColor = '#75BA80';
                    element.children[2].children[0].setAttribute('type','submit');
                }
            })
        }
    }
    function rate () {
        let rate = '';
        for (let i = 0; i < items.length; i++) {
            if(rate===''){
                rate = `${items[i].children[2].defaultValue}-${items[i].children[4].defaultValue}`;
            } else {
                rate += `-${items[i].children[2].defaultValue}-${items[i].children[4].defaultValue}`;
            }
        }
        element.children[3].defaultValue = rate;
    }
    function checkRate() {
        let rate = element.children[3].defaultValue;
        if(rate.indexOf('--') === -1 && !rate.endsWith('-')) {
            return true;
        }
        return false;
    }
});
function timeStr(t) {
    var temp = t.split(':');
    return `${temp[0]}:${temp[1]}`;
}
function dateMeeting(date) {
    var temp = date.split('-');
    return `${temp[2]}-${temp[1]}-${temp[0]}`;
}

var time = document.querySelectorAll('.crete-rate .rate .time-date');
time.forEach((element, key) => {
    element.innerHTML = `<i class="fa-solid fa-clock"></i> ${timeStr(element.getAttribute('startTime'))}-${timeStr(element.getAttribute('endTime'))} ${dateMeeting(element.getAttribute('dateMeeting'))}`;
})