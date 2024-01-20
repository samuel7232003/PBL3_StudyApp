var itemUsers = document.querySelectorAll('.content-list-user .item')
var detailDiv = document.querySelector('.detail-div')
var exitDetailDiv = document.querySelector('.detail-div .exit')
var panelDetail = document.querySelector('.detail-div .panel')
var panelDelete = document.querySelector('.confirm-div.delete .panel')
var panelLock = document.querySelector('.confirm-div.lock .panel')
var detailDivMain = document.querySelector('.detail-div .detail-div-main')
var detailDivForm = document.querySelector('.detail-div .detail-div-main form')
var detailDivChancePwd = document.querySelector('.detail-div .detail-div-main form .chance-password')
itemUsers.forEach((item, index)=> {
    item.addEventListener('click', function (e){
        //reset
        Object.assign(detailDivChancePwd.children[3].style, {
            backgroundColor: 'gray',
            cursor: 'inherit'
        })
        detailDivChancePwd.children[3].setAttribute('type','button');
        detailDivForm.children[1].children[1].value = "";
        detailDivForm.children[1].children[2].value = "";
        detailDivChancePwd.children[1].children[1].value = "";
        detailDivChancePwd.children[2].children[1].value = "";
        error.innerText = "";
        //background
        detailDivMain.children[1].attributes[0].value = item.children[5].value
        //avatar
        detailDivMain.children[2].attributes[0].value = item.children[0].children[0].attributes[0].value
        //name
        detailDivMain.children[3].innerText = item.children[0].children[1].childNodes[0].nodeValue
        //firstName
        detailDivForm.children[1].children[1].setAttribute('placeholder',item.children[7].value);
        //lastName
        detailDivForm.children[1].children[2].setAttribute('placeholder',item.children[8].value);
        //sex
        detailDivForm.children[1].children[3].children[2].innerHTML += `<option selected hidden> ${item.children[1].children[1].childNodes[0].nodeValue} </option>`
        //school
        detailDivForm.children[1].children[4].children[2].innerHTML += `<option selected hidden> ${item.children[4].defaultValue} </option>`
        //birth
        detailDivForm.children[1].children[5].children[2].value = item.children[2].children[1].attributes[2].value
        //email
        detailDivChancePwd.children[1].children[1].attributes[1].value = item.children[6].value
        //password
        // detailDivChancePwd.children[2].children[1].attributes[1].value = item.children[7].value
        //id
        detailDivForm.children[1].children[7].value = item.children[9].value;
        detailDiv.classList.remove('hide');
    })
    if(item.children[10]){
        //background
        detailDivMain.children[1].attributes[0].value = item.children[5].value
        //avatar
        detailDivMain.children[2].attributes[0].value = item.children[0].children[0].attributes[0].value
        //name
        detailDivMain.children[3].innerText = item.children[0].children[1].childNodes[0].nodeValue
        //firstName
        detailDivForm.children[1].children[1].setAttribute('placeholder',item.children[7].value);
        //lastName
        detailDivForm.children[1].children[2].setAttribute('placeholder',item.children[8].value);
        //sex
        detailDivForm.children[1].children[3].children[2].innerHTML += `<option selected hidden> ${item.children[1].children[1].childNodes[0].nodeValue} </option>`
        //school
        detailDivForm.children[1].children[4].children[2].innerHTML += `<option selected hidden> ${item.children[4].defaultValue} </option>`
        //birth
        detailDivForm.children[1].children[5].children[2].value = item.children[2].children[1].attributes[2].value
        //email
        detailDivChancePwd.children[1].children[1].attributes[1].value = item.children[6].value
        //password
        // detailDivChancePwd.children[2].children[1].attributes[1].value = item.children[7].value
        //id
        detailDivForm.children[1].children[7].value = item.children[9].value;
        detailDiv.classList.remove('hide');
    }
});
detailDivForm.onchange = function (e) {
    detailDivChancePwd.children[3].removeAttribute('style');
    Object.assign(detailDivChancePwd.children[3].style, {
        backgroundColor: 'rgb(117, 186, 128)'
    })
    detailDivChancePwd.children[3].setAttribute('type','submit');
}
var error = document.getElementById('error');
if(error.innerText !== "") {
    detailDiv.classList.remove('hide');
}

exitDetailDiv.addEventListener('click', function (){
    detailDiv.classList.add('hide');
} )

panelDetail.addEventListener('click', function (){
    detailDiv.classList.add('hide');
})

var deleteBtn = document.querySelectorAll('.content-list-user .item .button.remove')
var confirmDiv = document.querySelector('.confirm-div.delete')
var exitConfirm = document.querySelector('.confirm-delete .list .exit')

deleteBtn.forEach((item, index) =>{
    item.addEventListener('click', function (e){
        confirmDiv.children[1].children[1].value = item.parentElement.parentElement.children[9].value;
        confirmDiv.classList.remove('hide')
        e.stopPropagation()
    })
})

panelDelete.addEventListener('click', function (){
    confirmDiv.classList.add('hide')
})

exitConfirm.addEventListener('click', function (){
    confirmDiv.classList.add('hide')
})

var lockBtn = document.querySelectorAll('.content-list-user .item .button.lock')
var confirmDivLock = document.querySelector('.confirm-div.lock')
var exitConfirmLock = document.querySelector('.confirm-lock .list .exit')

lockBtn.forEach((item, index) =>{
    item.addEventListener('click', function (e){
        confirmDivLock.children[1].children[1].value = item.parentElement.parentElement.children[9].value;
        confirmDivLock.children[1].children[0].children[0].innerText = "Bạn muốn khóa tài khoản này?";
        confirmDivLock.classList.remove('hide')
        e.stopPropagation()
    })
})
var unlLockBtn = document.querySelectorAll('.content-list-user .item .button.unlock');
unlLockBtn.forEach((item, index) => {
    item.addEventListener('click', function (e){
        confirmDivLock.children[1].children[1].value = item.parentElement.parentElement.children[9].value;
        confirmDivLock.children[1].children[0].children[0].innerText = "Bạn mở muốn khóa tài khoản này?";
        confirmDivLock.classList.remove('hide')
        e.stopPropagation()
    })
})

panelLock.addEventListener('click', function (){
    confirmDivLock.classList.add('hide')
})

exitConfirmLock.addEventListener('click', function (){
    confirmDivLock.classList.add('hide')
})

var addNewUser = document.querySelector('.content-add')
var addNewUserDiv = document.querySelector('.add-new-user')
var panelAdd = document.querySelector('.add-new-user .panel')
var exitAddDiv = document.querySelector('.add-new-user .exit')

addNewUser.addEventListener('click', function (){
    addNewUserDiv.classList.remove('hide')
})

panelAdd.addEventListener('click', function (){
    addNewUserDiv.classList.add('hide')
})

exitAddDiv.addEventListener('click', function (){
    addNewUserDiv.classList.add('hide')
})
var errorCreate = document.getElementById('errorCreate');
if (errorCreate.innerText !== ""){
    addNewUserDiv.classList.remove('hide')
}

