var openDetailDiv = document.querySelectorAll('.content-list-user .item')
var DetailDiv = document.querySelector('.detail-div')

var detailDivMain = document.querySelector('.detail-div .detail-div-main')
var detailDivForm = document.querySelector('.detail-div form')

openDetailDiv.forEach((item, index) => {
    item.addEventListener('click', function (){
        //background
        detailDivMain.children[1].attributes[0].value = item.children[5].value
        //avatar
        detailDivMain.children[2].attributes[0].value = item.children[0].children[0].attributes[0].value
        //name
        detailDivMain.children[3].innerText=item.children[0].children[1].childNodes[0].nodeValue
        //sex
        detailDivForm.children[1].children[2].innerText = item.children[1].children[1].childNodes[0].nodeValue
        //school
        detailDivForm.children[2].children[2].innerText = item.children[4].defaultValue
        //birth
        detailDivForm.children[3].children[2].attributes[1].nodeValue = item.children[2].children[1].attributes[2].nodeValue

        DetailDiv.classList.remove('hide')
    })
})

console.log(detailDivForm)

var exitDetailDiv = document.querySelector('.detail-div .exit')

exitDetailDiv.addEventListener('click', function (){
    DetailDiv.classList.add('hide')
})

var formUnSet = document.getElementsByName('formUnSet');
if (formUnSet) {
    formUnSet.forEach((element, index) => {
        element.onclick = function (e) {
            e.stopImmediatePropagation();
        }
    })
}